package service;


import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import static util.ValidationUtil.checkNotFound;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl() {
        this.repository = new UserRepositoryImpl();
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User getByEmailAndPassword(String email, String password){
        return checkNotFound(repository.getByEmailAndPassword(email , password), "email=" + email + ", password=" + password);
    }

}
