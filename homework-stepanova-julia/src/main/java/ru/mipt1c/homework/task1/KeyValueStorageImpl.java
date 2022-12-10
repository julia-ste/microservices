package ru.mipt1c.homework.task1;

import ru.mipt1c.homework.task1.exceptions.MalformedDataException;
import ru.mipt1c.homework.task1.serializers.Serializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KeyValueStorageImpl<K, V> implements KeyValueStorage<K, V> {
    private static final String FILENAME = "storage.db";
    private final File databaseFile;
    private final Serializable<K> keySerializer;
    private final Serializable<V> valueSerializer;
    private final Map<K, V> database = new HashMap<>();
    private boolean isClosed;

    public KeyValueStorageImpl(String directoryPath,
                               Serializable<K> keySerializer,
                               Serializable<V> valueSerializer) {
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;

        File directory = new File(directoryPath);
        if (!directory.isDirectory() || !directory.exists()) {
            throw new MalformedDataException(String.format("Directory '%s' not found", directoryPath));
        }

        databaseFile = new File(directory, FILENAME);
        if (databaseFile.exists()) {
            readFromFile();
        }
    }

    private void readFromFile() {
        try (DataInputStream input = new DataInputStream(Files.newInputStream(databaseFile.toPath()))) {
            int databaseSize = input.readInt();
            for (int i = 0; i < databaseSize; i++) {
                K key = keySerializer.deserialize(input);
                V value = valueSerializer.deserialize(input);
                database.put(key, value);
            }
        } catch (IOException e) {
            throw new MalformedDataException("Can't read from database file");
        }
    }

    private void writeToFile() {
        try (DataOutputStream output = new DataOutputStream(Files.newOutputStream(databaseFile.toPath()))) {
            output.writeInt(database.size());
            for (Map.Entry<K, V> entry : database.entrySet()) {
                keySerializer.serialize(entry.getKey(), output);
                valueSerializer.serialize(entry.getValue(), output);
            }
        } catch (IOException e) {
            throw new MalformedDataException("Can't write to database file");
        }
    }

    private void checkIsClosed() {
        if (isClosed) {
            throw new MalformedDataException("Access to closed database file");
        }
    }

    @Override
    public V read(K key) {
        checkIsClosed();
        return database.get(key);
    }

    @Override
    public boolean exists(K key) {
        checkIsClosed();
        return database.containsKey(key);
    }

    @Override
    public void write(K key, V value) {
        checkIsClosed();
        database.put(key, value);
    }

    @Override
    public void delete(K key) {
        checkIsClosed();
        database.remove(key);
    }

    @Override
    public Iterator<K> readKeys() {
        checkIsClosed();
        return database.keySet().iterator();
    }

    @Override
    public int size() {
        checkIsClosed();
        return database.size();
    }

    @Override
    public void close() {
        checkIsClosed();
        writeToFile();
        isClosed = true;
        database.clear();
    }
}
