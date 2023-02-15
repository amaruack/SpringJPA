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
