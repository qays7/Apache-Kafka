package dev.com.kafkasendmessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class User {

    private String name;
    private int age;

}