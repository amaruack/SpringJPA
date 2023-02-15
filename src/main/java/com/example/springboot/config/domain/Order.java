package com.example.springboot.config.domain;

import com.example.springboot.config.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Order extends SuperEntity {

    @Id
    @GeneratedValue
//    @Column
    Long id;

    @Column(name = "member_id")
    Long memberId;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @Column
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    Member member;

//    @OneToMany(mappedBy = "order")
//    List<OrderItem> orderItems;

}
