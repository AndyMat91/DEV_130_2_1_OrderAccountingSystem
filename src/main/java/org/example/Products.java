package org.example;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Products {
    public Map<Integer, String> article_number = new HashMap<>();
    public Map<Integer, String> name = new HashMap<>();
    public Map<Integer, String> colour = new HashMap<>();
    public Map<Integer, Integer> price = new HashMap<>();
    public Map<Integer, Integer> stock_balance = new HashMap<>();
    int key = 1;

    public void loadingProducts (){

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/order_accounting_system",
                "root", "password");
             Statement s = conn.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                article_number.put(key, rs.getString(1));
                name.put(key, rs.getString(2));
                colour.put(key, rs.getString(3));
                price.put(key, rs.getInt(4));
                stock_balance.put(key, rs.getInt(5));
                key ++;
            }
        } catch (SQLException e) {
            System.out.println("Exception e: " + e.getMessage());
        }
    }

    public void loadingProductsWithId (int id){
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/order_accounting_system",
                "root", "password");
             Statement s = conn.createStatement()) {
            ResultSet rs = s.executeQuery("select order_entry_code, name, colour from order_accounting_system.products left join order_accounting_system.order_positions\n" +
                    "on order_accounting_system.products.article_number = order_accounting_system.order_positions.item_number\n" +
                    "where order_accounting_system.order_positions.order_entry_code = " + id);
            while (rs.next()) {
                name.put(key, rs.getString(2));
                colour.put(key, rs.getString(3));
                System.out.println("order_entry_code: " + rs.getString(1) +
                        ", name: " + rs.getString(2)
                        + ((rs.getString(3).equals("")) ? "" : ", colour: " + rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("Exception e: " + e.getMessage());
        }
    }

    public void orderRegistration (String fullName, String phoneNumber, String email, String deliveryAddress, String article, int quantity){

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/order_accounting_system",
                "root", "password");
             Statement s = conn.createStatement()) {
            int max = 0;
            int price = 0;
            ResultSet maxId = s.executeQuery("select max(id)+1 from order_accounting_system.orders");
            while (maxId.next()){
                max = maxId.getInt(1);
            }

            ResultSet p = s.executeQuery("select price from order_accounting_system.products where article_number = " + article);
            while (p.next()){
                price = p.getInt(1);
            }

            s.executeUpdate("insert into order_accounting_system.orders values\n" +
                    " (" + max + ", curdate(), '" + fullName +"', '"+ phoneNumber +"', '"+email+"', '"+deliveryAddress+"', 'P', null)");

            s.executeUpdate("insert into order_accounting_system.order_positions values\n" +
                    " (" + max + ", '"+article+"', "+ price + ", " + quantity +")");

            System.out.println("Ваш заказ успешно зарегистрирован!");
        } catch (SQLException e) {
            System.out.println("Exception e: " + e.getMessage());
        }
    }



    public void printAll() {
        for (int k = 1; k != article_number.size() + 1; k++) {
            System.out.println(
                    "article_number = " + article_number.get(k) +
                    ", name = " + name.get(k) +
                    ", colour = " + colour.get(k) +
                    ", price = " + price.get(k) +
                    ", stock_balance = " + stock_balance.get(k)
            );
        }
    }


}
