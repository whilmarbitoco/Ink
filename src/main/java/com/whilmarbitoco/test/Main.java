package com.whilmarbitoco.test;

import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.repository.OrderRepository;

public class Main {

    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();

      orderRepository.getStoreOrder(2).forEach(order -> {
          System.out.println(order.getStatus());
      });
    }
}
