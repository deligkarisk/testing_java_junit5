package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

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

    @RepeatedTest(value = 5, name = "{displayName}")
    @DisplayName("My other repeated test")
    void myRepeatedTestwithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " " + repetitionInfo.getCurrentRepetition());

    }

    @RepeatedTest(value = 10, name = "{displayName} - {currentRepetition}: {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest() {
        //todo
    }

}