package com.miaosha.model;

/**
 * @author luther
 */
public class UserListModel {
    private String name;
    private Integer age;
    private Boolean student;

    public UserListModel(String name, Integer age, Boolean student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }
}
