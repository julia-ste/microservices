package ru.mipt1c.homework.tests.task2;

import ru.mipt1c.homework.task2.KeyValueStorage;
import ru.mipt1c.homework.task2.exceptions.MalformedDataException;

public abstract class KeyValueStorageFactories {
    protected abstract KeyValueStorage<String, String> buildStringsStorage(String path) throws MalformedDataException;

    protected abstract KeyValueStorage<Integer, Double> buildNumbersStorage(String path) throws MalformedDataException;

    protected abstract KeyValueStorage<StudentKey, Student> buildPojoStorage(String path) throws MalformedDataException;
}
