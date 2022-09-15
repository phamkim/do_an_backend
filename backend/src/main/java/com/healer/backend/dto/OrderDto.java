package com.healer.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDto {

    private UUID id;

    @NotEmpty(message = "not blank")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @NotEmpty(message = "not blank")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @NotEmpty(message = "not blank")
    private String state;

    @NotEmpty(message = "not blank")
    private List<OrderDetailDto> orderDetailDtoList;

    @NotEmpty(message = "not blank")
    private UserDto userDto;


}
