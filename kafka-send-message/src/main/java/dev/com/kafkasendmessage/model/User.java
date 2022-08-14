package dev.com.kafkasendmessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private int age;

}