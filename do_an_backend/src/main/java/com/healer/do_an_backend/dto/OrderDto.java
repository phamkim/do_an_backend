package com.healer.do_an_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healer.do_an_backend.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDto {

    private UUID id;

    @NotBlank(message = "not blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate orderDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate paymentDate;

    @NotBlank(message = "state not blank")
    private String state;

    @NotEmpty(message = "user not empty")
    private UserDto user;

    private List<OrderDetailDto> orderDetails;

    public static OrderDto toDto(Order order){
        OrderDto orderDto = new ModelMapper().map(order,OrderDto.class);
        orderDto.setUser(new ModelMapper().map(order.getUser(), UserDto.class));
        orderDto.setOrderDetails(order.getOrderDetails().stream().map(
                orderDetail -> {
                    return new ModelMapper().map(orderDetail,OrderDetailDto.class);
                }).collect(Collectors.toList()));
        return  orderDto;
    }


}
