package product;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.TreeMap;

public class Products {
    private TreeMap<Integer, Product> productList;
    private static Products instance = new Products();

    private Products() {
        productList = new TreeMap<Integer, Product>();
    }

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }

    public Product getProduct(int productID) {
        if (productList.containsKey(productID)) {
            return productList.get(productID);
        } else {
            return null;
        }
    }

    public TreeMap<Integer, Product> getProductList() {
        return productList;
    }

    public void printAllProducts() {
        Iterator<Integer> ir = productList.keySet().iterator();

        System.out.println("========================================상품 목록========================================");

        while (ir.hasNext()) {
            int key = ir.next();
            Product product = productList.get(key);
            System.out.print(product.getId() + "번 : " + product);
            if (product.getQuantity() <= 0) {
                System.out.println(" (품절)");
            } else {
                System.out.println();
            }

        }

        System.out.println("========================================================================================");
    }

    public String addProduct(Product product) {
        int addIndex = 0;
        Iterator<Integer> ir = productList.keySet().iterator();

        if ( (productList.size() != 0) && (productList.lastKey() != productList.size()) ) {
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
            addIndex = productList.size() + 1;
        }

        if (productList.containsKey(addIndex)) {
            return "상품등록을 실패하였습니다.";
        }

        product.setId(addIndex);
        productList.put(addIndex, product);

        return "상품을 등록하였습니다.";
    }

    public String delProduct(int productId) {
        if (productList.remove(productId) != null) {
            return "해당 상품을 삭제하였습니다.";
        } else {
            return "해당 상품을 찾을 수 없습니다.";
        }
    }

    public Product searchProduct(String tag, Object value) throws NoSuchFieldException { // 리터럴 값만 비교 가능
        Iterator<Integer> ir = productList.keySet().iterator();

        while (ir.hasNext()) {
            int key = ir.next();
            Product product = productList.get(key);

            Field field = product.getClass().getDeclaredField(tag);
            field.setAccessible(true);
            try {
                Object result = field.get(product);
                if ((result).equals(value)) {
                    return product;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
