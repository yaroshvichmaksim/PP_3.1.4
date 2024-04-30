package ru.kata.spring.boot_security.demo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.roles where u.userName=:name")
    User findByUserName(String name);

//    void addUser(User user);
//
//    void editUser(User user);
//
//    void deleteUser(int id);
//
//    User getUser(int id);
//
//    List<User> getAllUsers();

}
