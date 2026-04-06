package com.example.multipledatasource.service;

import com.example.multipledatasource.model.User;

import java.util.List;

public interface UserService {
        void saveUserToPrimary(String name);
        void saveUserToSecondary(String name);
        User getUserFromPrimary(Long id);
        User getUserFromSecondary(Long id);
        List<User> getAllUsersFromPrimary();
        List<User> getAllUsersFromSecondary();
        void batchInsertToPrimary(List<String> names);
        void batchInsertToSecondary(List<String> names);
}
