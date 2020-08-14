package product;

public class RegisteredProductsTest {
    public static void main(String[] args) {
        RegisteredProduct registeredProduct;
        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        registeredProduct = new RegisteredProduct("제품", "a사", 15000, 100);

        System.out.println(registeredProducts.addProduct(registeredProduct));
        registeredProduct = new RegisteredProduct("제품", "a사", 15000, 100);

        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("제품", "a사", 15000, 100);

        registeredProducts.addProduct(registeredProduct);
        //System.out.println(products.getProduct(1));


    }
}
