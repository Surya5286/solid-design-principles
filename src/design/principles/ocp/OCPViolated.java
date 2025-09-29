package design.principles.ocp;

import java.util.ArrayList;
import java.util.List;

// Product class representing any item of any ECommerce.
class Product {
    public String name;
    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

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
            total += p.price;
        }
        return total;
    }
}

class ShoppingCartPrinter {
    private final ShoppingCart cart;

    public ShoppingCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        List<Product> products = cart.getProducts();
        for (Product p : products)
            System.out.println(p.name + " - Rs " + p.price);
    }
}

class SaveProductToDB {
    private final ShoppingCart cart;

    public SaveProductToDB(ShoppingCart cart) {
        this.cart = cart;
    }

    // 1. Save to SQL DB - Violating OCP
    public void saveToSql() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to SQL DB."));
        System.out.println();
    }

    // 2. Save to Mongo DB - Violating OCP
    public void saveToMongo() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to MongoDB."));
        System.out.println();
    }

    // 3. Save to File - Violating OCP
    public void saveToFile() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to File Path."));
    }
}

public class OCPViolated {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        System.out.println("\n--- Saving Products ---");
        SaveProductToDB saveProduct = new SaveProductToDB(cart);
        saveProduct.saveToSql();
        saveProduct.saveToMongo();
        saveProduct.saveToFile();
    }
}
