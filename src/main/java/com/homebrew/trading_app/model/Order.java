package com.homebrew.trading_app.model;

public class Order {
  Integer orderId;
  String userId;
  String symbol;
  String orderType;
  Integer quantity;
  Double price;
  String status;
  Long creationTs;

  public Order(String orderType, String symbol, Double price, Integer quantity) {
    this.orderType = orderType;
    this.symbol = symbol;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Order{" +
      "symbol='" + symbol + '\'' +
      ", quantity=" + quantity +
      ", price=" + price +
      ", orderType='" + orderType + '\'' +
      '}';
  }

  public Long getCreationTs() {
    return creationTs;
  }

  public void setCreationTs(Long creationTs) {
    this.creationTs = creationTs;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
}
