package com.kaab.crud_app.service;

import com.kaab.crud_app.entity.User;
import com.kaab.crud_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createSingleUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public Boolean existUserById(Integer userId){
        return userRepository.existsById(userId);
    }
    public Optional<User> findUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> deleteUserById(Integer userId) {
        Optional<User> deletedUser = findUserById(userId);
        userRepository.deleteById(userId);
        return deletedUser;
    }

    public User updateUserById(Integer userId, User updatedUser) {
        return userRepository.save(updatedUser);
    }
}
