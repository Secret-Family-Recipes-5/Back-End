package com.lambdaschool.secretrecipes.models;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "recipes")
public class Recipe extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;
    private String recipetitle;
    private String source;
    private int copy;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "recipes",
            allowSetters = true)
    private User user;

    public Recipe() {

    }

    public Recipe(String recipetitle, @NotNull User user) {
        this.recipetitle = recipetitle;
        this.user = user;
    }

    public Recipe(User user, String recipetitle, String source, int copy) {
        this.user = user;
        this.recipetitle = recipetitle;
        this.source = source;
        this.copy = copy;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getRecipetitle() {
        return recipetitle;
    }

    public void setRecipetitle(String recipetitle) {
        this.recipetitle = recipetitle;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
