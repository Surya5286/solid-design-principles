package design.principles.srp;

import java.util.ArrayList;
import java.util.List;

class ShopCart {
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
    private final ShopCart cart;

    public ShoppingCartPrinter(ShopCart cart) {
        this.cart = cart;
    }

    public void printInvoice() {
        System.out.println("Shopping Cart Invoice:");
        List<Product> products = cart.getProducts();
        for (Product p : products)
            System.out.println(p.name + " - Rs " + p.price);
    }
}

class SaveProduct {
    private final ShopCart cart;

    public SaveProduct(ShopCart cart) {
        this.cart = cart;
    }

    public void saveToDb() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to database."));
    }
}

public class SRPFollowed {
    public static void main(String[] args) {
        ShopCart cart = new ShopCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        System.out.println("Total price : " + cart.calculateTotal());

        SaveProduct save = new SaveProduct(cart);
        save.saveToDb();
    }
}
