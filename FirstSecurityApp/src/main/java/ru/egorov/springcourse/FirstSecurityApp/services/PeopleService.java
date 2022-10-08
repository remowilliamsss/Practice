package ru.egorov.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egorov.springcourse.FirstSecurityApp.models.Person;
import ru.egorov.springcourse.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findPersonByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
}
