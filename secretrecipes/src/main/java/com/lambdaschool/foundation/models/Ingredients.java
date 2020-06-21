package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    private String ingredientName;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid",
            nullable = false)
    private Recipe recipe;

    public Ingredients(String ingredientName, @NotNull Recipe recipe) {
        this.ingredientName = ingredientName;
        this.recipe = recipe;
    }

    public Ingredients() {
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredientid=" + ingredientid +
                ", ingredientName='" + ingredientName + '\'' +
                '}';
    }
}
