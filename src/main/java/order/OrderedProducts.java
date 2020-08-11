package order;

import product.Product;
import product.Products;

import java.util.HashSet;
import java.util.Iterator;

public class OrderedProducts {
    HashSet<OrderedProduct> orderedProducts;

    private static OrderedProducts instance = new OrderedProducts();
    private OrderedProducts() {
        orderedProducts = new HashSet<>();
    }

    public static OrderedProducts getInstance() {
        if (instance == null) {
            instance = new OrderedProducts();
        }
        return instance;
    }

    public void addOrderedProduct(int productID, int purchaseQuantity) {
        Products products = Products.getInstance();
        Product product = products.getProduct(productID);
        if (product == null) {
            System.out.println("해당 상품은 상품리스트에서 존재하지 않습니다.");
            return;
        }

        Iterator<OrderedProduct> ir = orderedProducts.iterator();

        while (ir.hasNext()) {
            OrderedProduct orderedProduct = ir.next();
            if (orderedProduct.getId() == productID) {
                try {
                    orderedProduct.setSalesQuantity(orderedProduct.getSalesQuantity() + purchaseQuantity);
                    return;
                } catch (ProductQuantityException e) {
                    System.out.println("구매할 수 있는 최대수량을 초과하였습니다.");
                }
            }
        }

        try (OrderedProduct orderedProduct = new OrderedProduct(productID, product.getName(), product.getPrice(), product.getQuantity(), product.getBrand(), purchaseQuantity)){
            orderedProducts.add(orderedProduct);
        } catch (ProductQuantityException e) {
            System.out.println("구매할 수 있는 최대수량을 초과하였습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calTotalPrice() {
        Iterator<OrderedProduct> ir = orderedProducts.iterator();

        int totalPrice = 0;

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            totalPrice += product.getTotalPrice();
        }

        return totalPrice;
    }

    public String printOrderedProducts() {
        Iterator<OrderedProduct> ir = orderedProducts.iterator();

        StringBuilder orderedListStr = new StringBuilder();


        orderedListStr.append("========================================구매 목록========================================\n");

        while (ir.hasNext()) {
            OrderedProduct product = ir.next();
            orderedListStr.append(product.toString()).append("\n");
        }
        orderedListStr.append("========================================================================================");

        return orderedListStr.toString();
    }

    @Override
    public String toString() {


        return "cOrderedProducts{" +
            "주문목록=\n" + printOrderedProducts() +
                    "총 구매액=" + calTotalPrice() +
                '}';
    }
}
