package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import product.RegisteredProduct;
import product.RegisteredProducts;
import util.HibernateUtil;

public class HibernateTest {
    public static void main(String[] args) {

        //RegisteredProducts registeredProducts = RegisteredProducts.getInstance();

        //RegisteredProduct registeredProduct1 = registeredProducts.getProduct(1);
        /*RegisteredProduct registeredProduct1 = registeredProducts.searchProduct("name", "신발");
        System.out.println(registeredProduct1);*/


        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = HibernateUtil.getSession();

            session.beginTransaction();

            //RegisteredProduct registeredProduct = new RegisteredProduct("반팔 티셔츠", "A사", 20000, 100);


            RegisteredProduct registeredProduct = new RegisteredProduct();
            registeredProduct.setName("반팔 티셔츠");
            registeredProduct.setBrand("A사");
            registeredProduct.setPrice(20000);
            registeredProduct.setQuantity(100);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("컴퓨터", "B사", 1000000, 30);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("책", "C사", 15000, 150);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("가죽자켓", "D사", 20000, 70);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("장난감", "E사", 5000, 50);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("화장품", "F사", 40000, 100);
            session.save(registeredProduct);
            registeredProduct = new RegisteredProduct("신발", "G사", 250000, 5);
            session.save(registeredProduct);


            session.getTransaction().commit();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //session.getTransaction().commit();
        //HibernateUtil.getSession().close();
        //HibernateUtil.getSessionFactory().close();
    }
}
