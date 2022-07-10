package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("owner mad service test - ")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("first before each");
    }

    @DisplayName("verify zero owners")
    @Test
    void ownersAreZero(){
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
        System.out.println("ownersAreZero()");
    }

    @DisplayName("pet type - ")
    @Nested
    class TestCreatePetType{
        @BeforeEach
        void setUp(){
            PetType petType = new PetType(1L, "dog");
            PetType petType2 = new PetType(2L, "cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);

            System.out.println("nested before each");
        }

        @Test
        void testPetCount(){
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotNull().isEqualTo(2);
            System.out.println("testPetCount()");
        }

        @DisplayName("save owners tests - ")
        @Nested
        class SaveOwnersTest{
            @BeforeEach
            void setUp(){
                ownerMapService.save(new Owner(1L, "before", "each"));

                System.out.println("saved owners before each");
            }

            @Test
            void saveOwner(){
                Owner owner = new Owner(2L, "joe", "doe");
                Owner savedOwner = ownerMapService.save(owner);

                assertThat(savedOwner).isNotNull();
                System.out.println("saveOwner()");
            }

            @DisplayName("saved owners tests - ")
            @Nested
            class FindOwnersTest{

                @DisplayName("find owner")
                @Test
                void findOwner(){
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();
                    System.out.println("findOwner()");
                }

                @DisplayName("find owner not found")
                @Test
                void findOwnerNNotFound(){
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertThat(foundOwner).isNull();
                    System.out.println("findOwnerNNotFound()");
                }

            }
        }
    }

    @DisplayName("verify still zero owners")
    @Test
    void ownersAreStillZero(){
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
        System.out.println("ownersAreStillZero()");
    }
}