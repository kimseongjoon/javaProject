package order;

import product.RegisteredProduct;
import product.RegisteredProducts;

import javax.persistence.Entity;
import javax.persistence.Table;


public class OrderedProduct implements AutoCloseable{
    private int id;
    private String name;
    private String brand;
    private int price;
    private int salesQuantity;
    private int totalPrice;


    public OrderedProduct(int id, String name, String brand, int price, int salesQuantity) throws ProductQuantityException {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        try {
            setSalesQuantity(salesQuantity);
        } catch (ProductQuantityException e) {
            throw e;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalesQuantity(int salesQuantity) throws ProductQuantityException {
        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct = registeredProducts.getProduct(1); // 수정 필요
        int productQuantity = registeredProduct.getQuantity();
        if ( (productQuantity > 0) && (salesQuantity - this.salesQuantity <= productQuantity) ) {
            registeredProduct.setQuantity(productQuantity - (salesQuantity - this.salesQuantity));
        }
        else {
            throw new ProductQuantityException();
        }
        this.salesQuantity = salesQuantity;
        this.totalPrice = salesQuantity * price;
    }

    @Override
    public String toString() {
        return  "{상품명 : '" + name + '\'' +
                ", 브랜드 : '" + brand + '\'' +
                ", 단가 : " + price +
                "원, 구매수량 : " + salesQuantity +
                "개, 상품금액 : " + totalPrice + "원}";
    }

    @Override
    public void close() throws Exception {
        // do something
    }
}
