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

record ShoppingCartPrinter(ShoppingCart cart) {
    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        List<Product> products = cart.getProducts();
        for (Product p : products)
            System.out.println(p.name + " - Rs " + p.price);
    }
}

record SaveProductToDB(ShoppingCart cart) {
    public void saveToSql() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to SQL DB."));
        System.out.println();
    }

    public void saveToMongo() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to MongoDB."));
        System.out.println();
    }

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
