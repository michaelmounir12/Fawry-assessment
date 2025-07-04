import java.time.LocalDate;

public class ShippableProduct extends Product implements Shippable{

    private double weight;

    public ShippableProduct(String name, double price, Integer quantity, LocalDate expiryDate, double weight) throws Exception{

        super(name,price,quantity,expiryDate);
        this.weight = weight;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
