package repository;
import model.User;

public interface UserRepository {

    User save(User user);

    User getByEmailAndPassword(String email, String password);

}