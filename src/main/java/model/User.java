package model;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    public User() {
    }
    public User(Integer id, String name) {
        super(id, name);
    }

    public User(String name, String email, String password) {
        super(null, name);
        this.email = email;
        this.password = password;
    }
    public User(Integer id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email='" + email +
                ", password='" + password +
                '}';
    }
}