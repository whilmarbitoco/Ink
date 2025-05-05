package com.whilmarbitoco.test;

import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.repository.ReviewRepository;

public class Main {

    public static void main(String[] args) {
        ReviewRepository rr = new ReviewRepository();

        rr.findAll().forEach(e -> {
            System.out.println(e.getCreated().getMonth() + " " + e.getCreated().getYear());
            System.out.println(e.getComment());
        });
    }
}
