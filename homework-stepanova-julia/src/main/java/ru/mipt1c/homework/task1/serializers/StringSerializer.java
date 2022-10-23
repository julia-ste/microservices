package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class StringSerializer implements Serializable<String> {
    @Override
    public void serialize(String value, DataOutput output) throws IOException {
        output.writeUTF(value);
    }

    @Override
    public String deserialize(DataInput input) throws IOException {
        return input.readUTF();
    }
}
