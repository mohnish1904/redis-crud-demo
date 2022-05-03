package com.example.redisdata.repo;

import com.example.redisdata.entity.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final RedisTemplate template;
    private static final String HASH_KEY = "Student";

    public StudentRepository(RedisTemplate template) {
        this.template = template;
    }

    public Student save(Student s){
        template.opsForHash().put(HASH_KEY, s.getId(), s);
        return s;
    }

    public Student findStudentById(Integer id){
        System.out.println("From the DB");
        return (Student) template.opsForHash().get(HASH_KEY, id);
    }

    public List<Student> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public String deleteById(Integer id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Deleted student for Id : "+id;
    }

}
