package base;

public class Product {
    protected int id;
    protected String name;
    protected String brand;
    protected int price;
    //private int brandId;
    //private int categoryId;

    public Product(int id, String name, String brand, int price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Product(String name, String brand, int price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
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

    public void setId(int id) {
        this.id = id;
    }
}
