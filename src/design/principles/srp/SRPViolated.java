package design.principles.srp;

import java.util.ArrayList;
import java.util.List;

// Product class representing any item of any ECommerce.
record Product(String name, double price) {
}

// Violating SRP: ShoppingCart is handling multiple responsibilities
class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    // 1. Calculates total price in cart.
    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.price();
        }
        return total;
    }

    // 2. Violating SRP - Prints invoice (Should be in a separate class)
    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        for (Product p : products) {
            System.out.println(p.name() + " - Rs " + p.price());
        }
        System.out.println("Total: Rs " + calculateTotal());
    }

    // 3. Violating SRP - Saves to DB (Should be in a separate class)
    public void saveToDatabase() {
        getProducts().forEach(p ->
                System.out.println("Product : " + p.name() + " with price: " + p.price() + " saved to database."));
    }
}

public class SRPViolated {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        cart.printInvoice();
        cart.saveToDatabase();
    }
}
