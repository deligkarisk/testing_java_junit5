package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void findById() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1l);
        assertNotNull(foundSpeciality);
    }

    @Test
    void findByIdbddTest() {
        Speciality speciality = new Speciality();

        // given
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpeciality = service.findById(1L);

        // then
        assertNotNull(foundSpeciality);
        then(specialtyRepository).should().findById(1L);
        then(specialtyRepository).should(times(1)).findById(1L);
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void deleteById() {

        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);

    }

    @Test
    void deleteByObject() {

        // given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));
        verify(specialtyRepository).delete(speciality);


    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("Boom111")).when(specialtyRepository).deleteById(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.deleteById(1L));
        verify(specialtyRepository).deleteById(any());
    }

    @Test
    void anotherTestOnThrowBdd() {
        // Cannot be used as delete() returns void
        // given(specialtyRepository.delete(any())).willThrow( new RuntimeException("Ca Boom!"));

        willThrow(new RuntimeException("Ca Bam!")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testThrowBdd() {
        // given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException(("Bam!")));

        // then
        assertThrows(RuntimeException.class, () -> service.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }
}