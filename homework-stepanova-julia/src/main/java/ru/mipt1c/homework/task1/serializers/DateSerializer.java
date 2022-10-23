package ru.mipt1c.homework.task1.serializers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class DateSerializer implements Serializable<Date> {
    public void serialize(Date value, DataOutput output) throws IOException {
        output.writeLong(value.getTime());
    }

    @Override
    public Date deserialize(DataInput input) throws IOException {
        return new Date(input.readLong());
    }
}
