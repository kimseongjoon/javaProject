package order;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class Order {
    private int id;
    private Date time;
    private HashSet<OrderedProducts> orderedProducts;
    private int totalPrice;

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setOrderedProducts(HashSet<OrderedProducts> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        Iterator<OrderedProducts> ir = orderedProducts.iterator();

        StringBuilder orderedListStr = new StringBuilder();
        int totalPrice = 0;

        while (ir.hasNext()) {
            OrderedProducts product = ir.next();
            //totalPrice += product.getPrice();
            orderedListStr.append(product.toString()).append("\n");
        }
        return "Order{" +
                ", 주문시간=" + time +
                "\n주문목록=\n" + orderedListStr.toString() +
                ", 총 구매액=" + totalPrice +
                '}';
    }
}
