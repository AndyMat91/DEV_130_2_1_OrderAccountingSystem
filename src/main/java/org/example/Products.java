package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Products {
    private String articleNumber;
    private String name;
    private String colour;
    private int price;
    private int stockBalance;
    private Set<OrderPositions> orderPositions;

    public Products(String article_number, String name, String colour, int price, int stock_balance) {
        this.articleNumber = article_number;
        this.colour = colour;
        this.name = name;
        this.price = price;
        this.stockBalance = stock_balance;
        orderPositions = new HashSet<>();
    }

    public Set<OrderPositions> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPositions> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(articleNumber, products.articleNumber) && Objects.equals(colour, products.colour) && Objects.equals(name, products.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleNumber, colour, name);
    }

    @Override
    public String toString() {
        return "Products{" +
                "article_number='" + articleNumber + '\'' +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", stock_balance=" + stockBalance +
                '}';
    }

    public void printProductName() {
        System.out.println("Products{name = " + name + (colour.equals("") ? "" : ", colour: " + colour + '}'));
        }
    }
