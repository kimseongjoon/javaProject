package order;

import registerproduct.RegisteredProduct;
import registerproduct.RegisteredProducts;

public class OrderedProductsTest {
    public static void main(String[] args) {
        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct;

        registeredProduct = new RegisteredProduct("제품", "a", 15000, 100);
        registeredProducts.addProduct(registeredProduct);

        registeredProduct = new RegisteredProduct("제품2", "b", 25000, 200);
        registeredProducts.addProduct(registeredProduct);

        registeredProduct = new RegisteredProduct("제품3", "c", 35000, 10);
        registeredProducts.addProduct(registeredProduct);

        OrderedProducts orderedProducts = OrderedProducts.getInstance();
        //System.out.println(orderedProducts.printOrderedProducts());
        //System.out.println(orderedProducts.calTotalPrice());
        orderedProducts.addOrderedProduct(1, 50);
        orderedProducts.addOrderedProduct(4, 50);
        orderedProducts.addOrderedProduct(3, 50);

        System.out.println(orderedProducts);
    }
}
