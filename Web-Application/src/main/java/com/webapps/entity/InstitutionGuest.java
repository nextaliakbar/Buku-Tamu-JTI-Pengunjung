package com.webapps.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionGuest {

    private Guest guest;

    private Institution institution;

    private Address address;

    private Need need;
}
