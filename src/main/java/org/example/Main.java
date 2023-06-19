package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Repository repository = new Repository();
        List <Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(4);

        System.out.println("Загрузите список продуктов и выведите его на экран:");
        System.out.println("Table: products");
        repository.findAllProducts().forEach(System.out::println);
        System.out.println();
        System.out.println("Table: orders");
        repository.findAllOrders().forEach(System.out::println);
        System.out.println();
        System.out.println("Table: order_positions");
        repository.findAllOrderPositions().forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------");

        System.out.println("Загрузка и печать списка наименований товаров заказов с заданными id:");
        repository.findAllProductsById(6).forEach(Products::printProductName);
        list.forEach(e -> {
            try {
                repository.findAllProductsById(e).forEach(Products::printProductName);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        System.out.println("--------------------------------------------------------------------------");

        System.out.println("Регистрация заказа:");
        repository.orderRegistration("Matveev Andrey", "+79118395302", "a.matveev91@gmail.com",
                "SPB, str. Uglovaia, d.2, korp.1, kv.222", "3251616", 3);
    }
}