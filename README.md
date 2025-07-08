1. 📦 Define Products with Dynamic Behaviors
Products may have different behaviors:

Shippable → needs shipping, has weight

Expirable → has expiry, may become invalid

Example:

java
Copy
Edit
Product cheese = new ExpirableShippableProduct("Cheese", 10.0, 5, LocalDate.of(2025, 1, 1), 2.0);
Product tv = new ShippableProduct("TV", 1000.0, 2, 15.0);
Product scratchCard = new Product("Mobile Card", 50.0, 10);
You can combine behaviors using interfaces and concrete implementations.

2. 🛒 Add Items to Cart with Validation
java
Copy
Edit
Cart cart = new Cart();
cart.addItem(cheese, 2); // Adds 2 Cheese
cart.addItem(tv, 1);     // Adds 1 TV
If requested quantity exceeds stock, an exception is thrown.

Cart holds CartItem objects tracking quantity and product.

3. ✅ Checkout with Policies & Service Integration
java
Copy
Edit
CheckoutService checkoutService = new CheckoutServiceImpl(new DefaultShippingService());
checkoutService.checkout(cart);
What happens at checkout:

Verifies all items in stock

Verifies none are expired

Ships all shippable items via ShippingService

Decrements stock quantities

4. 🚚 Shipping Service (Pluggable via DI)
java
Copy
Edit
ShippingService shippingService = new DefaultShippingService();
shippingService.ship(cart.getShippableItems());
This design allows for:

Swapping in mock shipping services for testing

Extending support to external APIs (FedEx, Aramex, etc.)

5. 📈 Expiry Validation
java
Copy
Edit
boolean isExpired = ((Expirable) cheese).isExpired();
You can dynamically test if a product has expired before checkout, which prevents accidental purchase of expired products.

