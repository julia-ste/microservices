package ru.mipt1c.homework.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mipt1c.homework.task2.exceptions.MalformedDataException;
import ru.mipt1c.homework.task2.models.StringEntity;
import ru.mipt1c.homework.task2.repositories.IRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Component("stringsStorage")
public class StringsStorage implements KeyValueStorage<String, String> {
    @Autowired
    @Qualifier("stringsRepository")
    private IRepository<String, StringEntity> repository;
    private boolean isClosed = false;

    private void checkIsClosed() {
        if (isClosed) {
            throw new MalformedDataException("Access to closed database file");
        }
    }

    @Override
    public String read(String key) {
        checkIsClosed();
        return repository.findByKey(key).map(StringEntity::getValue).orElse(null);
    }

    @Override
    public boolean exists(String key) {
        checkIsClosed();
        return repository.existsByKey(key);
    }

    @Override
    public void write(String key, String value) {
        checkIsClosed();
        Optional<StringEntity> found = repository.findByKey(key);
        StringEntity entity;
        if (found.isPresent()) {
            entity = found.get();
            entity.setValue(value);
        } else {
            entity = new StringEntity(key, value);
        }
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(String key) {
        checkIsClosed();
        repository.deleteByKey(key);
    }

    @Override
    public Iterator<String> readKeys() {
        checkIsClosed();
        Iterator<StringEntity> iterator = repository.findAll().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public String next() {
                if (!iterator.hasNext()) {
                    return null;
                }
                return iterator.next().getKey();
            }
        };
    }

    @Override
    public int size() {
        checkIsClosed();
        return (int) repository.count();
    }

    @Override
    public void close() throws IOException {
        checkIsClosed();
        isClosed = true;
    }
}
