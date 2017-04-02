package main.pl.com.props.vertx.web.rest.order.orderItem;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderItem {

	private static final AtomicInteger COUNTER = new AtomicInteger();

	private final int id;

	private String name;
	private String value;

	public OrderItem(String name, String value) {
		this.id = COUNTER.getAndIncrement();
		this.name = name;
		this.value = value;
	}

	public OrderItem() {
		this.id = COUNTER.getAndIncrement();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

}
