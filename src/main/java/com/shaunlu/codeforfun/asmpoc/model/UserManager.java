package com.shaunlu.codeforfun.asmpoc.model;

import java.util.HashMap;

public class UserManager {

    private HashMap<String, User> users;

    public UserManager(){
        users = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getUsername(), user);
    }

    public void removeUser(User user){
        users.remove(user.getUsername());
    }

}
