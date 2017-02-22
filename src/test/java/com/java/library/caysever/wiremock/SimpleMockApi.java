package com.java.library.caysever.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.java.library.caysever.model.Product;

public class SimpleMockApi {
    private final String API_URL = "https://caysever-spark.herokuapp.com";
    private WireMockTestClient client;
    private Gson gson;
    private Product product;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    @Before
    public void setup() {
	client = new WireMockTestClient(wireMockRule.port());
	gson = new Gson();

	product = new Product();
	product.setName("ZTE Nubia Z11 MAX");
	product.setPrice(1999.99);
	product.setAvailable(true);
    }

    @Test
    public void mockApi() throws IOException {

	stubFor(get(urlEqualTo(API_URL + "/api/products")).withHeader("Accept", equalTo("text/xml")).willReturn(
		aResponse().withStatus(200).withHeader("Content-Type", "text/xml").withBody(gson.toJson(product))));
	System.out.println(client.get(API_URL + "/api/products").content());
	assertThat(client.get(API_URL + "/api/products").statusCode(), is(200));
    }
}
