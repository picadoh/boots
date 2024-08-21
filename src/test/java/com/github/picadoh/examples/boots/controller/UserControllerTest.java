package com.github.picadoh.examples.boots.controller;

import com.github.picadoh.examples.boots.domain.Person;
import com.github.picadoh.examples.boots.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final UsersController victim = new UsersController(personRepository);

    @Test
    public void shouldPopulateListModel() {
        List<Person> people = List.of(
                new Person("123", "ABC"),
                new Person("321", "XPT")
        );

        when(personRepository.findAll()).thenReturn(people);

        Model model = mock(Model.class);

        String result = victim.index(model);

        verify(model).addAttribute(eq("people"), eq(people));

        assertEquals(result, "index");
    }

}
