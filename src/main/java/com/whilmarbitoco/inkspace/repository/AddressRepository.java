package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Address;
import com.whilmarbitoco.inkspace.model.User;

import java.util.List;
import java.util.Optional;

public class AddressRepository extends BaseRepository<Address> {

    private final UserRepository userRepository = new UserRepository();

    public AddressRepository() {
        super(Address.class);
    }

    public List<Address> getByUser(int userID) {
        Optional<User> user = userRepository.findByID(userID);
        if (user.isEmpty()) return List.of();

        return  findWhere("UserID", "=", userID + " LIMIT 2");
    }
}
