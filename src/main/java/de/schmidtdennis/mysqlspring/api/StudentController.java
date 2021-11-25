package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Student;
import de.schmidtdennis.mysqlspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping(value = "student/add")
    public String addStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return "saved";
    }

    @GetMapping("/student/getAll")
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("student/get/{id}")
    public Student getStudentById(@PathVariable String id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("student/delete/{id}")
    public void deleteStudentById(@PathVariable String id){
        studentService.deleteStudent(id);
    }

}
