package design.principles.ocp;

interface SaveProduct {
    void save();
}

// Follows OCP as we can add new storage classes without modifying existing ones.
record SaveProductToSqlDB(ShoppingCart cart) implements SaveProduct {
    @Override
    public void save() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to SQL database."));
    }
}

// Follows OCP as we can add new storage classes without modifying existing ones.
record SaveProductToNoSqlDB(ShoppingCart cart) implements SaveProduct {
    @Override
    public void save() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to NoSQL database."));
    }
}

// Follows OCP as we can add new storage classes without modifying existing ones.
record SaveProductToFile(ShoppingCart cart) implements SaveProduct {
    @Override
    public void save() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to file."));
    }
}

public class OCPFollowed {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        // Print Invoice
        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();
        System.out.println("Total: Rs " + cart.calculateTotal());

        System.out.println("\n--- Saving Products ---");
        // Save to SQL DB
        SaveProductToSqlDB saveToSql = new SaveProductToSqlDB(cart);
        saveToSql.save();
        System.out.println();

        // Save to NoSQL DB
        SaveProductToNoSqlDB saveToNoSql = new SaveProductToNoSqlDB(cart);
        saveToNoSql.save();
        System.out.println();

        // Save to File
        SaveProductToFile saveToFile = new SaveProductToFile(cart);
        saveToFile.save();
    }
}