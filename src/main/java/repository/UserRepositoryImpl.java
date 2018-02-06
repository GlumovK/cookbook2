package repository;

import model.User;
import sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    public SqlHelper sqlHelper;

    public UserRepositoryImpl() {
        try {
            sqlHelper = Storage.getInstance().sqlHelper;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            sqlHelper.transactionalExecute(conn -> {
                        try (PreparedStatement ps = conn.prepareStatement(
                                "INSERT INTO users (name, email, password) VALUES (?,?,?)")) {
                            ps.setString(1, user.getName());
                            ps.setString(2, user.getEmail());
                            ps.setString(3, user.getPassword());
                            ps.executeUpdate();
                        }
                        return user;
                    }
            );
        }
        return user;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        return sqlHelper.transactionalExecute(conn -> {
            User user = new User();
            try (PreparedStatement ps = conn.prepareStatement("select id, name, email from users where email=? and password=? limit 1")) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String _email = rs.getString("email");
                    user.setId(id);
                    user.setName(name);
                    user.setName(email);
                }
            }
            return user;
        });
    }
}
