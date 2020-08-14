package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import product.RegisteredProduct;
import product.RegisteredProducts;
import util.HibernateUtil;

public class HibernateTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        RegisteredProduct registeredProduct = new RegisteredProduct();
        registeredProduct.setId(1);
        registeredProduct.setName("반팔 티셔츠");
        registeredProduct.setBrand("a사");
        registeredProduct.setPrice(20000);
        registeredProduct.setQuantity(100);

        session.save(registeredProduct);

        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();

        RegisteredProduct registeredProduct1 = registeredProducts.getProduct(1);
        System.out.println(registeredProduct1.getName());


        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
