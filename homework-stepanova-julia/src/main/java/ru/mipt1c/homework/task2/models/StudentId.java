package ru.mipt1c.homework.task2.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentId implements Serializable {
    private int groupId;
    private String name;
}
