package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    public static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Mock
    Owner mockOwner;


    @Test
    void processCreationFormHasErrors() {

        // given
        given(bindingResult.hasErrors()).willReturn(Boolean.TRUE);

        // when
        String viewReturned = ownerController.processCreationForm(mockOwner, bindingResult);

        // then
        assertEquals(OWNERS_CREATE_OR_UPDATE_OWNER_FORM, viewReturned);

    }

    @Test
    void argumentCaptorsTest() {
        // given
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        List<Owner> ownerList = new ArrayList<>();
        given(mockOwner.getLastName()).willReturn("Buck");
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);


        // when
        String view = ownerController.processFindForm(mockOwner, bindingResult, null);

        // then
        assertEquals("%Buck%", captor.getValue());
    }

    @Test
    void testErrorResponseHasNoErrors() {
        // given
        given(bindingResult.hasErrors()).willReturn(Boolean.FALSE);
        given(mockOwner.getId()).willReturn(5L);
        given(ownerService.save(any())).willReturn(mockOwner);


        // when
        String viewReturned = ownerController.processCreationForm(mockOwner, bindingResult);

        // then
        assertEquals(REDIRECT_OWNERS_5, viewReturned);
        then(ownerService).should().save(any(Owner.class));
        then(ownerService).shouldHaveNoMoreInteractions();

    }
}