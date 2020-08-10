package product;

import java.util.Iterator;
import java.util.TreeMap;

public class ProductsTest {
    public static void main(String[] args) {
        Product product;
        Products products = Products.getInstance();
        product = new Product("제품", 15000, 100, 1, 1);

        System.out.println( products.addProduct(product));
         product = new Product("제품2", 25000, 200, 1, 1);

        products.addProduct(product);
         product = new Product("제품3", 35000, 300, 1, 1);

        products.addProduct(product);
        //System.out.println(products.getProduct(1));





    }
}
