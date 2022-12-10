package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Serializable<V> {
    void serialize(V value, DataOutput output) throws IOException;

    V deserialize(DataInput input) throws IOException;
}
