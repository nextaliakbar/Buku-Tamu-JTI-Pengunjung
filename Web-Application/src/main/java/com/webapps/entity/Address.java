package com.webapps.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank(message = "Bidang ini harus diisi")
    private String country;

    private String province;

    private String city;

    private String subdistrict;

    private String description;

}
