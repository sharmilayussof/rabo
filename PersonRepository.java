package net.guides.springboot2.springboot2jpacrud.repository;

import net.guides.springboot2.springboot2jpacrud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findByFirstName(String firstName);
}
