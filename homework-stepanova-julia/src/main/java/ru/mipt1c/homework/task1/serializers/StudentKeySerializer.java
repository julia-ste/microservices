package ru.mipt1c.homework.task1.serializers;

import ru.mipt1c.homework.tests.task1.StudentKey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StudentKeySerializer implements Serializable<StudentKey> {
    @Override
    public void serialize(StudentKey studentKey, DataOutput output) throws IOException {
        new IntegerSerializer().serialize(studentKey.getGroupId(), output);
        new StringSerializer().serialize(studentKey.getName(), output);
    }

    @Override
    public StudentKey deserialize(DataInput input) throws IOException {
        int group = new IntegerSerializer().deserialize(input);
        String name = new StringSerializer().deserialize(input);
        return new StudentKey(group, name);
    }
}
