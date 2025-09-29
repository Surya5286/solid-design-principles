package design.principles.ocp;

class SaveProductToSqlDB implements SaveProduct {
    private final ShoppingCart cart;

    public SaveProductToSqlDB(ShoppingCart cart) {
        this.cart = cart;
    }

    public void saveToDb() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to SQL database."));
    }
}

class SaveProductToNoSqlDB implements SaveProduct {
    private final ShoppingCart cart;

    public SaveProductToNoSqlDB(ShoppingCart cart) {
        this.cart = cart;
    }

    public void saveToDb() {
        cart.getProducts().forEach(p ->
                System.out.println("Product : " + p.name + " with price: " + p.price + " saved to NoSQL database."));
    }
}

class SaveProductToFile implements SaveProduct {
    private final ShoppingCart cart;

    public SaveProductToFile(ShoppingCart cart) {
        this.cart = cart;
    }

    public void saveToFile() {
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
        saveToSql.saveToDb();
        System.out.println();

        // Save to NoSQL DB
        SaveProductToNoSqlDB saveToNoSql = new SaveProductToNoSqlDB(cart);
        saveToNoSql.saveToDb();
        System.out.println();

        // Save to File
        SaveProductToFile saveToFile = new SaveProductToFile(cart);
        saveToFile.saveToFile();
    }
}

