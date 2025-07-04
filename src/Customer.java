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
                .mapToDouble(item -> {
                    Product product = item.getProduct();
                    double unitWeight = ((Shippable) product).getWeight();
                    return unitWeight * item.getQuantity(); // weight × quantity

                })
                .sum() * 0.25; // rate per kg

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
        List<CartItem> toShip = cart.getShippableItems();
        if (!toShip.isEmpty()) {
            new ShippingService().ship(toShip);
        }

        // print receipt
        printCheckoutReceipt(cart.getItems(),shippingFees,subtotal);
        cart.eraseProducts();
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }
    public static void printCheckoutReceipt(List<CartItem> items, double shippingFee,double subtotal) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : items) {
            Product product = item.getProduct();

            double itemPrice = product.getPrice() * item.getQuantity();
            System.out.printf("%dx %s %.0f\n", item.getQuantity(), product.getName(), itemPrice);

        }
        double amount = subtotal + shippingFee;

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", amount);
    }
}
