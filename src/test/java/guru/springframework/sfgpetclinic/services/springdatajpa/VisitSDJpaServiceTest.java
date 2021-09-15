package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;


    @DisplayName(("Test find by Id"))
    @Test
    void findById() {

        // given
        Visit visit = new Visit();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        // when
        Visit foundVisit = visitSDJpaService.findById(1L);

        // then
        then(visitRepository).should().findById(1L);
        then(visitRepository).should(times(1)).findById(any(Long.class));

    }

    @Test
    void deleteById() {

        // given - none

        // when
        visitSDJpaService.deleteById(1L);

        // then
        then(visitRepository).should().deleteById(1l);
        then(visitRepository).should(times(1)).deleteById(1L);
        then(visitRepository).shouldHaveNoMoreInteractions();

    }
}