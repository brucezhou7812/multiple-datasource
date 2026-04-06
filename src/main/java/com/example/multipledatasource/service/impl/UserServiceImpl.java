package com.example.multipledatasource.service.impl;

import com.example.multipledatasource.common.DS;
import com.example.multipledatasource.common.DataSourceType;
import com.example.multipledatasource.dao.UserRepository;
import com.example.multipledatasource.model.User;
import com.example.multipledatasource.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @DS(DataSourceType.PRIMARY)
    public void saveUserToPrimary(String name) {
        User user = new User();
        user.setName(name);
        this.userRepository.save(user);
    }

    @Override
    @DS(DataSourceType.SECONDARY)
    public void saveUserToSecondary(String name) {
        User user = new User();
        user.setName(name);
        this.userRepository.save(user);
    }

    @Override
    @DS(DataSourceType.PRIMARY)
    public User getUserFromPrimary(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    @DS(DataSourceType.SECONDARY)
    public User getUserFromSecondary(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    @DS(DataSourceType.PRIMARY)
    public List<User> getAllUsersFromPrimary() {
        return this.userRepository.findAll();
    }

    @Override
    @DS(DataSourceType.SECONDARY)
    public List<User> getAllUsersFromSecondary() {
        return this.userRepository.findAll();
    }

    @Override
    @DS(DataSourceType.PRIMARY)
    public void batchInsertToPrimary(List<String> names) {
        List<User> users = names.stream().map(name -> {
            User user = new User();
            user.setName(name);
            return user;
        }).toList();
        this.userRepository.saveAll(users);
    }

    @Override
    @DS(DataSourceType.SECONDARY)
    public void batchInsertToSecondary(List<String> names) {
        List<User> users = names.stream().map(name -> {
            User user = new User();
            user.setName(name);
            return user;
        }).toList();
        this.userRepository.saveAll(users);
    }
}
