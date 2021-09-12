package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Person")
class PersonTest {

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(1L, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck",person.getLastName()));
    }

    @Test
    @RepeatedTest(value = 10, name = "{displayName} - {currentRepetition}: {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest() {
        //todo
    }

}