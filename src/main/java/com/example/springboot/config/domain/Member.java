package com.example.springboot.config.domain;

import com.example.springboot.config.common.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member", uniqueConstraints = {})
// sequence 전략
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1,
        allocationSize = 1 // 성능 최적화에 사용 함, 증가값을 50 으로 해놓으면 id 값이 50번 까지 올라갈때까지 query 는 1번 호출 된다.
)
// table 전략
@TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SE", initialValue = 1, allocationSize = 1)
public class Member {

    @Id
    // sequence 전략
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    // table 전략
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Lob
    private String description;

}
