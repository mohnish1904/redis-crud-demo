package com.example.redisdata.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@RedisHash("student")
public class Student implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String std;
    private Long roll;
}
