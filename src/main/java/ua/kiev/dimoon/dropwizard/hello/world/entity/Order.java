package ua.kiev.dimoon.dropwizard.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Order {

    private Integer orderId;
    private String shipName;
    private String shipAddress;
    private LocalDate orderDate;

    public Integer getOrderId() {
        return orderId;
    }

    public Order setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getShipName() {
        return shipName;
    }

    public Order setShipName(String shipName) {
        this.shipName = shipName;
        return this;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public Order setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }
}
