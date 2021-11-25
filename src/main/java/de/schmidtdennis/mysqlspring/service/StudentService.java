package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.model.Student;
import de.schmidtdennis.mysqlspring.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
        log.info(String.format("Saved Student %s to redis", student));
    }

    public Student getStudentById(String id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public List<Student> getAllStudents(){
        List<Student> allStudents = new ArrayList<>();
        studentRepository.findAll().forEach(allStudents::add);
        return allStudents;
    }

    public void editUserAge(String id, Integer age){
        Student retrievedStudent = getStudentById(id);
        retrievedStudent.setAge(age);
        studentRepository.save(retrievedStudent);
    }

    public void deleteStudent(String id){
        studentRepository.deleteById(id);
        log.info(String.format("Deleted Student with id=%s", id));
    }

}
