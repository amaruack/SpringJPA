package com.example.springboot;

import com.example.springboot.config.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class HibernateTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("sdf");
//            em.persist(member);
            Member member = em.find(Member.class, 1L);
//            member.setName("sdf");
            em.clear();

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
