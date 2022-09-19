package com.healer.do_an_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImageDto {

    private UUID id;

    @NotBlank(message = "name not blank")
    private String name;

    @NotBlank(message = "type not blank")
    private String type;

    @NotBlank(message = "value not blank")
    private byte[] value;

}
