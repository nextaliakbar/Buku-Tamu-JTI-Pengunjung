package com.webapps.controller;

import com.webapps.entity.*;
import com.webapps.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(path = "/")
    public String home() {
        return "index";
    }

    @GetMapping(path ="/perorangan")
    public String individual(Model model) {
        var individual = new IndividualGuest();
        individual.setGuest(new Guest());
        individual.setAddress(new Address());
        individual.setNeed(new Need());
        List<Province> provinces = addressRepository.getAllProvinces();
        model.addAttribute("individual", individual);
        model.addAttribute("provinces", provinces);
        return "perorangan";
    }

    @GetMapping(path = "/lembaga")
    public String institution(Model model) {
        var institution = new InstitutionGuest();
        institution.setGuest(new Guest());
        institution.setAddress(new Address());
        institution.setInstitution(new Institution());
        institution.setNeed(new Need());
        List<Province> provinces = addressRepository.getAllProvinces();
        model.addAttribute("institution", institution);
        model.addAttribute("provinces", provinces);
        return "lembaga";
    }

}
