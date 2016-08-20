package com.github.picadoh.examples.boots.dao;

import com.github.picadoh.examples.boots.domain.Person;

import java.util.List;

/**
 * Provides access to {@see Person} in the underlying storage.
 *
 * @author picadoh
 */
public interface PersonDao {

    List<Person> findAll();

    void insert(Person person);

}
