package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeatedModel")
public interface ModelRepeatedTest {
    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println("model repeated interface - " + testInfo.getDisplayName() + ", " + repetitionInfo.getCurrentRepetition());
    }

}
