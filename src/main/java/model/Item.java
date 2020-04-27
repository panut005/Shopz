package model;

public class Item {
    private String id  ;
    private String item;
    private double price;
    private int quantity;
    private double amount;
    private String month;
    private String year;
    private String type;

    public Item(String id, String item, double price, int quantity, double amount,String month,String year,String type) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.month=month;
        this.year=year;
        this.type=type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item(String id, String item, double price, int quantity, double amount, String month, String year) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
