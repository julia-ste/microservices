package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BooleanSerializer implements Serializable<Boolean> {
    @Override
    public void serialize(Boolean value, DataOutput output) throws IOException {
        output.writeBoolean(value);
    }

    @Override
    public Boolean deserialize(DataInput input) throws IOException {
        return input.readBoolean();
    }
}
