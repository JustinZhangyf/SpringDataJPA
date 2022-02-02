package com.justin.test;

import com.justin.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {

    EntityManagerFactory factory;

    @Before
    public void before() {
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    @Test
    public void test_C(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.persist(new Customer("jpa","citi"));

        tx.commit();
    }

    @Test
    public void test_R(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Customer customer = em.find(Customer.class, 5L);

        tx.commit();

        System.out.println(customer);
    }

    @Test
    public void test_Lazy_R(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Customer customer = em.merge(new Customer());

        tx.commit();

        System.out.println(customer);
    }

    @Test
    public void test_U(){
        EntityManager em = factory.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Customer customer = new Customer();
        customer.setCustId(5L);
        customer.setCustName("Update");

        em.merge(customer);
        tx.commit();
    }

    @Test
    public void test_JPQL(){
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        String jpql = "UPDATE Customer set custAddress=:address where custId=:id";

        em.createQuery(jpql).setParameter("address","chenhuiRoad")
                            .setParameter("id",5l)
                            .executeUpdate();

        tx.commit();

    }

    @Test
    public void test_SQL(){
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        String sql = "UPDATE cst_customer set cust_address=:address where id=:id";

        em.createNativeQuery(sql).setParameter("address","chenhuiRoad")
                                 .setParameter("id",6l)
                                 .executeUpdate();

        tx.commit();

    }
}
