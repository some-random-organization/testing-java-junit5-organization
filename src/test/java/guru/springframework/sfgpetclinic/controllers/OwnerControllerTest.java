package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest implements ControllerTests {

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult result;

    @InjectMocks
    OwnerController controller;

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