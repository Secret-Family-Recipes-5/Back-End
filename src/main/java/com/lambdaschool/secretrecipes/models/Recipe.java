package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@JsonIgnoreProperties(value = "hasprice")
public class Recipe
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @Column(nullable = false)
    private String title;

    private String source;

    private String ingredients;

    private String instructions;

    private String category;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "recipes",
            allowSetters = true)
    private User user;

    public Recipe() {
    }

    public Recipe(String title, String source, String ingredients, String instructions, String category, @NotNull User user) {
        this.title = title;
        this.source = source;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
        this.user = user;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
