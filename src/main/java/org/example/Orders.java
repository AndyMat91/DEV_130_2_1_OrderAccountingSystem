package org.example;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Orders {
    private int id;
    private Date dateOfCreation;
    private String customerFullName;
    private String contactPhoneNumber;
    private String emailAddress;
    private String deliveryAddress;
    private char deliveryStatus;
    private Date dateShipmentOrder;
    private Set<OrderPositions> orderPositions;

    public Orders(int id, Date date_of_creation, String customer_full_name, String contact_phone_number, String email_address, String delivery_address, char delivery_status, Date date_shipment_order) {
        this.id = id;
        this.dateOfCreation = date_of_creation;
        this.customerFullName = customer_full_name;
        this.contactPhoneNumber = contact_phone_number;
        this.emailAddress = email_address;
        this.deliveryAddress = delivery_address;
        this.deliveryStatus = delivery_status;
        this.dateShipmentOrder = date_shipment_order;
        orderPositions = new HashSet<>();
    }

    public Set<OrderPositions> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPositions> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateShipmentOrder() {
        return dateShipmentOrder;
    }

    public void setDateShipmentOrder(Date dateShipmentOrder) {
        this.dateShipmentOrder = dateShipmentOrder;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public char getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(char deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && Objects.equals(customerFullName, orders.customerFullName) && Objects.equals(dateOfCreation, orders.dateOfCreation) && Objects.equals(dateShipmentOrder, orders.dateShipmentOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerFullName, dateOfCreation, dateShipmentOrder);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date_of_creation=" + dateOfCreation +
                ", customer_full_name='" + customerFullName + '\'' +
                ", contact_phone_number='" + contactPhoneNumber + '\'' +
                ", email_address='" + emailAddress + '\'' +
                ", delivery_address='" + deliveryAddress + '\'' +
                ", delivery_status=" + deliveryStatus +
                ", date_shipment_order=" + dateShipmentOrder +
                '}';
    }
}
