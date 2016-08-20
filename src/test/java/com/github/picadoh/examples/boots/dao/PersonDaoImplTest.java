package com.github.picadoh.examples.boots.dao;

import com.datastax.driver.core.Row;
import com.github.picadoh.examples.boots.domain.Person;
import com.github.picadoh.examples.boots.util.AbstractCassandraTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class PersonDaoImplTest extends AbstractCassandraTest {

    private PersonDao personDao;

    @BeforeClass
    public void setUp() throws Exception {
        personDao = new PersonDaoImpl(cassandra.session);
    }

    @Test
    public void shouldReturnStoredPersons() throws Exception {
        //given
        Person john = new Person("XYZ123", "John");
        Person anna = new Person("ZYX567", "Anna");
 
        //when
        List<Person> personList = personDao.findAll();
 
        //then
        assertThat(personList, hasItem(john));
        assertThat(personList, hasItem(anna));
    }

    @Test
    public void shouldInsertNewPerson() throws Exception {
        //given
        String personId = "ABC988";
        String personName = "Joanne";
        Person newPerson = new Person(personId, personName);

        //when
        personDao.insert(newPerson);

        // then
        Row personRow = fetchPersonRowById(personId);

        assertThat(personRow.getString("id"), equalTo(personId));
        assertThat(personRow.getString("name"), equalTo(personName));
    }

}