package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @Test
    void groupedAssertions(){
        Person person = new Person(1L, "Joe", "Doe");

        assertAll("test props set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName()));
    }

    @Test
    void groupedAssertions2(){
        Person person = new Person(1L, "Joe", "Doe");

        assertAll("test props set",
                () -> assertEquals("Joe", person.getFirstName(), "first name failed"),
                () -> assertEquals("Doe", person.getLastName(), "last name failed"));
    }

}