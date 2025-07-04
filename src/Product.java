import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private Integer quantity;
    private LocalDate expiryDate;
    public Product(String name, double price, Integer quantity,LocalDate expiryDate) throws Exception {
        if(name=="" || price <0 || quantity<0 ) throw new Exception("enter a valid product");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isExpired() {
        if(expiryDate==null) return false;

        return LocalDate.now().isAfter(expiryDate);
    }
    // thread safe method needed
    public synchronized void decreaseQuantity(Integer quantity){
        this.quantity-=quantity;
    }
}
