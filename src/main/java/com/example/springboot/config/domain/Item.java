package com.example.springboot.config.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 해당 애노테이션은 DTYPE 을 넣어준다. 하위 객체의 타입을 넣어준다.
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    Integer price;
    @Column(name = "stock_quantity")
    Integer stockQuantity;


}
