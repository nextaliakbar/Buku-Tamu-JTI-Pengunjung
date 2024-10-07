package com.webapps.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution {

    private String name;

    private String noTelp;

    private String noFax;

    private Guest guest;
}
