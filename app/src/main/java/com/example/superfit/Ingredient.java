package com.example.superfit;

import java.io.Serializable;

/**
 * Класс, хранящий информацию об ингредиентах
 */
public class Ingredient implements Serializable {

    private String ingredient;

    public Ingredient(String ingredient) {

        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
