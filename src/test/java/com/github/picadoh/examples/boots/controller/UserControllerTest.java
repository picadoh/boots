package com.github.picadoh.examples.boots.controller;

import com.github.picadoh.examples.boots.dao.PersonDao;
import com.github.picadoh.examples.boots.domain.Person;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class UserControllerTest {

    @Mock
    private PersonDao personDao;

    private UsersController victim;


    @BeforeClass
    public void setupScenario() {
        initMocks(this);

        victim = new UsersController(personDao);
    }

    @Test
    public void shouldRetrieveAListOfUsers() {
        List<Person> people = newArrayList(
                new Person("123", "ABC"),
                new Person("321", "XPT")
        );

        when(personDao.findAll()).thenReturn(people);

        Model model = mock(Model.class);

        String result = victim.index(model);

        verify(model).addAttribute(eq("people"), eq(people));

        assertEquals(result, "index");
    }

}
