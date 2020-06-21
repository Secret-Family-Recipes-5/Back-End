package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    private String source;

    @OneToMany(mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe",
            allowSetters = true)
    private List<Ingredients> recipeIngredients = new ArrayList<>();

    private String instructions;

    private int category;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "useremails",
            allowSetters = true)
    private User user;

    public Recipe(String source, List<Ingredients> recipeIngredients, String instructions, int category, @NotNull User user) {
        this.source = source;
        this.recipeIngredients = recipeIngredients;
        this.instructions = instructions;
        this.category = category;
        this.user = user;
    }

    public Recipe() {
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Ingredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<Ingredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeid=" + recipeid +
                ", source='" + source + '\'' +
                ", recipeIngredients=" + recipeIngredients +
                ", instructions='" + instructions + '\'' +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
