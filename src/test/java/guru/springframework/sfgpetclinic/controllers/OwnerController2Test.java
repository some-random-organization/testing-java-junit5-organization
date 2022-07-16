package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OwnerController2Test implements ControllerTests {

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult result;

    @Mock
    Model model;

    @InjectMocks
    OwnerController controller;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
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

    @AfterEach
    void after(){
        this.controller = null;
    }

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

        InOrder inOrder = Mockito.inOrder(ownerService, model);


        String viewName = controller.processFindForm(owner, result, model);

        assertThat("%find%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
    }


}