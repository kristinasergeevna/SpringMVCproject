package jdbc.Repositories;

import jdbc.Entities.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> findUserByFirstname(String firstname);
}

