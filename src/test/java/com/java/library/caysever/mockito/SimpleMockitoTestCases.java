package com.java.library.caysever.mockito;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.java.library.caysever.model.Product;
import com.java.library.caysever.service.ProductService;

public class SimpleMockitoTestCases {
    
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

	Call<List<Product>> productCall = service.products();
	Response<List<Product>> response = productCall.execute();

	System.out.println("Response code : " + response.code() + " and " + response.message());
	
	List<Product> products = response.body();
	
	Product product = products.get(0);
	assertNotEquals(null, product);
	
	List<Product> spy = spy(products);
	
	doReturn(product).when(spy).get(0);

	when(spy.get(0)).thenReturn(product);
	//when(products.get(1)).thenThrow(new RuntimeException("Products size must be one!"));
	
	
	product = products.get(0);
	System.out.println(product);
	
	try{
	    product = products.get(1);
	}catch(RuntimeException rte){
	    System.out.println("Products throwed RuntimeException!");
	}catch(Exception e){
	    e.printStackTrace();
	}
	
    }


}
