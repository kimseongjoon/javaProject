package order;

import product.Product;
import product.Products;

public class OrderedProduct extends Product implements AutoCloseable{
    private int salesQuantity;
    private int totalPrice;

    public OrderedProduct(int productID, String name, int price, int quantity, String brand, int salesQuantity) throws ProductQuantityException {
        super(productID, name, price, quantity, brand);
        try {
            setSalesQuantity(salesQuantity);
        } catch (ProductQuantityException e) {
            throw e;
        }
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setSalesQuantity(int salesQuantity) throws ProductQuantityException {
        Products products = Products.getInstance();
        Product product = products.getProduct(super.getId());
        int productQuantity = product.getQuantity();
        if ( (productQuantity > 0) && (salesQuantity - this.salesQuantity <= productQuantity) ) {
            product.setQuantity(productQuantity - (salesQuantity - this.salesQuantity));
        }
        else {
            throw new ProductQuantityException();
        }
        this.salesQuantity = salesQuantity;
        this.totalPrice = salesQuantity * super.getPrice();
    }

    @Override
    public String toString() {
        return  "{상품명 : '" + super.getName() + '\'' +
                ", 브랜드 : '" + super.getBrand() + '\'' +
                ", 단가 : " + super.getPrice() +
                "원, 구매수량 : " + salesQuantity +
                "개, 상품금액 : " + totalPrice + "원}";
    }

    @Override
    public void close() throws Exception {
        // do something
    }
}
