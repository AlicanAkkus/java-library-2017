package com.java.library.caysever.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import com.java.library.caysever.model.Product;

public interface ProductService {
    @GET("/api/products")
    Call<List<Product>> products();
}