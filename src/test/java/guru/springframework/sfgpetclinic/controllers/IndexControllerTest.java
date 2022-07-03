package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "wrong view returned");
        assertEquals("index", controller.index(), () -> "expensive message" +
                "make only if you have to");
    }

    @Test
    void oupsHandler() {
        assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "this is some" +
                "expensive message to build" +
                "for my test"
        );
    }
}