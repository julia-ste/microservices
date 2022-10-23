package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntegerSerializer implements Serializable<Integer> {
    @Override
    public void serialize(Integer value, DataOutput output) throws IOException {
        output.writeInt(value);
    }

    @Override
    public Integer deserialize(DataInput input) throws IOException {
        return input.readInt();
    }
}
