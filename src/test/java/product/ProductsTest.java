package product;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    Products products = Products.getInstance();
    @Test
    void addProduct() {
        Product product = new Product("제품", 15000, 100, 1, 1);
        product = new Product("제품", 15000, 100, 1, 1);

        System.out.println( products.addProduct(product));
        product = new Product("제품2", 25000, 200, 1, 1);

        products.addProduct(product);
        product = new Product("제품3", 35000, 300, 1, 1);

        products.addProduct(product);
        assertEquals("상품을 등록하였습니다.", products.addProduct(product));
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProductList() {
        TreeMap<Integer, Product> a = products.getProductList();

        Iterator<Integer> ir = a.keySet().iterator();

        System.out.println("===========================상품 목록=============================");

        while (ir.hasNext()) {
            int key = ir.next();
            Product product = a.get(key);
            System.out.println(product);
        }

        System.out.println("==================================================================");

    }

    @Test
    void printAllProducts() {
        products.printAllProducts();
    }

    @Test
    void delProduct() {
        System.out.println(products.delProduct(4));
    }

    @Test
    void searchProduct() {
        try {
            Product a = products.searchProduct("name", "제품3");

            if (a != null) {
                System.out.println(a.toString());
            } else {
                System.out.println("상품을 찾을 수 없습니다.");
            }



            a = products.searchProduct("price", 40000);

            if (a != null) {
                System.out.println(a.toString());
            } else {
                System.out.println("상품을 찾을 수 없습니다.");
            }


            a = products.searchProduct("price", 35000);

            if (a != null) {
                System.out.println(a.toString());
            } else {
                System.out.println("상품을 찾을 수 없습니다.");
            }

        } catch (NoSuchFieldException e) {
            System.out.println("상품 속성이 잘 못 입력되었습니다.");
        }
    }
}