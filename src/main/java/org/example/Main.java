package org.example;

public class Main {
    public static void main(String[] args) {
        OrderAccountingSystem test = new OrderAccountingSystem();
        test.productsList();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Загрузка и печать списка наименований товаров заказов с заданными id:");

        test.productsListWithId(1);
        test.productsListWithId(2);
        test.productsListWithId(3);

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Регистрация заказа:");
        test.orderRegistration("Matveev Andrey", "+79118395302", "a.matveev91@gmail.com",
                "SPB, str. Uglovaia, d.2, korp.1, kv.222", "3251616", 3);
    }
}