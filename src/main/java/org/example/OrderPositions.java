package org.example;

import java.util.Objects;

public class OrderPositions {
    private Orders orderEntryCode;
    private Products itemNumber;
    private int price;
    private int quantity;

    public OrderPositions(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Products getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Products itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Orders getOrderEntryCode() {
        return orderEntryCode;
    }

    public void setOrderEntryCode(Orders orderEntryCode) {
        this.orderEntryCode = orderEntryCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPositions that = (OrderPositions) o;
        return Objects.equals(orderEntryCode, that.orderEntryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderEntryCode);
    }

    @Override
    public String toString() {
        return "OrderPositions{" +
                "order_entry_code=" + orderEntryCode +
                ", item_number=" + itemNumber +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

