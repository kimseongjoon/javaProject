package order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.SessionFactory;
import product.QRegisteredProduct;
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
import java.util.List;

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

        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct = registeredProducts.getProduct(productID);
        if (registeredProduct == null) {
            System.out.println("해당 상품은 상품리스트에서 존재하지 않습니다.");
            em.close();
            return;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QOrderedProduct orderedProduct = QOrderedProduct.orderedProduct;
        OrderedProduct foundOrderedProduct =
            queryFactory.selectFrom(orderedProduct)
            .where(orderedProduct.registeredProduct.id.eq(productID))
            .fetchOne();

        if (foundOrderedProduct == null) {
            foundOrderedProduct = new OrderedProduct();

            foundOrderedProduct.setRegisteredProduct(registeredProduct);
            foundOrderedProduct.setTotalPrice(registeredProduct.getPrice() * purchaseQuantity);
        }

        try {
            foundOrderedProduct.setSalesQuantity(foundOrderedProduct.getSalesQuantity() + purchaseQuantity);
        } catch (ProductQuantityException e) {
            System.out.println("구매할 수 있는 최대수량을 초과하였습니다.");
            em.close();
            return;
        }

        em.persist(foundOrderedProduct);

        tx.commit();
        em.close();
    }

    public int calTotalPrice() {


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QOrderedProduct qOrdedProduct = QOrderedProduct.orderedProduct;
        List<OrderedProduct> ordProductList =
                queryFactory.selectFrom(qOrdedProduct)
                        .fetch();

        Iterator<OrderedProduct> ir = ordProductList.iterator();

        int totalPrice = 0;

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            totalPrice += product.getTotalPrice();
        }

        return totalPrice;
    }

    public void printOrderedProducts() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QOrderedProduct qOrdedProduct = QOrderedProduct.orderedProduct;
        List<OrderedProduct> ordProductList =
                queryFactory.selectFrom(qOrdedProduct)
                        .fetch();

        Iterator<OrderedProduct> ir = ordProductList.iterator();

        StringBuilder orderedListStr = new StringBuilder();

        orderedListStr.append("========================================구매 목록=========================================\n");

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            orderedListStr.append(product.toString()).append("\n");
        }

        orderedListStr.append("========================================================================================");

        entityManager.close();

        return orderedListStr.toString();
    }
}
