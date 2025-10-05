package design.principles.DIP.followed;

import java.util.List;

interface DataBase {
    void save(String data);
}

record SaveToSqlDB() implements DataBase {
    @Override
    public void save(String data) {
        System.out.println(
                "Executing SQL Query: INSERT INTO users VALUES('"
                        + data + "');"
        );
    }
}

record SaveToMongoDB() implements DataBase {
    @Override
    public void save(String data) {
        System.out.println(
                "Executing MongoDB Function: db.users.insert({name: '"
                        + data + "'})"
        );
    }
}

record UserService(DataBase dataBase) {
    public void registerUser(String name) {
        dataBase.save(name); // Dependency Injection
    }
}

public class DIPFollowed {
    public static void main(String[] args) {
        List<DataBase> dbList = List.of(
                new SaveToSqlDB(),
                new SaveToMongoDB()
        );

        dbList.forEach(db -> new UserService(db).registerUser("Surya Kalyan"));
    }
}
