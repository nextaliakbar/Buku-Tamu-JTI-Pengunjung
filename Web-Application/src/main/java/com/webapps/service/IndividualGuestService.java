package com.webapps.service;

import com.webapps.entity.Address;
import com.webapps.entity.Guest;
import com.webapps.entity.IndividualGuest;
import com.webapps.entity.Need;
import com.webapps.model.WebResponse;
import com.webapps.repository.IndividualGuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.Objects;

@Slf4j
@Service
public class IndividualGuestService implements IndividualGuestRepository {

    @Autowired
    private RestTemplate restTemplate;

    private String SERVER = "http://localhost:8080";

    @Override
    public IndividualGuest create(IndividualGuest individualGuest) {
        Guest guest = createGuest(individualGuest.getGuest());
        Address address = createAddress(individualGuest.getAddress(), guest.getId());
        Need need = createNeed(individualGuest.getNeed(), guest.getId());
        return IndividualGuest.builder().guest(guest).address(address)
                .need(need).build();
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
    public Need createNeed(Need need, String guestId) {
        String url = SERVER + "/api/guests/"+guestId+"/needs";

        RequestEntity<Need> request =
                new RequestEntity<>(need, HttpMethod.POST, URI.create(url));

        ResponseEntity<WebResponse<Need>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }

}
