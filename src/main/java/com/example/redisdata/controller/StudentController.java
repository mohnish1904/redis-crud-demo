package com.example.redisdata.controller;

import com.example.redisdata.entity.Student;
import com.example.redisdata.repo.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student s) {
        studentRepository.save(s);
        return s;
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Student", unless = "#result.roll > 10")
    public Student getStudentById(@PathVariable Integer id) {
        return studentRepository.findStudentById(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Student")
    public String deleteStudentById(@PathVariable Integer id) {
        return studentRepository.deleteById(id);
    }
}
