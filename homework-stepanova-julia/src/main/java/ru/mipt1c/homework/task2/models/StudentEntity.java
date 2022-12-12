package ru.mipt1c.homework.task2.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "StudentEntity")
@IdClass(StudentId.class)
@NoArgsConstructor
public class StudentEntity implements Serializable {

    @Id
    private int groupId;

    @Id
    private String name;

    private String hometown;
    private Date birthDate;
    private boolean hasDormitory;
    private double averageScore;

    public StudentEntity(int groupId, String name, String hometown,
                         Date birthDate, boolean hasDormitory, double averageScore) {
        this.groupId = groupId;
        this.name = name;
        this.hometown = hometown;
        this.birthDate = birthDate;
        this.hasDormitory = hasDormitory;
        this.averageScore = averageScore;
    }
}
