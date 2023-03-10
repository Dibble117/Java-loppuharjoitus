package com.nikooy.userdemo;


public class User {
    private String name;
    private int id;
    private static int idCounter = 0;
    private String course;

// autoincrement new user id
    public User(int id, String name, String course) {
        this.name = name;
        this.id = idCounter++;
        this.course = course;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
