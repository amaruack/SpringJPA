package com.example.springboot;

import com.example.springboot.config.common.OrderStatus;
import com.example.springboot.config.common.RoleType;
import com.example.springboot.config.domain.Member;
import com.example.springboot.config.domain.Order;
import com.example.springboot.config.domain.item.Album;
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

        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();

        try {
            tx.begin();

            Album album = Album.builder()
                    .name("album")
                    .price(2424)
                    .artist("artist")
                    .build();
            em.persist(album);

            em.flush();
            em.clear();

            Album find = em.find(Album.class, album.getId());

            System.out.printf("dsafdf");

            Member member = Member.builder()
                    .name("wetewt")
                    .build();

            em.persist(member);
            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member.getId());
            System.out.println("init ?? "+persistenceUnitUtil.isLoaded(reference));
            Member find2 = em.find(Member.class, member.getId());
            System.out.println();
//            System.out.println(reference.getName());
            System.out.println("reference class = "+reference.getClass());
//            System.out.println("reference class = "+reference.getName());

            System.out.println("init ?? "+persistenceUnitUtil.isLoaded(reference));

            System.out.println(find2.getName());
            System.out.println("find class = "+find2.getClass());

            Order order = Order.builder()
                    .memberId(member.getId())
                    .orderDate(LocalDateTime.now())
                    .build();
            em.persist(order);

            em.flush();
            em.clear();

            Order fineOrder = em.find(Order.class, order.getId());
            System.out.println(fineOrder.getOrderDate().toString());
            System.out.println("member class = "+fineOrder.getMember().getClass());
            System.out.println("member getName = "+fineOrder.getMember().getName());

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
