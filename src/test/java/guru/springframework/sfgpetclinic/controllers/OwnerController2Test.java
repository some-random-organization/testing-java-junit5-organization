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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OwnerController2Test implements ControllerTests {

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
        controller = new OwnerController(ownerService);

        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocation.getArgument(0);

                    if (name.equals("%Doe%")){
                        owners.add(new Owner(1L, "Joe", "Doe"));
                        return owners;
                    }else if(name.equals("%nie ma%")){
                        return owners;
                    }else if(name.equals("%find%")){
                        owners.add(new Owner(1L, "Joe", "Doe"));
                        owners.add(new Owner(2L, "Joe2", "Doe2"));
                        return owners;
                    }

                    throw new RuntimeException("invalid argument");
                }
        );
    }

//    @Test
//    void processFindFormWildcardsString() {
//        Owner owner = new Owner(5L, "Joe", "Doe");
//        List<Owner> ownerList = new ArrayList<>();
//        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);
//
//        String viewName = controller.processFindForm(owner, result, null);
//
//
//        assertThat("%Doe%").isEqualToIgnoringCase(captor.getValue());
//    }
//
//    @Test
//    void processFindFormWildcardsStringAnnotation() {
//        Owner owner = new Owner(5L, "Joe", "Doe");
//        List<Owner> ownerList = new ArrayList<>();
//
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);
//
//        String viewName = controller.processFindForm(owner, result, null);
//
//        assertThat("%Doe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
//    }

    @Test
    void processFindFormWildcardsStringAnnotation2() {
        Owner owner = new Owner(5L, "Joe", "Doe");
        List<Owner> ownerList = new ArrayList<>();

        String viewName = controller.processFindForm(owner, result, null);

        assertThat("%Doe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardsStringAnnotation2NotFound() {
        Owner owner = new Owner(5L, "Joe", "nie ma");
        List<Owner> ownerList = new ArrayList<>();



        String viewName = controller.processFindForm(owner, result, null);

        assertThat("%nie ma%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardsStringAnnotation2Found() {
        Owner owner = new Owner(5L, "Joe", "find");
        List<Owner> ownerList = new ArrayList<>();



        String viewName = controller.processFindForm(owner, result, mock(Model.class));

        assertThat("%find%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

//    @DisplayName("binding result has no errors")
//    @Test
//    void processCreationForm() {
//        final String EXPECTED = "redirect:/owners/5";
//        Owner owner = new Owner(5L, "Joe", "Doe");
//        given(result.hasErrors()).willReturn(false);
//        given(ownerService.save(any(Owner.class))).willReturn(owner);
//
//        String controllerReturn = controller.processCreationForm(owner, result);
//
//        assertEquals(EXPECTED, controllerReturn);
//        assertThat(controllerReturn).isEqualTo(EXPECTED);
//        then(ownerService).should().save(any(Owner.class));
//        then(ownerService).shouldHaveNoMoreInteractions();
//        then(result).should().hasErrors();
//        then(result).shouldHaveNoMoreInteractions();
//    }
//
//    @DisplayName("binding result has errors")
//    @Test
//    void processCreationFormHasError() {
//        final String EXPECTED = "owners/createOrUpdateOwnerForm";
//        Owner owner = new Owner(5L, "Joe", "Doe");
//        given(result.hasErrors()).willReturn(true);
//
//        String controllerReturn = controller.processCreationForm(owner, result);
//
//        assertEquals(EXPECTED, controllerReturn);
//        assertThat(controllerReturn).isEqualTo(EXPECTED);
//        then(ownerService).shouldHaveNoInteractions();
//        then(result).should().hasErrors();
//        then(result).shouldHaveNoMoreInteractions();
//    }

}