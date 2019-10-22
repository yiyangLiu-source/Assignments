package com.assignment.order.entity;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Food implements Serializable {
    private int picture;
    private String name;
    private String description;
    private float cost;

    private int count = 0;

    public Food(int picture, String name, String description, float cost) {
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Food)) {
            return false;
        }
        return name.equals(((Food) obj).name);
    }
}
