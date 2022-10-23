package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleSerializer implements Serializable<Double> {
    @Override
    public void serialize(Double value, DataOutput output) throws IOException {
        output.writeDouble(value);
    }

    @Override
    public Double deserialize(DataInput input) throws IOException {
        return input.readDouble();
    }
}
