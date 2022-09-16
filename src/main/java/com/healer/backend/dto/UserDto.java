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

    @NotBlank(message = "name not blank")
    private String name;

    @NotBlank(message = "email not blank")
    @Email()
    private String email;

    @NotBlank(message = "password not blank")
    @Min(value = 6)
    @Max(value = 10)
    private String password;

//    @NotEmpty(message = "birthday not empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;

//    @NotBlank(message = "gender not blank")
    private String gender;

//    @NotEmpty(message = "avatar not empty")
    private String avatar;

//    @NotBlank(message = "address not blank")
    private String address;

//    @NotBlank(message = "phone not blank")
    @Digits(integer = 10, fraction = 0)
    private String phone;

//    @NotBlank(message = "permission not blank")
    private String permission;

}
