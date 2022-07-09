package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("test proper view name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "wrong view returned");
        assertEquals("index", controller.index(), () -> "expensive message" +
                "make only if you have to");
    }

    @Test
    @DisplayName("test exception")
    void oupsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oupsHandler();
        });
    }

    @Disabled("demo of timeout")
    @Test
    void testTimeOut(){
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("i got here");
        });
    }

    @Disabled("demo of timeout")
    @Test
    void testTimeOutPrempt(){
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("i got here 2");
        });
    }

    @Test
    void testAssumptionTrue(){
        assumeTrue("bozo".equalsIgnoreCase(System.getenv("bozo_runtime")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue(){
        assumeTrue("bozo".equalsIgnoreCase("bozo"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS(){

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows(){

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8(){

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11(){

    }

    @EnabledOnJre(JRE.JAVA_17)
    @Test
    void testMeOnJava17(){

    }

    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    @Test
    void testMeOnJava11_17(){

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "bozo")
    @Test
    void testIfUserBozo(){

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
    @Test
    void testIfUserFred(){

    }

}