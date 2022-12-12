package ru.mipt1c.homework.tests.task2;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mipt1c.homework.task2.KeyValueStorage;
import ru.mipt1c.homework.task2.Task2Application;
import ru.mipt1c.homework.task2.exceptions.MalformedDataException;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = Task2Application.class)
@RunWith(SpringRunner.class)
@EnableJpaRepositories("ru.mipt1c.homework.task2.repositories")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SingleFileStorageTest extends AbstractSingleFileStorageTest {
    @Autowired
    @Qualifier("stringsStorage")
    private KeyValueStorage<String, String> stringsStorage;

    @Autowired
    @Qualifier("numbersStorage")
    private KeyValueStorage<Integer, Double> numbersStorage;

    @Autowired
    @Qualifier("studentsStorage")
    private KeyValueStorage<StudentKey, Student> studentsStorage;

    @Override
    protected KeyValueStorage<String, String> buildStringsStorage(String path) throws MalformedDataException {
        return stringsStorage;
    }

    @Override
    protected KeyValueStorage<Integer, Double> buildNumbersStorage(String path) throws MalformedDataException {
        return numbersStorage;
    }

    @Override
    protected KeyValueStorage<StudentKey, Student> buildPojoStorage(String path) throws MalformedDataException {
        return studentsStorage;
    }
}
