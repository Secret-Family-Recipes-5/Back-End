package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "wrotes", uniqueConstraints = {@UniqueConstraint(columnNames = {"authorid", "recipeid"})})
public class Wrote extends Auditable implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Author author;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "authors", allowSetters = true)
    private Recipe recipe;

    public Wrote() {
    }

    public Wrote(Author author, Recipe recipe) {
        this.author = author;
        this.recipe = recipe;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Recipe getrecipe() {
        return recipe;
    }

    public void setrecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrote wrote = (Wrote) o;
        return Objects.equals(author, wrote.author) &&
                Objects.equals(recipe, wrote.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, recipe);
    }
}
