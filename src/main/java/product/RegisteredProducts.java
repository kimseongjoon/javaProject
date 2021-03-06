package product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import order.OrderedProduct;
import order.QOrderedProduct;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class RegisteredProducts {
    private static RegisteredProducts instance = new RegisteredProducts();

    public static RegisteredProducts getInstance() {
        if (instance == null) {
            instance = new RegisteredProducts();
        }
        return instance;
    }

    public RegisteredProduct getProduct(long productID) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        RegisteredProduct findRegProduct = entityManager.find(RegisteredProduct.class, productID);

        entityManager.close();
        return findRegProduct;
    }

    public List<RegisteredProduct> getProductList() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RegisteredProduct> query = builder.createQuery(RegisteredProduct.class);
        Root<RegisteredProduct> root = query.from(RegisteredProduct.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    public void printAllProducts() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QRegisteredProduct qregisteredProduct = QRegisteredProduct.registeredProduct;
        List<RegisteredProduct> regProductList =
                queryFactory.selectFrom(qregisteredProduct)
                        .fetch();

        Iterator<RegisteredProduct> ir = regProductList.iterator();

        System.out.println("========================================상품 목록========================================");

        while (ir.hasNext()) {
            RegisteredProduct registeredProduct = ir.next();
            System.out.print(registeredProduct.getId() + "번 : " + registeredProduct);
            if (registeredProduct.getQuantity() <= 0) {
                System.out.println(" (품절)");
            } else {
                System.out.println();
            }
        }

        System.out.println("========================================================================================");

        entityManager.close();
    }

    public String addProduct(RegisteredProduct registeredProduct) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager em = sessionFactory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(registeredProduct);
        tx.commit();

        em.close();

        return "상품을 등록하였습니다.";
        /*int addIndex = 0;
        Iterator<Integer> ir = productList.keySet().iterator();

        if ( (productList.size() != 0) && (productList.lastKey() != productList.size()) ) {
            int prevKey = 0;
            while (ir.hasNext()) {
                int key = ir.next();
                if ((prevKey + 1) != key) {
                    addIndex = prevKey + 1;
                    break;
                }
            }
        }
        else {
            addIndex = productList.size() + 1;
        }

        if (productList.containsKey(addIndex)) {
            return "상품등록을 실패하였습니다.";
        }

        registeredProduct.setId(addIndex);
        productList.put(addIndex, registeredProduct);

        return "상품을 등록하였습니다.";*/
    }

        session.save(registeredProduct);
    }

    public void delProduct(int productID) {
        Session session = HibernateUtil.getSession();

        RegisteredProduct registeredProduct = (RegisteredProduct)session.get(RegisteredProduct.class, productID);

        session.delete(registeredProduct);
    }

    public RegisteredProduct searchProduct(String tag, Object value) throws NoResultException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RegisteredProduct> query = builder.createQuery(RegisteredProduct.class);
        Root<RegisteredProduct> root = query.from(RegisteredProduct.class);
        query.select(root).where(builder.and(builder.equal(root.get(tag), value)));

        return entityManager.createQuery(query).getSingleResult();
    }
}
