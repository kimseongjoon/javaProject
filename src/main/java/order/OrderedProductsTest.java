package order;

import product.Product;
import product.Products;

public class OrderedProductsTest {
    public static void main(String[] args) {
        Products products = Products.getInstance();
        Product product;

        product = new Product("제품", 15000, 100, "a");
        products.addProduct(product);

        product = new Product("제품2", 25000, 200, "b");
        products.addProduct(product);

        product = new Product("제품3", 35000, 10, "c");
        products.addProduct(product);

        OrderedProducts orderedProducts = OrderedProducts.getInstance();
        //System.out.println(orderedProducts.printOrderedProducts());
        //System.out.println(orderedProducts.calTotalPrice());
        orderedProducts.addOrderedProduct(1, 50);
        orderedProducts.addOrderedProduct(4, 50);
        orderedProducts.addOrderedProduct(3, 50);

        System.out.println(orderedProducts);
    }
}
