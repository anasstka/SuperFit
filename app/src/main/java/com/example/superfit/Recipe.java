package com.example.superfit;

import java.net.URL;
import java.util.ArrayList;

public class Recipe {

    private String name;
    private String image;
    private ArrayList<String> dietLabels;
    private int kcal;
    private int protein;
    private int fat;
    private int carbs;
    private ArrayList<String> ingredients;

    public Recipe(String name, String image, ArrayList<String> dietLabels, int kcal, int protein, int fat, int carbs, ArrayList<String> ingredients) {
        this.name = name;
        this.image = image;
        this.dietLabels = dietLabels;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(ArrayList<String> dietLabels) {
        this.dietLabels = dietLabels;
    }
}
