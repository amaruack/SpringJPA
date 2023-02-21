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
//                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member);

            Order order1 = Order.builder()
                    .memberId(member.getId())
                    .member(member)
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER)
                    .build();
            em.persist(order1);

            Order order2 = Order.builder()
                    .memberId(member.getId())
                    .member(member)
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER)
                    .build();
            em.persist(order2);

            Member member2 = Member.builder()
                    .name("son1")
                    .address(Address.builder().street("집1").build())
                    .favoriteFoods(List.of("치킨", "족발"))
//                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member2);

            Order order3 = Order.builder()
                    .memberId(member2.getId())
                    .member(member2)
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER)
                    .build();
            em.persist(order3);

            Order order4 = Order.builder()
                    .memberId(member2.getId())
                    .member(member2)
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER)
                    .build();
            em.persist(order4);

            Member member3 = Member.builder()
                    .name("son2")
                    .address(Address.builder().street("외부").build())
                    .favoriteFoods(List.of("치킨", "족발"))
//                    .addressHistory(List.of(Address.builder().street("old1").build(), Address.builder().street("old1").build()))
                    .build();
            em.persist(member3);

            em.flush();
            em.clear();

//
//            String query = "select o from Order o join fetch o.member ";
//            List<Order> list = em.createQuery(query, Order.class).getResultList();
//
//            for (Order result : list) {
//                System.out.println(result.getId());
//                System.out.println(result.getMember().getName());
//            }

            String query = "select distinct m from Member m join fetch m.orders ";
            List<Member> list = em.createQuery(query, Member.class).getResultList();

            for (Member result : list) {
                System.out.println(result.getId());
                System.out.println(result.getName());
                System.out.println(result.getOrders().size());
            }


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
