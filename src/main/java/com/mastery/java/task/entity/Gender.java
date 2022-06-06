package com.mastery.java.task.entity;


public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
