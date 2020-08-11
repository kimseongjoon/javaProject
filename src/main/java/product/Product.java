package product;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    //private int categoryId;
    private String brand; //private int brandId;

    public Product(String name, int price, int quantity, String brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
//        this.categoryId = categoryId;
//        this.brandId = brandId;
        this.brand = brand;
    }

    public Product(int id, String name, int price, int quantity, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrand() {
        return brand;
    }

    /* public int getCategoryId() {
        return categoryId;
    }

    public int getBrandId() {
        return brandId;
    }*/

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
