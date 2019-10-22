package com.assignment.order.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private List<Food> foods;

    public Order() {
        this.foods = new ArrayList<>();
    }

    public String cost() {
        float cost = 0;
        for (Food food : foods) {
            cost += food.getCost() * food.getCount();
        }
        return cost + "";
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
