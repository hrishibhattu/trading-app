package com.homebrew.trading_app.dto;

import com.homebrew.trading_app.model.Order;

public class PgClient {
  public Boolean buyOrder(Order order) {
//    return this.client.query("INSERT INTO orders (user_id, symbol, quantity, order_type, price)\n" +
//      "VALUES ('1', 'AAPL', 1000, 'BUY', 241);").execute().succeeded();
    return true;
  }
}
