package com.crud.CrudApp.controller;


import com.crud.CrudApp.entity.Course;
import com.crud.CrudApp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/course")
    public String getCoursePage(){

        return "/course";
    }

    @GetMapping("/newC")
    public String newCoursePage(Model model){
        model.addAttribute("course", new Course());
        return "/newCourse";

    }

    @GetMapping("/findCourse")
    public String findCourse(){

        return "/findCourse";

    }

    @GetMapping("/courseList")
    public String courseList(Model model){
        Iterable<Course> courses = courseRepository.getAllCourseInfo();
        model.addAttribute("courses", courses);

        return "/courseList";

    }

    @GetMapping("/findByCourseId")
    public String findByCourseId(Model model, @RequestParam Integer courseId) {

        Optional<Course> result = courseRepository.getCourseId(courseId);

        if(result.isPresent()){
            Course course = result.get();
            List<Course> cList = new ArrayList<Course>();
            cList.add(course);
            model.addAttribute("courses", cList);
        }
        return "/courseList";
    }

    @GetMapping("/findByCourseName")
    public String findByCourseName(Model model, @RequestParam String courseName) {

        Optional<Course> result = courseRepository.getCourseName(courseName);

        if(result.isPresent()){
            Course course = result.get();
            List<Course> cList = new ArrayList<Course>();
            cList.add(course);
            model.addAttribute("courses", cList);
        }
        return "/courseList";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(Model model, Course courseData) {

        courseRepository.save(courseData);
        model.addAttribute("message", "Course Saved! Check 'View All Courses' link to see the changes.");

        return "/course";

    }

    @GetMapping("/deleteCourse")
    public String deleteStudent() {

        return "/deleteCourse";
    }

    @GetMapping("/deleteCr")
    public String deleteCourseById(Model model, @RequestParam Integer courseId) {

        try {
            Optional<Course> result = courseRepository.getCourseId(courseId);

            courseRepository.delete(result.get());
            model.addAttribute("message", "Course Deleted! Check 'View All Courses' link to see the changes.");
        }
        catch (Exception exc){
            model.addAttribute("message", "You cannot delete this course, because either a student is enrolled in it, or a course with this ID does not exist. ");
        }

        return "/course";
    }


    @GetMapping("/deleteCrName")
    public String deleteCourseByName(Model model, @RequestParam String courseName) {

        try {
            Optional<Course> result = courseRepository.getCourseName(courseName);

            courseRepository.delete(result.get());
            model.addAttribute("message", "Course Deleted! Check 'View All Courses' link to see the changes.");
        }
        catch (Exception exc){
            model.addAttribute("message", "You cannot delete this course, because either a student is enrolled in this course, or a course with this name does not exist.");
        }

        return "/course";
    }




}
