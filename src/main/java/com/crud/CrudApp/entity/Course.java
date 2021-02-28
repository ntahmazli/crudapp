package com.crud.CrudApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    Integer courseId;

    String courseName;

    @ManyToMany(mappedBy = "courses")
    List<Student> students;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

