//package com.lambdaschool.secretrecipes.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Entity
//@Table(name = "sections")
//public class Section extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long sectionid;
//
//    private String sectionname;
//
//    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("section")
//    private List<Recipe> recipesInSection = new ArrayList<>();
//
//    public Section() {
//    }
//
//    public Section(String sectionname, List<Recipe> recipesInSection) {
//        this.sectionname = sectionname;
//        this.recipesInSection = recipesInSection;
//    }
//
//    public Section(String sectionname) {
//        this.sectionname = sectionname;
//    }
//
//    public long getSectionid() {
//        return sectionid;
//    }
//
//    public void setSectionid(long sectionid) {
//        this.sectionid = sectionid;
//    }
//
//    public String getSectionname() {
//        return sectionname;
//    }
//
//    public void setSectionname(String sectionname) {
//        this.sectionname = sectionname;
//    }
//
//    public List<Recipe> getrecipesInSection() {
//        return recipesInSection;
//    }
//
//    public void setrecipesInSection(List<Recipe> recipesInSection) {
//        this.recipesInSection = recipesInSection;
//    }
//}