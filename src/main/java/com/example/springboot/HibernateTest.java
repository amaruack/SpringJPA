package com.example.springboot;

import com.example.springboot.config.common.OrderStatus;
import com.example.springboot.config.common.RoleType;
import com.example.springboot.config.domain.Address;
import com.example.springboot.config.domain.Member;
import com.example.springboot.config.domain.Order;
import com.example.springboot.config.domain.Period;
import com.example.springboot.config.domain.item.Album;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class HibernateTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();

        try {
            tx.begin();

            Member member = Member.builder()
                    .name("son")
                    .address(Address.builder().street("집").build())
                    .favoriteFoods(List.of("치킨", "족발"))
                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member);

            Member member2 = Member.builder()
                    .name("son1")
                    .address(Address.builder().street("집1").build())
                    .favoriteFoods(List.of("치킨", "족발"))
                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member2);

            Member member3 = Member.builder()
                    .name("son2")
                    .address(Address.builder().street("외부").build())
                    .favoriteFoods(List.of("치킨", "족발"))
                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member3);

            em.flush();
            em.clear();

            List<Member> list = em.createQuery("select m from Member m where m.address.street like '집%' ", Member.class).getResultList();

            list.stream().forEach(m -> System.out.println(m.getName()));

            System.out.println();



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
