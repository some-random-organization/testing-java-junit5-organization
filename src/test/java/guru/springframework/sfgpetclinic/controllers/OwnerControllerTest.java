package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest implements ControllerTests {

//    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult result;

//    @InjectMocks
    OwnerController controller;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    /**
     * to capture work for all tests owner service needs to be refreshed for every test
     */
    @BeforeEach
    void setUp(){
        ownerService = mock(OwnerService.class);
//        result = mock(BindingResult.class);
        controller = new OwnerController(ownerService);
    }

    @Test
    void processFindFormWildcardsString() {
        Owner owner = new Owner(5L, "Joe", "Doe");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        String viewName = controller.processFindForm(owner, result, null);


        assertThat("%Doe%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void processFindFormWildcardsStringAnnotation() {
        Owner owner = new Owner(5L, "Joe", "Doe");
        List<Owner> ownerList = new ArrayList<>();

        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        String viewName = controller.processFindForm(owner, result, null);

        assertThat("%Doe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    }

    @DisplayName("binding result has no errors")
    @Test
    void processCreationForm() {
        final String EXPECTED = "redirect:/owners/5";
        Owner owner = new Owner(5L, "Joe", "Doe");
        given(result.hasErrors()).willReturn(false);
        given(ownerService.save(any(Owner.class))).willReturn(owner);

        String controllerReturn = controller.processCreationForm(owner, result);

        assertEquals(EXPECTED, controllerReturn);
        assertThat(controllerReturn).isEqualTo(EXPECTED);
        then(ownerService).should().save(any(Owner.class));
        then(ownerService).shouldHaveNoMoreInteractions();
        then(result).should().hasErrors();
        then(result).shouldHaveNoMoreInteractions();
    }

    @DisplayName("binding result has errors")
    @Test
    void processCreationFormHasError() {
        final String EXPECTED = "owners/createOrUpdateOwnerForm";
        Owner owner = new Owner(5L, "Joe", "Doe");
        given(result.hasErrors()).willReturn(true);

        String controllerReturn = controller.processCreationForm(owner, result);

        assertEquals(EXPECTED, controllerReturn);
        assertThat(controllerReturn).isEqualTo(EXPECTED);
        then(ownerService).shouldHaveNoInteractions();
        then(result).should().hasErrors();
        then(result).shouldHaveNoMoreInteractions();
    }

}