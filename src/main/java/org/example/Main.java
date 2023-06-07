package org.example;

public class Main {
    public static void main(String[] args) {

           Products test1 = new Products();
           Products test2 = new Products();

        System.out.println("Загрузите список продуктов и выведите его на экран:");
        test1.loadingProducts();
        test1.printAll();
        System.out.println("--------------------------------------------------------------------------");

        System.out.println("Загрузка и печать списка наименований товаров заказов с заданными id:");
        // тут печать я сделал как прямиком в методе загрузки, так и отдельным методом....
        test2.loadingProductsWithId(1);
        test2.loadingProductsWithId(2);
        test2.loadingProductsWithId(3);
        test2.printProductName();
        System.out.println("--------------------------------------------------------------------------");

        System.out.println("Регистрация заказа:");
        test1.orderRegistration("Matveev Andrey", "+79118395302", "a.matveev91@gmail.com",
                "SPB, str. Uglovaia, d.2, korp.1, kv.222", "3251616", 3);
    }
}