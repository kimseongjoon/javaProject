package registerproduct;

import base.Product;

public class RegisteredProduct extends Product {
    private int quantity;

    public RegisteredProduct(int id, String name, String brand, int price, int quantity) {
        super(id, name, brand, price);
        this.quantity = quantity;
    }

    public RegisteredProduct(String name, String brand, int price, int quantity) {
        super(name, brand, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{상품명 : '" + name + '\'' +
               ", 브랜드 ': " + brand + '\'' +
               ", 상품가격 : " + price +
               "원, 재고 : " + quantity + "개}"
               /*", categoryId=" + categoryId +
               ", brandId=" + brandId +*/;
    }
}
