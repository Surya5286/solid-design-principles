package design.principles.DIP.violated;

record SaveToSQLDatabase() { // Low-level module
    public void save(String data) {
        System.out.println(
                "Executing SQL Query: INSERT INTO users VALUES('"
                        + data + "');"
        );
    }
}

record SaveToMongoDB() { // Low-level module
    public void save(String data) {
        System.out.println(
                "Executing MongoDB Function: db.users.insert({name: '"
                        + data + "'})"
        );
    }
}

class UserService { // High-level module (Tightly coupled)
    private final SaveToSQLDatabase sqlDatabase;
    private final SaveToMongoDB mongoDB;

    public UserService() {
        this.sqlDatabase = new SaveToSQLDatabase();
        this.mongoDB = new SaveToMongoDB();
    }

    public void saveUserDataToSQL(String str) {
        sqlDatabase.save(str);
    }

    public void saveUserDataToMongoDB(String str) {
        mongoDB.save(str);
    }
}

public class DIPViolated {
    public static void main(String[] args) {
        UserService user = new UserService();
        user.saveUserDataToSQL("Surya");
        user.saveUserDataToMongoDB("Kalyan");
    }
}
