package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Введите фамилию")
    @Size(min=2, message = "Фамилия не может быть меньше 2 букв")
    private String  lastName;
    @NotNull(message = "Заполните рост")
    @Min(value = 50, message = "Не меньше 50")
    @Max(value = 300, message = "Не больше 300")
    private float height;
    private boolean gender;
    @NotNull(message = "Заполните дату")
    private Date birthday;
    @NotNull(message = "Заполните вес")
    @Min(value = 20, message = "Не меньше 20")
    @Max(value = 300, message = "Не больше 300")
    private double weight;
    private int views;

    public Human(String lastName, float height, boolean gender, Date birthday, double weight) {
        this.lastName = lastName;
        this.height = height;
        this.gender = gender;
        this.birthday = birthday;
        this.weight = weight;
    }

    public Human() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
