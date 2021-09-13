package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@Tag("modelTests")
public interface ModelTests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Default method running" + " " + testInfo.getDisplayName() + ":" +
                repetitionInfo.getCurrentRepetition());

    }
}
