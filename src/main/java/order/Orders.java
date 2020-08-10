package order;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

public class Orders {
    private TreeMap<Integer, Order> orderList;
    private static Orders instance = new Orders();

    private Orders() {
        orderList = new TreeMap<Integer, Order>();
    }

    public static Orders getInstance() {
        if (instance == null) {
            instance = new Orders();
        }
        return instance;
    }

    public void ordering() {
        Order order = new Order();
        OrderdProduct orderdProduct = new OrderdProduct();


        order.setOrderdProducts(orderdProduct);
        order.setTime(new Date());

    }

    public String addOrder(Order order) {
        int addIndex = 0;
        Iterator<Integer> ir = orderList.keySet().iterator();

        if ( (orderList.size() != 0) && (orderList.lastKey() != orderList.size()) ) {
            int prevKey = 0;
            while (ir.hasNext()) {
                int key = ir.next();
                if ((prevKey + 1) != key) {
                    addIndex = prevKey + 1;
                    break;
                }
            }
        }
        else {
            addIndex = orderList.size() + 1;
        }

        if (orderList.containsKey(addIndex)) {
            return "상품등록을 실패하였습니다.";
        }

        order.setId(addIndex);
        orderList.put(addIndex, order);

        return "상품을 등록하였습니다.";
    }
}
