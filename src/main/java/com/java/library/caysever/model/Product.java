package com.java.library.caysever.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<String> categories = new LinkedList<String>();
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
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