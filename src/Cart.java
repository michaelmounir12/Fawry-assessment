import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Cart {
    private Map<String,CartItem> items = new HashMap<>();

    public void addItem(Product product, int quantity) throws Exception {
        if (quantity > product.getQuantity()) {
            throw new Exception("Not enough stock for product: " + product.getName());
        }
        String productName = product.getName();
        if (items.containsKey(productName)) {
            CartItem existingItem = items.get(productName);
            int newQuantity = existingItem.getQuantity() + quantity;
            items.put(productName, new CartItem(product, newQuantity));
        } else {
            items.put(productName, new CartItem(product, quantity));
        }
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }


    public List<Shippable> getShippableItems() {
        return items.values().stream()
                .map(CartItem::getProduct)
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toList());
    }
    public void eraseProducts(){
        items.clear();
    }
}
