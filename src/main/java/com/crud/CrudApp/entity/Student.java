package com.crud.CrudApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="STUDENTS")
public class Student{
    @Id
    Integer stId;

    String firstName;

    String lastName;


    String major;

    @ManyToMany
    @JoinTable(name="ENROLLMENTS"
            , joinColumns = @JoinColumn(name = "ST_ID")
            , inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
