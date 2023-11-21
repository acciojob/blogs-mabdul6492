package com.driver.models;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue
    int id;
    String description;
    String dimentions;
    @ManyToOne(cascade = CascadeType.ALL)
    Blog blog;

    public Image() {
    }

    public Image(int id, String description, String dimentions, Blog blog) {
        this.id = id;
        this.description = description;
        this.dimentions = dimentions;
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimentions() {
        return dimentions;
    }

    public void setDimentions(String dimentions) {
        this.dimentions = dimentions;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}