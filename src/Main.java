import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            // Create some products
            Product tv = new ShippableProduct("TV", 500.0, 5, null, 10.0);
            Product cheese = new ShippableProduct("Cheese", 20.0, 10, LocalDate.now().plusDays(5), 1.5);
            Product scratchCard = new Product("Mobile Scratch Card", 10.0, 50, null);
            Product expiredBiscuits = new ShippableProduct("Biscuits", 5.0, 30, LocalDate.now().minusDays(1), 0.3);

            // Create a customer
            Customer customer = new Customer("Michael", 1000.0);

            // Add items to cart
            customer.addToCart(tv, 1);              // 1 * 500
            customer.addToCart(cheese, 2);          // 2 * 20
            customer.addToCart(scratchCard, 3);     // 3 * 10
            //customer.addToCart(expiredBiscuits, 1); // Will fail at checkout

            // Try checkout
            customer.checkout();
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }
}
