import java.util.List;
class ShippingService {
    public void ship(List<CartItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (CartItem item : items) {
            Product product = item.getProduct();

            Shippable ship = (Shippable) product;
            double itemWeight = ship.getWeight() * item.getQuantity();
            totalWeight += itemWeight;
            System.out.printf("%dx %s %.0fg\n", item.getQuantity(), product.getName(), itemWeight * 1000);

        }

        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }


}
