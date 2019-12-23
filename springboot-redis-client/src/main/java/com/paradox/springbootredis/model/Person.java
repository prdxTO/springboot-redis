package com.paradox.springbootredis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


@Data
@AllArgsConstructor
@Builder
@RedisHash("person")
public class Person implements Serializable {
    private int id;
    @Indexed
    private String firstName;
    private String lastName;
}
