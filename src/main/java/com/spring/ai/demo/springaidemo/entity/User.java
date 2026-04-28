package com.spring.ai.demo.springaidemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String name;
    private String nationality;
    private String profession;
    private String content;
}
