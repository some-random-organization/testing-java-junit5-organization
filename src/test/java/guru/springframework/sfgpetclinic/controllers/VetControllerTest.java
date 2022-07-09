package guru.springframework.sfgpetclinic.controllers;

//import guru.springframework.sfgpetclinic.fauxspring.Model;
//import guru.springframework.sfgpetclinic.fauxspring.ModelImpl;
//import guru.springframework.sfgpetclinic.model.Vet;
//import guru.springframework.sfgpetclinic.services.SpecialtyService;
//import guru.springframework.sfgpetclinic.services.VetService;
//import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
//import guru.springframework.sfgpetclinic.services.map.VetMapService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;

//class VetControllerTest {
//
//    SpecialtyService specialtyService;
//    VetService vetService;
//    VetController controller;
//    Model model;
//
//    @BeforeEach
//    void setUp() {
//        specialtyService = new SpecialityMapService();
//        vetService = new VetMapService(specialtyService);
//        controller = new VetController(vetService);
//        model = new ModelImpl();
//        Vet vet = new Vet(1L, "Joe", "Doe", specialtyService.findAll());
//        vetService.save(vet);
//        Vet vet1 = new Vet(2L, "Joe2", "Doe2", specialtyService.findAll());
//        vetService.save(vet1);
//
//    }
//
//    @Test
//    void listVets() {
//        assertEquals("vets/index", controller.listVets(model));
//    }
//
//    @Test
//    void addingToModel(){
//        controller.listVets(model);
//
//        model.getMap().forEach((k,v) -> System.out.println("key " + k + " | value " + v));
//        System.out.println(model.getMap().get("vets"));
//        System.out.println(vetService.findAll());
//
//        assertEquals(vetService.findAll(), model.getMap().get("vets"));
////        assertEquals(vetService.findAll().size(), ((Set) model.get("vets")).size());
//        assertEquals(vetService.findAll().size(), model.get("vets").size());
//
//        System.out.println( model.get("vets"));
//        System.out.println(((Set) model.get("vets")).size());
//
//    }
//}