package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    @Mock
    Model model;


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
    void testOrderOfInteractions() {
        // given
        InOrder inOrder = Mockito.inOrder(ownerService, model);
        Owner owner1 = new Owner(2L, "John", "Doe");
        List<Owner> listOwners = new ArrayList<>();
        listOwners.add(owner1);
        listOwners.add(owner1);
        given(ownerService.findAllByLastNameLike(anyString())).willReturn(listOwners);

        // when
        String view = ownerController.processFindForm(mockOwner, bindingResult, model);

        // then
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);

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
        verifyNoMoreInteractions(model);
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