package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Post {

    public Post(double title, boolean anons, String full_text, Date dateAnons, int countReaders) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.dateAnons = dateAnons;
        this.countReaders = countReaders;
    }

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String  full_text;
    private double title;
    private boolean anons;
    private Date dateAnons;
    private int countReaders;
    private int views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTitle() {
        return title;
    }

    public void setTitle(double title) {
        this.title = title;
    }

    public boolean getAnons() {
        return anons;
    }

    public void setAnons(boolean anons) {
        this.anons = anons;
    }

    public String getFull_text() {return full_text;}

    public void setFull_text(String full_text) {this.full_text = full_text;}

    public Date getDateAnons() {
        return dateAnons;
    }

    public void setDateAnons(Date dateAnons) {
        this.dateAnons = dateAnons;
    }

    public int getCountReaders() {
        return countReaders;
    }

    public void setCountReaders(int countReaders) {
        this.countReaders = countReaders;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


}
