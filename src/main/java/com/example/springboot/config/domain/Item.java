package com.example.springboot.config.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 해당 애노테이션은 DTYPE 을 넣어준다. 하위 객체의 타입을 넣어준다.
@DiscriminatorColumn()
public abstract class Item {

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
