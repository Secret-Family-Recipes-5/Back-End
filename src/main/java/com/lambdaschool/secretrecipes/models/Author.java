package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;
    private String lastname;
    private String firstname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "author", allowSetters = true)
    private List<Wrote> recipes = new ArrayList<>();

    public Author() {
    }


    public Author(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Wrote> getrecipes() {
        return recipes;
    }

    public void setrecipes(List<Wrote> recipes) {
        this.recipes = recipes;
    }
}