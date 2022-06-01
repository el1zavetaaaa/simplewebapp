package com.mastery.java.task.dto.model;

public enum Gender {
    MALE(0),
    FEMALE(1);

    private int id;

    Gender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Gender getGenderById(int id) {
        if (id == 0) {
            return MALE;
        } else return FEMALE;
    }

}
