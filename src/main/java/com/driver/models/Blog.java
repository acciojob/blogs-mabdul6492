package com.driver.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    int id;
    String title;
    String content;
    Date pubDate;
    @OneToMany(mappedBy = "blog")
    List<Image> imageList;
    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    public Blog() {
    }

    public Blog(int id, String title, String content, Date pubDate, List<Image> imageList, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pubDate = pubDate;
        this.imageList = imageList;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
