package com.example.springboot;

import com.example.springboot.config.common.RoleType;
import com.example.springboot.config.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
                    .id(ThreadLocalRandom.current().nextLong(1000))
                    .age(12)
                    .roleType(RoleType.ADMIN)
                    .build());

            em.flush();

            // managed 상태
            Member member = em.find(Member.class, 1L);
            em.clear(); // detached 상태

            // 다시 managed 상태 // query 2번 날라감
            Member member2 = em.find(Member.class, 1L);

            log.info("equals {}", member == member2);

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
