package com.example.springboot;

import com.example.springboot.config.common.OrderStatus;
import com.example.springboot.config.common.RoleType;
import com.example.springboot.config.domain.Member;
import com.example.springboot.config.domain.Order;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class HibernateTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            em.persist(Member.builder()
                    .name("1111")
                    .age(12)
                    .roleType(RoleType.ADMIN)
                    .createAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .description("adsfdasf")
                    .build());

            em.persist(Member.builder()
                    .name("222")
                    .age(12)
                    .roleType(RoleType.ADMIN)
                    .createAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .description("adsfdasf")
                    .build());

            em.flush();
            em.clear();

            Member find = em.find(Member.class, 2L);

//            LocalDateTime orderDate;
//            OrderStatus status;

            em.persist(Order.builder()
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER)
                    .memberId(2L)
//                    .member(find)
                    .build());

            em.flush();
            em.clear();

            Order findOrder = em.find(Order.class, 1L);
            System.out.printf("");
//
//            // managed 상태
//            Member member = em.find(Member.class, 1L);
//            em.clear(); // detached 상태
//
//            // 다시 managed 상태 // query 2번 날라감
//            Member member2 = em.find(Member.class, 1L);
//
//            log.info("equals {}", member == member2);

            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();

    }
}
