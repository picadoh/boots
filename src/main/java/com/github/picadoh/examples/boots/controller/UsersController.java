package com.github.picadoh.examples.boots.controller;

import com.github.picadoh.examples.boots.repository.PersonRepository;
import com.github.picadoh.examples.boots.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Handles the index requests and presents a list of users.
 */
@Controller
public class UsersController {
    private final PersonRepository personRepository;

    @Autowired
    public UsersController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        return "index";
    }
}
