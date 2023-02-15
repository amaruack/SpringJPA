package com.example.springboot.config.domain;

import com.example.springboot.config.common.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

public class Member extends SuperEntity implements Serializable {

    @Id
    // sequence 전략
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "id")
    // table 전략
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;



    @Lob
    private String description;

    @OneToMany(mappedBy = "member")
    List<Order> orders;


}
