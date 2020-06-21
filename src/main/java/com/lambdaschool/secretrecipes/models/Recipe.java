package com.lambdaschool.secretrecipes.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;
    private String recipetitle;
    private String source;
    private int copy;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Wrote> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("recipesInSection")
    private Section section;

    public Recipe() {

    }

    public Recipe(String recipetitle, String source, int copy) {
        this.recipetitle = recipetitle;
        this.source = source;
        this.copy = copy;
    }

    public Recipe(String recipetitle, String source, int copy, Section section) {
        this.recipetitle = recipetitle;
        this.source = source;
        this.copy = copy;
        this.section = section;
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

    public List<Wrote> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Wrote> authors) {
        this.authors = authors;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setWrotes(List<Wrote> wrotes)
    {
        this.authors = wrotes;

        for (Wrote wrote : this.authors)
            wrote.setrecipe(this);
    }
}
