package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.OTS4L.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String userName);

}
