package com.example.springboot.config.domain;

import com.example.springboot.config.common.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member")
// sequence 전략
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1,
        allocationSize = 1 // 성능 최적화에 사용 함, 증가값을 50 으로 해놓으면 id 값이 50번 까지 올라갈때까지 query 는 1번 호출 된다.
)
// table 전략
//@TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SE", initialValue = 1, allocationSize = 1)

public class Member extends BaseEntity implements Serializable {

    @Id
    // sequence 전략
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "id")
    // table 전략
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String description;

    @Embedded
    private Period period;

    @Embedded
    private Address address;

    // orphanRemoval = true 시 자식 객체 삭제하면 엔티티도 삭제됨 delete 날라감
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders ;

    // locker id 값으로 하고 싶으면 해당 조건 사용
//    @Column(name = "lock_id")
    // locker id 값으로 mapping을 하지않고 아래의 객체 형태로 할경우 insertable = false, updatable = false 입력
    @Column(name = "lock_id", insertable = false, updatable = false )
    private Long lockId;

    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lock_id", referencedColumnName = "id", insertable = false, updatable = false, unique = true)
    @JoinColumn(name = "lock_id", referencedColumnName = "id", unique = true)
    Locker locker;

    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    List<String> favoriteFoods;


    /**
     * 사용 하지 않는걸 추천
     * 사용할려면 value type 인 Address 를 entity 로 wrapping 해서 사용한다.  ex ) AddressEntity
     */
    @OrderColumn(name = "address_history_order")
    @ElementCollection
    @CollectionTable(name = "address_history", joinColumns = @JoinColumn(name = "member_id"))
    List<Address> addressHistory;



}
