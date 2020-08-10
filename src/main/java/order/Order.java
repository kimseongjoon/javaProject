package order;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class Order {
    private int id;
    private Date time;
    private HashSet<OrderdProduct> orderdProducts;
    private int totalPrice;

    public void ordering() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setOrderdProducts(HashSet<OrderdProduct> orderdProducts) {
        this.orderdProducts = orderdProducts;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        Iterator<OrderdProduct> ir = orderdProducts.iterator();

        StringBuilder orderedListStr = new StringBuilder();
        int totalPrice = 0;

        while (ir.hasNext()) {
            OrderdProduct product = ir.next();
            totalPrice += product.getPrice();
            orderedListStr.append(product.toString()).append("\n");
        }
        return "Order{" +
                ", 주문시간=" + time +
                "\n주문목록=\n" + orderedListStr.toString() +
                ", 총 구매액=" + totalPrice +
                '}';
    }
}
