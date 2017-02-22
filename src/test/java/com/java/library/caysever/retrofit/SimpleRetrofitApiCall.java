package com.java.library.caysever.retrofit;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.java.library.caysever.model.Product;
import com.java.library.caysever.service.ProductService;

public class SimpleRetrofitApiCall {
    private final String API_URL = "https://caysever-spark.herokuapp.com";
    private Retrofit retrofit;
    private ProductService service;

    @Before
    public void setup() {
	retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
	service = retrofit.create(ProductService.class);
    }

    @Test
    public void callProductApi() throws IOException {

	Call<List<Product>> products = service.products();
	Response<List<Product>> response = products.execute();

	System.out.println("Response code : " + response.code() + " and " + response.message());

	for (Product product : response.body()) {
	    System.out.println(product);
	}
    }

}
