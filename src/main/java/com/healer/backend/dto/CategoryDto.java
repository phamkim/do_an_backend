package com.healer.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private UUID id;

    @NotBlank(message = "name not blank")
    private String name;

    @NotBlank(message = "image not blank")
    private String image;

}
