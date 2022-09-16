package com.healer.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "order_date") // ngày tạo đơn hàng
    private LocalDate orderDate;

    @Column(name = "payment_date") // ngày thanh toán đơn hàng
    private LocalDate paymentDate;

    /*
     * trạng thái đơn hàng: inCard, ordered, packed, shipping, delivered
     * nếu state = inCart thì đang trong giỏ hàng
     * */
    @Column(name = "state")
    private String state;

    /*
     *   1 order có nhiều orderDetail
     *  CascadeType.ALL : khi xóa order thì sẽ xóa luôn các order_detail
     * */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    /*
     *  nhiều order có cùng 1 user
     * */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


}
