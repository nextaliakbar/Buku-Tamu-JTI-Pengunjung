package com.webapps.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Need {

    @NotBlank(message = "Bidang ini harus diisi")
    private String title;

    private LocalDateTime createdAt;

    private String description;
}
