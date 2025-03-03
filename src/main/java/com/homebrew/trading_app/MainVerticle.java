package com.homebrew.trading_app;

import com.homebrew.trading_app.service.BuyService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;

public class MainVerticle extends AbstractVerticle {

  SqlClient client;

  public void hello() {
    PgConnectOptions connectOptions = new PgConnectOptions()
      .setPort(5432)
      .setHost("localhost")
      .setDatabase("postgres")
      .setUser("postgres")
      .setPassword("password");

    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);


    this.client = PgBuilder.client().with(poolOptions).using(vertx).connectingTo(connectOptions).build();
    this.client.query("INSERT INTO orders (user_id, symbol, quantity, order_type, price)\n" +
      "VALUES ('1', 'AAPL', 1000, 'BUY', 241);").execute().succeeded();

  }


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);

    hello();
    router.route("/hello").handler(context -> {
      final String address = context.request().connection().remoteAddress().toString();
      MultiMap queryParams = context.queryParams();
      String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
      context.json(
        new JsonObject()
          .put("name", name)
          .put("address", address)
          .put("message", "Hello " + name + " connected from " + address)
      );
    });

    router.route().handler(BodyHandler.create());

    // buy
    router.post("/buy").handler(BuyService::buyAction);

    // sell
    router.post("/sell").handler(BuyService::buyAction);

    // list portfolio
    router.post("/portfolio").handler(BuyService::buyAction);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8888)
      .onSuccess(server -> {
        System.out.println("HTTP server started on port " + server.actualPort());
        startPromise.complete();
      })
      .onFailure(throwable -> {
        throwable.printStackTrace();
        startPromise.fail(throwable);
      });
  }
}
