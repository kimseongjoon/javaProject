package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import product.RegisteredProduct;
import product.RegisteredProducts;
import util.HibernateUtil;

import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {

        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();


        /*RegisteredProduct registeredProduct1 = registeredProducts.searchProduct("name", "신발");
        System.out.println(registeredProduct1);*/


        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = HibernateUtil.getSession();

            session.beginTransaction();

            //List<RegisteredProduct> registeredProductList = RegisteredProducts.getInstance().getProductList();
            RegisteredProducts.getInstance().printAllProducts();
            /*RegisteredProduct registeredProduct = new RegisteredProduct("반팔 티셔츠", "A사", 20000, 100);
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
            session.save(registeredProduct);*/


            //RegisteredProduct registeredProduct1 = registeredProducts.getProduct(8);
            //System.out.println(registeredProduct1);

            //registeredProducts.delProduct(9);
            session.getTransaction().commit();
            session.close();

            //session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //session.getTransaction().commit();
        //HibernateUtil.getSessionFactory().close();
    }
}
