package com.crud.CrudApp.controller;


import com.crud.CrudApp.entity.Student;
import com.crud.CrudApp.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;


    @GetMapping("/")
    public String getWelcomePage(){

        return "/index";
    }

    @GetMapping("/student")
    public String getStudentPage(){

        return "/student";
    }



    @GetMapping("/new")
    public String newStudentPage(Model model){
        model.addAttribute("student", new Student());
        return "/newStudent";

    }

    @PostMapping("/save")
    public String saveStudent(Model model, Student studentData) {
        studentRepository.save(studentData);
        model.addAttribute("message", "Student Added! Check 'View All Students' link to see the changes.");
        return "/student";

    }


    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        Iterable<Student> students = studentRepository.getAllStudentInfo();
        model.addAttribute("students", students);

        return "/studentList";
    }

    @GetMapping("/findStudent")
    public String findStudent(){

        return "/findStudent";
    }

    @GetMapping("/findById")
    public String findStudentById(Model model, @RequestParam Integer id) {

        Optional<Student> result = studentRepository.getStudentId(id);

        if (result.isPresent()) {
            Student student = result.get();
            List<Student> stList = new ArrayList<Student>();
            stList.add(student);
            model.addAttribute("students", stList);
        }

        return "/studentList";
    }

    @GetMapping("/findByName")
    public String findStudentByName(Model model, @RequestParam String stName) {


        List<Student> stList = studentRepository.getFirstName(stName);
        model.addAttribute("students", stList);
        return "/studentList";


    }

    @GetMapping("/findBySurname")
    public String findStudentBySurname(Model model, @RequestParam String stSurname) {

        List<Student> stList = studentRepository.getSurname(stSurname);
        model.addAttribute("students", stList);
        return "/studentList";

    }

    @GetMapping("/findCourseByStId")
    public String findCourseByStId(Model model, @RequestParam Integer stId) {

        Optional<Student> result = studentRepository.getStudentId(stId);

        if(result.isPresent()){
            Student student = result.get();
            List<Student> stList = new ArrayList<Student>();
            stList.add(student);
            model.addAttribute("courses", student.getCourses());
        }
        return "/courseList";


    }

    @GetMapping("/findByMajor")
    public String findStudentByMajor(Model model, @RequestParam String major) {

        try {
            List<Student> stList = studentRepository.getMajor(major);
            model.addAttribute("students", stList);
            return "/studentList";
        }
        catch(Exception exc){
            model.addAttribute("message", "A student with this major does not exist.");
            return "/student";
        }
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent() {

        return "/deleteStudent";
    }


    @GetMapping("/deleteStById")
    public String deleteStudentById(Model model, Student deleteStudent, @RequestParam Integer stId) {

        try {
            Optional<Student> result = studentRepository.getStudentId(stId);

            studentRepository.delete(result.get());
            model.addAttribute("message", "Student Deleted! Check 'View All Students' link to see the changes.");
        }
        catch (Exception exc){
            model.addAttribute("message", "You cannot delete this student, because a student with this ID does not exist.");
        }

        return "/student";
    }

}
