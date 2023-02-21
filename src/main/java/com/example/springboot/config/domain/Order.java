package com.example.springboot.config.domain;

import com.example.springboot.config.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "member_id", insertable = false, updatable = false)
    Long memberId;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @Column
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    Delivery delivery;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;


}
