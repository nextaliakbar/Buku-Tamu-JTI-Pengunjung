package com.webapps.controller;

import com.webapps.entity.*;
import com.webapps.repository.InstitutionGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class InstitutionGuestController {

    @Autowired
    private InstitutionGuestRepository institutionGuestRepo;

    @PostMapping(path = "/lembaga", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@ModelAttribute("institution") InstitutionGuest modelInstitutionGuest,
    @RequestParam("jenisLembaga") String guestType) {

        Guest guest = modelInstitutionGuest.getGuest();
        guest.setGuestType(guestType);
        Address address = modelInstitutionGuest.getAddress();
        Institution institution = modelInstitutionGuest.getInstitution();

        if(guest.getGuestType().equals("Internasional")) {
            address.setProvince("");
            address.setCity("");
            address.setSubdistrict("");
        } else {
            address.setCountry("Indonesia");
        }

        if(address.getProvince().equals("0")) {
            address.setProvince("35");
        }

        if(address.getCity().equals("0")) {
            address.setCity("3509");
        }

        Need need = modelInstitutionGuest.getNeed();
        need.setCreatedAt(LocalDateTime.now());
        institutionGuestRepo.create(InstitutionGuest.builder()
                .guest(guest).address(address)
                .institution(institution).need(need)
                .build());

        return "redirect:/";
    }
}
