package com.webapps;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InstitutionGuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testCreate() {
        MultiValueMap<String, String> institutionGuest = new LinkedMultiValueMap<>();
        institutionGuest.add("guest.name", "Ali Akbar Rafsanjani");
        institutionGuest.add("guest.gender", "Laki-laki");
        institutionGuest.add("guest.placeOfBirth", "Jember");
        institutionGuest.add("guest.dateOfBirth", "2003-07-02");
        institutionGuest.add("guest.noHp", "08123456789");
        institutionGuest.add("institution.name", "Politeknik Negeri Jember");
        institutionGuest.add("address.country", "Indonesia");
        institutionGuest.add("address.province", "Jawa Timur");
        institutionGuest.add("address.city","Jember");
        institutionGuest.add("address.subdistrict","Bangsalsari");
        institutionGuest.add("institution.noTelp", "08123456789");
        institutionGuest.add("need.title","Konsultasi");
        mockMvc.perform(
                post("/lembaga")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("jenisLembaga", "Lokal")
                        .params(institutionGuest)
        ).andExpectAll(status().isFound(), redirectedUrl("/"));
    }

}
