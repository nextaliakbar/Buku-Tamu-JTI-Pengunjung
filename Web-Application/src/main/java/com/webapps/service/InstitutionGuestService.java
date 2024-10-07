package com.webapps.service;

import com.webapps.entity.*;
import com.webapps.model.WebResponse;
import com.webapps.repository.InstitutionGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
public class InstitutionGuestService implements InstitutionGuestRepository {

    @Autowired
    private RestTemplate restTemplate;

    private String SERVER = "http://localhost:8080";

    @Override
    public InstitutionGuest create(InstitutionGuest institutionGuest) {
        Guest guest = createGuest(institutionGuest.getGuest());
        Address address = createAddress(institutionGuest.getAddress(), guest.getId());
        Institution institution = createInstitution(institutionGuest.getInstitution(), guest.getId());
        Need need = createNeed(institutionGuest.getNeed(), guest.getId());
        return InstitutionGuest.builder().guest(guest).address(address)
                .institution(institution).need(need).build();
    }

    @Override
    public Guest createGuest(Guest guest) {
        String url = SERVER + "/api/guests";

        RequestEntity<Guest> request =
                new RequestEntity<>(guest, HttpMethod.POST, URI.create(url));

        ResponseEntity<WebResponse<Guest>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public Address createAddress(Address address, String guestId) {
        String url = SERVER + "/api/guests/"+guestId+"/addresses";

        RequestEntity<Address> request =
                new RequestEntity<>(address, HttpMethod.POST, URI.create(url));

        ResponseEntity<WebResponse<Address>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public Institution createInstitution(Institution institution, String guestId) {
        String url = SERVER + "/api/guests/"+guestId+"/institutions";

        RequestEntity<Institution> request =
                new RequestEntity<>(institution, HttpMethod.POST, URI.create(url));

        ResponseEntity<WebResponse<Institution>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public Need createNeed(Need need, String guestId) {
        String url = SERVER + "/api/guests/"+guestId+"/needs";

        RequestEntity<Need> request =
                new RequestEntity<>(need, HttpMethod.POST, URI.create(url));

        ResponseEntity<WebResponse<Need>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }
}
