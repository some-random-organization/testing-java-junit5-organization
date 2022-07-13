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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>(Set.of(new Visit(), new Visit()));
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        assertNotNull(foundVisits);
        assertEquals(visits.size(), foundVisits.size());
        assertEquals(visits, foundVisits);
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertNotNull(foundVisit);
        assertEquals(visit, foundVisit);
        verify(visitRepository).findById(1L);
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(visit)).thenReturn(visit);
        Visit savedVisit = service.save(visit);
        assertNotNull(savedVisit);
        assertEquals(visit, savedVisit);
        verify(visitRepository).save(visit);
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository).delete(visit);
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(visitRepository).deleteById(anyLong());
    }
}