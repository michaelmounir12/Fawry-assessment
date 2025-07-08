# 🛒 Java Shopping Cart System

A Java console-based system simulating a simple shopping cart and checkout process. Demonstrates object-oriented principles including interfaces, encapsulation, exception handling, and separation of concerns.

---

## ✨ Features

* Add various products to a cart
* Handle **stock validation** when adding items
* Detect **expired products** before checkout
* Handle **shippable products** with weight-based summaries
* Perform complete **checkout** flow with balance deduction

---

## 📅 How to Run

### Requirements

* Java 11+

### Compile and Execute

```bash
javac *.java
java Main
```

---

## 📃 Product Types

```java
Product tv = new ShippableProduct("TV", 500.0, 5, null, 10.0);
Product cheese = new ShippableProduct("Cheese", 20.0, 10, LocalDate.now().plusDays(5), 1.5);
Product scratchCard = new Product("Mobile Scratch Card", 10.0, 50, null);
Product expiredBiscuits = new ShippableProduct("Biscuits", 5.0, 30, LocalDate.now().minusDays(1), 0.3);
```

* `ShippableProduct` implements `Shippable`
* `Product` may or may not be perishable (based on expiration date)

---

## 📄 Cart System

### Add to Cart

```java
customer.addToCart(tv, 1);
customer.addToCart(cheese, 2);
```

* Throws exception if stock is insufficient
* Supports quantity merging for same product

### Get Cart Details

```java
cart.getSubtotal();
cart.getShippableItems();
```

---

## 💸 Checkout Flow

```java
customer.checkout();
```

Checks:

* Cart is not empty
* No expired products
* Stock availability
* Deducts balance from customer
* Triggers shipment for shippable items

### Shipping Summary

```java
** Shipment notice **
1x TV 10000g
2x Cheese 3000g
Total package weight 13.0kg
```

---

## 💡 Design Highlights

* `CartItem` holds `Product` and quantity
* `Shippable` interface isolates shipping behavior
* `ShippingService` computes and prints total weight
* `Customer` class orchestrates the interaction

---

## 🤖 Sample Output

```
** Shipment notice **
1x TV 10000g
2x Cheese 3000g
Total package weight 13.0kg
```



