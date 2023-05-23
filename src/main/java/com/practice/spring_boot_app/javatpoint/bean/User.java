package com.practice.spring_boot_app.javatpoint.bean;


import java.util.Date;

public class User {

    private Integer id;
    private String name;
    private Date dob;

    public User(Integer id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return String.format("User [id = %d, name = %s, DOB = %s]", id, name, dob);
    }
}
