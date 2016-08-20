package com.github.picadoh.examples.boots.controller;

import com.github.picadoh.examples.boots.dao.PersonDao;
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
    private PersonDao personDao;

    @Autowired
    public UsersController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Person> people = personDao.findAll();
        model.addAttribute("people", people);
        return "index";
    }
}