package order;

public class OrderdProduct {
    private int productId;
    private String name;
    private String brand;
    private int salesQuantity;
    private int unitPrice;
    private int price;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderdProduct{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", salesQuantity=" + salesQuantity +
                ", unitPrice=" + unitPrice +
                ", price=" + price +
                '}';
    }
}
