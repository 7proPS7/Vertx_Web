package main.pl.com.props.vertx.web.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import main.pl.com.props.vertx.web.rest.order.orderItem.OrderItem;

public class RestVertxStart extends AbstractVerticle {

	private Map<Integer, OrderItem> items = new LinkedHashMap<>();

	private void createItems() {
		OrderItem shirtA = new OrderItem("Shirt_A", "99.99");
		items.put(shirtA.getId(), shirtA);

		OrderItem shirtB = new OrderItem("Shirt_B", "79.99");
		items.put(shirtB.getId(), shirtB);

	}

	@Override
	public void start(Future<Void> future) throws Exception {

		createItems();

		Router router = Router.router(vertx);

		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h2>Web vertx app</h2>");
		});

		router.route("/assets/*").handler(StaticHandler.create("assets"));

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8080),
				result -> {
					if (result.succeeded()) {
						future.complete();
					} else {
						future.fail(result.cause());
					}
				});
	}

}
