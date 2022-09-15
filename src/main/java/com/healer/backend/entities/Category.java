package com.healer.backend.entities;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorys")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;


    /*
    * 1 danh phục sản phẩm thì có nhiều sản phẩm
    * */
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> products;

}
