package com.healer.backend.dto;

import com.healer.backend.entities.Order;
import com.healer.backend.entities.OrderDetail;
import com.healer.backend.entities.Product;
import com.healer.backend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDetailDto {
    private UUID id ;

    @NotEmpty(message = "not blank")
    private float quantity;

    @NotEmpty(message = "not blank")
    private ProductDto productDto;

    @NotEmpty(message = "not blank")
    private OrderDto orderDto;

}
