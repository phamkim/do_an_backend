package com.healer.backend.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    /*
    * phân quyền cho user: admin, employee, customer, shipper
    * */
    @Column(name = "permission")
    private String permission;

    /*
    *  1 user thì sẽ có nhiều order
    * */
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Order> orders;


}
