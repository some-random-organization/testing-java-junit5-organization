package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>(Set.of(new Visit(), new Visit()));
        given(visitRepository.findAll()).willReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        assertNotNull(foundVisits);
        assertEquals(visits.size(), foundVisits.size());
        assertEquals(visits, foundVisits);
        then(visitRepository).should().findAll();
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertNotNull(foundVisit);
        assertEquals(visit, foundVisit);
        then(visitRepository).should().findById(1L);
        then(visitRepository).should(never()).findById(5L);
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(visit);
        assertNotNull(savedVisit);
        assertEquals(visit, savedVisit);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void delete() {
        service.delete(new Visit());
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}