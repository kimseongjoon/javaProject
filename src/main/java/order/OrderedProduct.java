package order;

import product.RegisteredProduct;
import product.RegisteredProducts;

import javax.persistence.*;

@Entity
@Table(name = "ORDPRODUCT")
public class OrderedProduct  {
    @Id
    @GeneratedValue
    private long id;
    private int salesQuantity;
    private int totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REG_PROD_ID")
    private RegisteredProduct registeredProduct;

    public OrderedProduct(int productId, int salesQuantity) throws ProductQuantityException {
        try {
            setSalesQuantity(salesQuantity);
        } catch (ProductQuantityException e) {
            throw e;
        }
    }

    public OrderedProduct() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public int getTotalPrice()   {
        return totalPrice;
    }

    public RegisteredProduct getRegisteredProduct() {
        return registeredProduct;
    }

    public void setRegisteredProduct(RegisteredProduct registeredProduct) {
        this.registeredProduct = registeredProduct;
    }

    public void setSalesQuantity(int salesQuantity) throws ProductQuantityException {
        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct = this.getRegisteredProduct();

        int productQuantity = registeredProduct.getQuantity();
        if ((productQuantity > 0) && (salesQuantity - this.salesQuantity <= productQuantity)) {
            registeredProduct.setQuantity(productQuantity - (salesQuantity - this.salesQuantity));
        } else {
            throw new ProductQuantityException();
        }
        this.salesQuantity = salesQuantity;
        this.totalPrice = salesQuantity * registeredProduct.getPrice();
    }

    @Override
    public String toString() {
        return "{상품명 : '" + registeredProduct.getName() + '\'' +
                ", 브랜드 : '" + registeredProduct.getBrand() + '\'' +
                ", 단가 : " + registeredProduct.getPrice() +
                "원, 구매수량 : " + salesQuantity +
                "개, 상품금액 : " + totalPrice + "원}";
    }

    /*@Override
    public void close() throws Exception {
        // do something
    }*/
}
