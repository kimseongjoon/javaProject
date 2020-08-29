package order;

import org.hibernate.SessionFactory;
import product.RegisteredProduct;
import product.RegisteredProducts;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Iterator;

public class OrderedProducts {
    HashSet<OrderedProduct> orderedProductList;

    private static OrderedProducts instance = new OrderedProducts();
    private OrderedProducts() {
        orderedProductList = new HashSet<>();
    }

    public static OrderedProducts getInstance() {
        if (instance == null) {
            instance = new OrderedProducts();
        }
        return instance;
    }

    public void addOrderedProduct(long productID, int purchaseQuantity) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager em = sessionFactory.createEntityManager();

        RegisteredProduct findRegProduct = em.find(RegisteredProduct.class, productID);


        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct = registeredProducts.getProduct(productID); // 수정 필요
        if (registeredProduct == null) {
            System.out.println("해당 상품은 상품리스트에서 존재하지 않습니다.");
            return;
        }


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        OrderedProduct orderedProduct = null;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<OrderedProduct> query = builder.createQuery(OrderedProduct.class);
        Root<OrderedProduct> root = query.from(OrderedProduct.class);
        query.select(root).where(builder.and(builder.equal(root.get("registeredProduct"), productID)));

        try {
            orderedProduct = em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            orderedProduct = new OrderedProduct();

            orderedProduct.setRegisteredProduct(registeredProduct);
            orderedProduct.setTotalPrice(registeredProduct.getPrice() * purchaseQuantity);
        }

        try {
            orderedProduct.setSalesQuantity(orderedProduct.getSalesQuantity() + purchaseQuantity);
        } catch (ProductQuantityException e) {
            System.out.println("구매할 수 있는 최대수량을 초과하였습니다.");
            em.close();

            return;
        }

        em.persist(orderedProduct);

        tx.commit();
        em.close();
    }

    public int calTotalPrice() {
        Iterator<OrderedProduct> ir = orderedProductList.iterator();

        int totalPrice = 0;

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            totalPrice += product.getTotalPrice();
        }

        return totalPrice;
    }

    public String printOrderedProducts() {
        Iterator<OrderedProduct> ir = orderedProductList.iterator();

        StringBuilder orderedListStr = new StringBuilder();


        orderedListStr.append("========================================구매 목록========================================\n");

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            orderedListStr.append(product.toString()).append("\n");
        }
        orderedListStr.append("========================================================================================");

        return orderedListStr.toString();
    }

    @Override
    public String toString() {
        return "cOrderedProducts{" +
                "주문목록=\n" + printOrderedProducts() +
                "총 구매액=" + calTotalPrice() +
                '}';
    }
}
