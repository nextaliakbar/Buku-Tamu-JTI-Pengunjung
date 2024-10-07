package com.webapps.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    private String id;

    @NotEmpty(message = "Silahkan pilih salah satu")
    private String guestType;

    @NotEmpty(message = "Bidang ini harus di isi")
    private String name;

    @NotEmpty(message = "Silahkan pilih salah satu")
    private String gender;

    @NotEmpty(message = "Bidang ini harus di isi")
    private String placeOfBirth;

    @NotEmpty
    private String dateOfBirth;

    @NotEmpty(message = "Bidang ini harus di isi")
    private String noHp;

    private String noTelp;

    private String email;

    private String position;
}
