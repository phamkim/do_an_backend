package com.healer.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private UUID id;

    @NotEmpty(message = "not blank")
    private String name;

    @NotEmpty(message = "not blank")
    @Email()
    private String email;

    @NotEmpty(message = "not blank")
    @Min(value = 6)
    @Max(value = 10)
    private String password;

    @NotEmpty(message = "not blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;

    @NotEmpty(message = "not blank")
    private String gender;

    @NotEmpty(message = "not blank")
    private String avatar;

    @NotEmpty(message = "not blank")
    private String address;

    @NotEmpty(message = "not blank")
    @Digits(integer = 10, fraction = 0)
    private String phone;

    @NotEmpty(message = "not blank")
    private String permission;


//    private List<OrderDto> orderDtoList = new ArrayList<>();


}
