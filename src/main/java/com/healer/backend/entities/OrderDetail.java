package com.healer.backend.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDetail {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "quantity") // số lượng sản phẩm
    private float quantity;

    /*
     * thông tin product (id => name, price, discount)
     * FetchType.EAGER: để lấy thêm các trường của product
     * */
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;


    /*
     *   thông tin order (id)
     *   nhiều orderDetail thì có chung 1 order
     * */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order order;

}
