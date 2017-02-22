package com.java.library.caysever.okhttp;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class SimplePostRequest {

    private final String API_URL = "https://caysever-spark.herokuapp.com/api/products";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private List<String> categoires;
    private Gson gson;

    @Before
    public void setup() {
	client = new OkHttpClient();
	
	gson = new Gson();
	
	categoires = new LinkedList<String>();

	categoires.add("ZTE");
	categoires.add("PHONE");
    }

    @Test
    public void postRequest() throws IOException {

	Product product = new Product();
	product.setName("ZTE Nubia Z11 MAX");
	product.setPrice(1999.99);
	product.setAvailable(true);
	product.setCategories(categoires);

	RequestBody body = RequestBody.create(JSON, gson.toJson(product));
	Request request = new Request.Builder().url(API_URL).post(body).build();
	Response response = client.newCall(request).execute();
	
	if(response.code() == 200){
	    System.out.println("Everytings fine!");
	}

	System.out.println(response.toString());
    }

    @After
    public void shutdown() {

    }

    class Product {

	private int id;
	private String name;
	private List<String> categories = new LinkedList<String>();
	private double price;
	private boolean available;

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public List<String> getCategories() {
	    return categories;
	}

	public void setCategories(List<String> categories) {
	    this.categories = categories;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}

	public boolean isAvailable() {
	    return available;
	}

	public void setAvailable(boolean available) {
	    this.available = available;
	}

	@Override
	public String toString() {
	    StringBuffer asString = new StringBuffer();
	    asString.append("id=").append(id).append(", ");
	    asString.append("name=").append(name).append(", ");
	    asString.append("price=").append(price).append(", ");
	    asString.append("available=").append(available).append(", ");
	    asString.append("categories=").append(Arrays.asList(categories));

	    return asString.toString();
	}

	public boolean isValid() {
	    return name != null && price >= 0.1 && categories.size() > 0;
	}

    }

}
