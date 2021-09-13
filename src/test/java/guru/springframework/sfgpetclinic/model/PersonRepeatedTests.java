package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTests  implements ModelTests  {

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


    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}")
    @DisplayName("assignment test")
    void assignmentTest() {

    }
}
