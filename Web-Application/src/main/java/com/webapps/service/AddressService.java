package com.webapps.service;

import com.webapps.entity.Province;
import com.webapps.model.WebResponse;
import com.webapps.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class AddressService implements AddressRepository {

    private String SERVER = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Province> getAllProvinces() {
        String url = SERVER + "/api/provinces";

        RequestEntity<List<Province>> request =
                new RequestEntity<>(HttpMethod.GET, URI.create(url));

        ResponseEntity<WebResponse<List<Province>>> response =
              restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).getData();
    }
}
