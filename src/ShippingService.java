import java.util.List;
class ShippingService {
    public void ship(List<Shippable> items) {
        System.out.println("Shipping the following items:");
        for (Shippable item : items) {
            System.out.printf("Item: %s, Weight: %.2f\n", item.getName(), item.getWeight());
        }
    }
}
