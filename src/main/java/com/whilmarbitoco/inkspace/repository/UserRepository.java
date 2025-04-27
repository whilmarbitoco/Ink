package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Role;
import com.whilmarbitoco.inkspace.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository extends Repository<User> {

    private final RoleRepository roleRepository = new RoleRepository();

    public UserRepository() {
        super(User.class);
    }

    public Role getRole(int userID) {
        Optional<User> user = findByID(userID);
        if (user.isEmpty()) throw new RuntimeException("User Not Found");

        Optional<Role> role = roleRepository.findByID(user.get().getRoleID());
        if (role.isEmpty()) throw new RuntimeException(user.get().getRoleID() + "Role ID Not Found");

        return role.get();
    }

    public User getByEmail(String email) {
        List<User> user = findWhere("Email", "=", email);
        return user.isEmpty() ? null : user.getFirst();
    }
}
