package com.java.library.caysever.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleGetRequest {

    private final String API_URL = "https://caysever-spark.herokuapp.com/api/products";
    private OkHttpClient client;

    @Before
    public void setup() {
	client = new OkHttpClient();
    }

    @Test
    public void getRequest() throws IOException {
	Request request = new Request.Builder().url(API_URL).build();

	Response response = client.newCall(request).execute();
	System.out.println(response.body().string());
    }
    
    @After
    public void shutdown(){
	
    }
}
