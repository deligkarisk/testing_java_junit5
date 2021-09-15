package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {


    @Spy
    PetMapService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetWithVisit() {

        // given
        Pet pet = new Pet(1L);
        petService.save(pet);
        given(petService.findById(1L)).willCallRealMethod();
        Map<String, Object> model = new HashMap<>();

        // when
        Visit visit = visitController.loadPetWithVisit(1L, model);

        // then
        assertNotNull(visit);
        assertNotNull(visit.getPet());
        assertEquals(1L,visit.getPet().getId());

    }
}