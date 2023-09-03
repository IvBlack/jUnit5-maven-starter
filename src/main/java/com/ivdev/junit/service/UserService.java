package com.ivdev.junit.service;

import com.ivdev.junit.dto.User.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {

    //опишем эмуляцию БД в виде List<User>
    //в реальном приложении д.б. слой DAO/Repository, кот.обращается к БД и сохраняет там
    private final List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public boolean add(User user) {
        return users.add(user);
    }
}
