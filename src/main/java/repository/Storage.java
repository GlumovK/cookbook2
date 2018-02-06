package repository;

import sql.SqlHelper;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage {
    private static volatile Storage instance;
    public final SqlHelper sqlHelper;
    String dbUrl = "jdbc:postgresql://localhost:5432/cookbook";
    String dbUser = "user";
    String dbPassword = "password";

    public Storage() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    public SqlHelper getsqlHelper() {
        return sqlHelper;
    }

    public static Storage getInstance() throws SQLException {
        Storage localInstance = instance;
        if (localInstance == null) {
            synchronized (Storage.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Storage();
                }
            }
        }
        return localInstance;
    }
}
