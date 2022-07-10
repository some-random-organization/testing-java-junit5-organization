package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions(){
        Owner owner = new Owner(1L, "Joe", "Doe");
        owner.setCity("Key West");
        owner.setTelephone("123");

        assertAll("properties test",
                () -> assertAll("person properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "first name not match"),
                        () -> assertEquals("Doe", owner.getLastName(), "last name not match")),
                () -> assertAll("owner properties",
                        () -> assertEquals("Key West", owner.getCity(), "city not match"),
                        () -> assertEquals("123", owner.getTelephone(), "phone not match"))
        );
    }

}