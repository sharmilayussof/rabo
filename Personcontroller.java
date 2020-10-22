package net.guides.springboot2.springboot2jpacrud.controller;

import java.util.HashMap;

        import java.util.List;
import java.util.Map;

import net.guides.springboot2.springboot2jpacrud.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrud.model.Person;
import net.guides.springboot2.springboot2jpacrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Personcontroller {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public List <Person> getAllPerson() {
        return personRepository.findAll();
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity < Person > getPersonId(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        return ResponseEntity.ok().body(person);
    }

   @GetMapping("/person/{firstName}")
    public ResponseEntity < Person > getPersonId(@PathVariable(value = "firstName") String firstName)
            throws ResourceNotFoundException {
        Person person = (Person) personRepository.findByFirstName(firstName);
        return ResponseEntity.ok().body(person);
    }

      @PutMapping("/person/{id}")
    public ResponseEntity < Person > updatePerson(@PathVariable(value = "id") Long personId,
                                                      @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

          person.setAddress(personDetails.getAddress());
          person.setAge(personDetails.getAge());
          person.setLastName(personDetails.getLastName());
          person.setFirstName(personDetails.getFirstName());
        final Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{id}")
    public Map< String, Boolean > deletPerson(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        personRepository.delete(person);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
