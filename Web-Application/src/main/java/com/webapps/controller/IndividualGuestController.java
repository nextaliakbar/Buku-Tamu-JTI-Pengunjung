package com.webapps.controller;
import com.webapps.entity.Address;
import com.webapps.entity.Guest;
import com.webapps.entity.IndividualGuest;
import com.webapps.entity.Need;
import com.webapps.repository.IndividualGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class IndividualGuestController {

    @Autowired
    private IndividualGuestRepository individualGuestRepo;

    @PostMapping(path = "/perorangan", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@ModelAttribute("individual") IndividualGuest modelIndividualGuest,
    @RequestParam("jenisLembaga") String guestType) {

        Guest guest = modelIndividualGuest.getGuest();
        guest.setGuestType(guestType);
        Address address = modelIndividualGuest.getAddress();

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

        Need need = modelIndividualGuest.getNeed();
        need.setCreatedAt(LocalDateTime.now());
        individualGuestRepo.create(IndividualGuest.builder()
                .guest(guest).address(address)
                .need(need).build());
         return "redirect:/";
    }

}
