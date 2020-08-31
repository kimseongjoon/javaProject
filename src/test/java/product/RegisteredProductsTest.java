package product;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class RegisteredProductsTest {

    RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
    @Test
    void addProduct() {
        /*RegisteredProduct registeredProduct = new RegisteredProduct("제품", "a사", 15000, 100);

        System.out.println( registeredProducts.addProduct(registeredProduct));
        registeredProduct = new RegisteredProduct("제품2", "b사", 25000, 200);

        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("제품3", "c사", 35000, 300);

        registeredProducts.addProduct(registeredProduct);
        assertEquals("상품을 등록하였습니다.", registeredProducts.addProduct(registeredProduct));*/
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProductList() {
//        TreeMap<Integer, RegisteredProduct> a = registeredProducts.getProductList();
//
//        Iterator<Integer> ir = a.keySet().iterator();
//
//        System.out.println("===========================상품 목록=============================");
//
//        while (ir.hasNext()) {
//            int key = ir.next();
//            RegisteredProduct registeredProduct = a.get(key);
//            System.out.println(registeredProduct);
//        }
//
//        System.out.println("==================================================================");

    }

    @Test
    void printAllProducts() {
        registeredProducts.printAllProducts();
    }

    @Test
    void delProduct() {
        //System.out.println(registeredProducts.delProduct(4));
    }

    @Test
    void searchProduct() {
        RegisteredProduct a = registeredProducts.searchProduct("name", "제품3");

        if (a != null) {
            System.out.println(a.toString());
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }


        a = registeredProducts.searchProduct("price", 40000);

        if (a != null) {
            System.out.println(a.toString());
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }


        a = registeredProducts.searchProduct("price", 35000);

        if (a != null) {
            System.out.println(a.toString());
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }

    }
}