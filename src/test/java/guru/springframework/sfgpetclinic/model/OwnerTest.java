package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @ParameterizedTest(name = "{displayName}[{index}] {arguments}")
    @ValueSource(strings = {"Spring", "is", "great"})
    @DisplayName("Value Source Test - ")
    void testValueSource(String val) {
        System.out.println(val);
    }

    @ParameterizedTest(name = "{displayName}[{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumParameterizedTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @ParameterizedTest(name = "{displayName}[{index}] {arguments}")
    @DisplayName("Value Source Test - ")
    @CsvSource({
            "FL, 1, 1",
            "OH, 4, 5"

    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println("State is: " + stateName + " with val 2: " + val2);

    }

    @ParameterizedTest(name = "{displayName}[{index}] {arguments}")
    @DisplayName("Value Source Test - ")
    @CsvFileSource(resources = "/sources.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println("State is: " + stateName + " with val 2: " + val2);
    }

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Thessaloniki");
        owner.setTelephone("07033454433");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Thessaloniki", owner.getCity()),
                        () -> assertEquals("07033454433", owner.getTelephone())));
    }
}
