package ru.mipt1c.homework.task1.serializers;

import ru.mipt1c.homework.tests.task1.Student;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class StudentSerializer implements Serializable<Student> {
    @Override
    public void serialize(Student student, DataOutput output) throws IOException {
        new IntegerSerializer().serialize(student.getGroupId(), output);
        new StringSerializer().serialize(student.getName(), output);
        new StringSerializer().serialize(student.getHometown(), output);
        new DateSerializer().serialize(student.getBirthDate(), output);
        new BooleanSerializer().serialize(student.isHasDormitory(), output);
        new DoubleSerializer().serialize(student.getAverageScore(), output);
    }

    @Override
    public Student deserialize(DataInput input) throws IOException {
        int group = new IntegerSerializer().deserialize(input);
        String name = new StringSerializer().deserialize(input);
        String hometown = new StringSerializer().deserialize(input);
        Date birthDate = new DateSerializer().deserialize(input);
        boolean hasDormitory = new BooleanSerializer().deserialize(input);
        double averageScore = new DoubleSerializer().deserialize(input);
        return new Student(group, name, hometown, birthDate, hasDormitory, averageScore);
    }
}
