package com.homebrew.trading_app.service;

import com.homebrew.trading_app.dto.PgClient;
import com.homebrew.trading_app.model.Order;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class BuyService {
  public static void buyAction(RoutingContext routingContext) {
    JsonObject body = routingContext.body().asJsonObject();
    final String symbol = body.getString("symbol");
    final Integer quantity = body.getInteger("quantity");
    final Double price = body.getDouble("price");

    Order order = new Order("BUY", symbol, price, quantity);

    System.out.println(order);
    PgClient client = new PgClient();
    System.out.println(client.buyOrder(order));
    routingContext.response().setStatusCode(200);
    routingContext.end();
  }
}
