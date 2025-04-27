package com.whilmarbitoco.test;

import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.model.Role;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

//        User usr = new User("John", "Doe", "johndoe@gmail.com", "test1234", 1);

        Role role = userRepository.getRole(1);

        System.out.print(role.getRoleID() + " :: " + role.getRoleName());
    }
}
