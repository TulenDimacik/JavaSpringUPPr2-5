package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

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
    @NotBlank(message = "Please enter name")
    @Size(min=4, message = "Name should be atleast 4 characters")
    @Size(max=10, message = "Name should not be greater than 10 characters")
    private String  full_text;
    @NotNull(message = "Please enter salary")
    @Min(value=1000, message = "Salary must be atleast 1000.00")
    @Max(value=10000, message = "Salary should not be greater than 10000.00")
    private double title;
    private boolean anons;
    @NotNull(message = "Заполните дату")
    private Date dateAnons;
    @Min(value = 1, message = "Не меньше 1")
    @Max(value = 10, message = "Не больше 10")
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
