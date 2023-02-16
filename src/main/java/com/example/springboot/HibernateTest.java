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
//                    .orders()
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

            Member findMember = em.find(Member.class, member.getId());
            findMember.getOrders().remove(0);


            Address address = Address.builder().city("seoul").build();

            Member embbed = Member.builder()
                    .name("sss")
                    .period(Period.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).build())
                    .address(address)
                    .build();
            em.persist(embbed);

            Member embbed2 = Member.builder()
                    .name("222")
                    .period(Period.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).build())
                    .address(address)
                    .build();
            em.persist(embbed2);

            embbed2.getAddress().setStreet("!!");

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
