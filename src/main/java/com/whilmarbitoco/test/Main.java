package com.whilmarbitoco.test;

import com.whilmarbitoco.inkspace.repository.AddressRepository;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.model.Role;

public class Main {
    public static void main(String[] args) {
        AddressRepository addRepo = new AddressRepository();

        addRepo.getByUser(1).forEach(address ->{
            System.out.println(address.getBarangay());
            System.out.println(address.getLabel());
            System.out.println();
        });
    }
}
