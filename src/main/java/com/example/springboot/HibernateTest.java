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


            member.setOrders(
                    List.of(Order.builder()
                                    .orderDate(LocalDateTime.now())
                                    .status(OrderStatus.ORDER)
                                    .member(member)
                                    .build(),
                            Order.builder()
                                    .orderDate(LocalDateTime.now())
                                    .status(OrderStatus.CANCEL)
                                    .member(member)
                                    .build())
            );

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println(findMember.getAddress().getStreet());

            findMember.getFavoriteFoods().stream().forEach(System.out::println);
            findMember.getAddressHistory().stream().forEach(add -> add.toString());

            System.out.println("sdf");

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
