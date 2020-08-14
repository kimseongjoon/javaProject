package product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RegisteredProduct")
public class RegisteredProduct {

    @Id
    private int id;
    private String name;
    private String brand;
    private int price;
    private int quantity;


    public RegisteredProduct(int id, String name, String brand, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public RegisteredProduct(String name, String brand, int price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public RegisteredProduct() {

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

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
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
