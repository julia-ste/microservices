package ru.mipt1c.homework.tests.task1;

import ru.mipt1c.homework.task1.*;
import ru.mipt1c.homework.task1.exceptions.MalformedDataException;
import ru.mipt1c.homework.task1.serializers.*;

public class SingleFileStorageTest extends AbstractSingleFileStorageTest {
    @Override
    protected KeyValueStorage<String, String> buildStringsStorage(String path) throws MalformedDataException {
        return new KeyValueStorageImpl<>(path, new StringSerializer(), new StringSerializer());
    }

    @Override
    protected KeyValueStorage<Integer, Double> buildNumbersStorage(String path) throws MalformedDataException {
        return new KeyValueStorageImpl<>(path, new IntegerSerializer(), new DoubleSerializer());
    }

    @Override
    protected KeyValueStorage<StudentKey, Student> buildPojoStorage(String path) throws MalformedDataException {
        return new KeyValueStorageImpl<>(path, new StudentKeySerializer(), new StudentSerializer());
    }
}
