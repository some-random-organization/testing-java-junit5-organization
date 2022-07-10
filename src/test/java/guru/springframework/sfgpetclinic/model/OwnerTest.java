package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @DisplayName("value source test - ")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @ValueSource(strings = {"one", "two", "three"})
    void testValueSource(String val){
        System.out.println(val);
    }

    @DisplayName("enum source test - ")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType){
        System.out.println(ownerType);
    }

    @DisplayName("csv source test - ")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3"
    })
    void csvInputTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("csv from file test - ")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("method provider test - ")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    static Stream<Arguments> getArgs(){
        System.out.println("getArgs");
        return Stream.of(
                Arguments.of("FL", 7, 7),
                Arguments.of("OH", 8, 8),
                Arguments.of("MI", 9, 9));
    }
}