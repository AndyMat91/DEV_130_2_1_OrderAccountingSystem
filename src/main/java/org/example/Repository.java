package org.example;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Repository {
    public Set<Products> findAllProducts() throws SQLException {
        List<Products> products = new LinkedList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                products.add(getAllProductsWithResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return new HashSet<>(products);
    }

    public Set<Orders> findAllOrders() throws SQLException {
        List<Orders> orders = new LinkedList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                orders.add(getAllOrdersWithResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return new HashSet<>(orders);
    }

    public Set<OrderPositions> findAllOrderPositions() throws SQLException {
        List<OrderPositions> orderPositions = new LinkedList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM order_positions");
            while (rs.next()) {
                OrderPositions orderPosition = getOrderPositionsWithResultSet(rs);
                orderPositions.add(orderPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return new HashSet<>(orderPositions);
    }

    public Set<Products> findAllProductsById(int id) throws SQLException {
        List<Products> products = new LinkedList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select order_accounting_system.products.article_number, name, colour, order_accounting_system.products.price, stock_balance from order_accounting_system.products right join order_accounting_system.order_positions \n" +
                    "on article_number = item_number\n" +
                    "right join order_accounting_system.orders  \n" +
                    "on order_entry_code = id \n" +
                    "where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(getAllProductsWithResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Exception e: " + e.getMessage());
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return new HashSet<>(products);
    }

    public void orderRegistration(String fullName, String phoneNumber, String email, String deliveryAddress, String article, int quantity) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement s = connection.createStatement();
            int max = 0;
            int price = 0;
            ResultSet maxId = s.executeQuery("select max(id)+1 from order_accounting_system.orders");
            while (maxId.next()) {
                max = maxId.getInt(1);
            }

            ResultSet p = s.executeQuery("select price from order_accounting_system.products where article_number = " + article);
            while (p.next()) {
                price = p.getInt(1);
            }

            s.executeUpdate("insert into order_accounting_system.orders values\n" +
                    " (" + max + ", curdate(), '" + fullName + "', '" + phoneNumber + "', '" + email + "', '" + deliveryAddress + "', 'P', null)");

            s.executeUpdate("insert into order_accounting_system.order_positions values\n" +
                    " (" + max + ", '" + article + "', " + price + ", " + quantity + ")");

            System.out.println("Ваш заказ успешно зарегистрирован!");
        } catch (SQLException e) {
            System.out.println("Заказ зарегистрировать не удалось!" + "\n" + "Exception e: " + e.getMessage());
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
    }

    public Products getProductByArticleNumber(String articleNumber) throws SQLException {
        Products product = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from products where article_number=?");
            statement.setString(1, articleNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = getAllProductsWithResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return product;
    }

    public Orders getOrderById(int id) throws SQLException {
        Orders order = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from orders where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = getAllOrdersWithResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) ConnectionPool.getInstance().resiveConnection(connection);
        }
        return order;
    }

    private Products getAllProductsWithResultSet(ResultSet resultSet) throws SQLException {
        Products product = null;
        String article_number = resultSet.getString(1);
        String name = resultSet.getString(2);
        String colour = resultSet.getString(3);
        int price = resultSet.getInt(4);
        int stock_balance = resultSet.getInt(5);
        product = new Products(article_number, name, colour, price, stock_balance);
        return product;
    }

    private Orders getAllOrdersWithResultSet(ResultSet resultSet) throws SQLException {
        Orders order = null;
        int id = resultSet.getInt(1);
        Date date_of_creation = resultSet.getDate(2);
        String customer_full_name = resultSet.getString(3);
        String contact_phone_number = resultSet.getString(4);
        String email_address = resultSet.getString(5);
        String delivery_address = resultSet.getString(6);
        char delivery_status = resultSet.getString(7).charAt(0);
        Date date_shipment_order = resultSet.getDate(8);
        order = new Orders(id, date_of_creation, customer_full_name, contact_phone_number, email_address, delivery_address, delivery_status, date_shipment_order);
        return order;
    }

    private OrderPositions getOrderPositionsWithResultSet(ResultSet resultSet) throws SQLException {
        Orders orderEntryCode = getOrderById(resultSet.getInt(1));
        Products itemNumber = getProductByArticleNumber(resultSet.getString(2));
        int price = resultSet.getInt(3);
        int quantity = resultSet.getInt(4);
        OrderPositions orderPosition = new OrderPositions(price, quantity);
        if (itemNumber != null) {
            orderPosition.setItemNumber(itemNumber);
            itemNumber.getOrderPositions().add(orderPosition);
        }
        if (orderEntryCode != null) {
            orderPosition.setOrderEntryCode(orderEntryCode);
            orderEntryCode.getOrderPositions().add(orderPosition);
        }
        return orderPosition;
    }
}
