package service;

import model.User;

public interface UserService {

    User create(User user) ;

    User getByEmailAndPassword(String email, String password);


}
