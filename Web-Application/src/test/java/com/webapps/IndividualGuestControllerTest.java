package com.webapps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class IndividualGuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testCreate() {
        MultiValueMap<String, String> individualGuest = new LinkedMultiValueMap<>();
        individualGuest.add("guest.name", "Ali Akbar Rafsanjani");
        individualGuest.add("guest.gender", "Laki-laki");
        individualGuest.add("guest.placeOfBirth", "Jember");
        individualGuest.add("guest.dateOfBirth", "2003-07-02");
        individualGuest.add("guest.noHp", "08123456789");
        individualGuest.add("address.country", "Indonesia");
        individualGuest.add("address.province", "Jawa Timur");
        individualGuest.add("address.city","Jember");
        individualGuest.add("address.subdistrict","Bangsalsari");
        individualGuest.add("need.title","Konsultasi");
        mockMvc.perform(
                post("/perorangan")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("jenisLembaga", "Lokal")
                        .params(individualGuest)
        ).andExpectAll(status().isFound(), redirectedUrl("/"));
    }


}
