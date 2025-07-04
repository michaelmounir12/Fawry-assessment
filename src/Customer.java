import java.util.List;

class Customer {
    private String name;
    private double balance;
    private Cart cart = new Cart();

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void addToCart(Product product, int quantity) throws Exception {
        cart.addItem(product, quantity);
    }

    public void checkout() throws Exception {
        if (cart.isEmpty()) throw new Exception("Cart is empty");

        double subtotal = cart.getSubtotal();
        double shippingFees = cart.getShippableItems().stream()
                .mapToDouble(Shippable::getWeight)
                .sum() * 0.25; // shipping rate per kg

        double total = subtotal + shippingFees;
        //filter expired items
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                throw new Exception("Product expired: " + item.getProduct().getName());
            }

        }
        //check if quantity left at the time of checking out is accepted
        for(CartItem item : cart.getItems()){
            if(item.getProduct().getQuantity()<item.getQuantity()) throw new Exception("Specified qunatity is currently out of stock");
        }
        if (balance < total) throw new Exception("Insufficient balance");

        // deduct stock and balance
        for (CartItem item : cart.getItems()) {

            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        balance -= total;

        // trigger shipping
        List<Shippable> toShip = cart.getShippableItems();
        if (!toShip.isEmpty()) {
            new ShippingService().ship(toShip);
        }
        cart.eraseProducts();
        // print receipt
        System.out.println("Checkout Summary:");
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Shipping: %.2f\n", shippingFees);
        System.out.printf("Total Paid: %.2f\n", total);
        System.out.printf("Balance Left: %.2f\n", balance);
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }
}
