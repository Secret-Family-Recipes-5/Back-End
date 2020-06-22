package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

    @Column(nullable = false,
            unique = true)
    private String title;

    private String ingredients;

    @Transient
    public boolean hasprice = false;
    private double price;

    private String instructions;
    private String comments;

    @OneToMany(mappedBy = "recipe",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "recipe",
            allowSetters = true)
    private List<CartItem> carts = new ArrayList<>();

    public Recipe()
    {

    }

    public long getrecipeid()
    {
        return recipeid;
    }

    public void setrecipeid(long recipeid)
    {
        this.recipeid = recipeid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        hasprice = true;
        this.price = price;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public List<CartItem> getCarts()
    {
        return carts;
    }

    public void setCarts(List<CartItem> carts)
    {
        this.carts = carts;
    }


    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isHasprice() {
        return hasprice;
    }

    public void setHasprice(boolean hasprice) {
        this.hasprice = hasprice;
    }
}
