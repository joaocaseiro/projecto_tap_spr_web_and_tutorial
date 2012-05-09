package com.grupoatwork.celebrities.repository.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
 
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.grupoatwork.celebrities.model.County;
import com.grupoatwork.celebrities.model.District;

public class DistrictTest {
	
	private EntityManagerFactory emf;

	private EntityManager em;

	@Before
	public void initEmfAndEm() {
		BasicConfigurator.configure();
		//Logger.getLogger("org").setLevel(Level.ERROR);

		emf = Persistence.createEntityManagerFactory("ComplexCelebrity");
		em = emf.createEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void emptyTest() {
	}
	
	@SuppressWarnings("unchecked")
    @Test
    public void testDistrict() {
		District d1 = new District();
		d1.setCode(1);
		d1.setName("Porto");
		
        em.getTransaction().begin();
        em.persist(d1);
        em.getTransaction().commit();
 
        List<District> list = em.createQuery("select d from District d")
                .getResultList();
 
        assertEquals(1, list.size());
        for (District current : list) {
            final String firstName = current.getName();
            assertTrue(firstName.equals("Porto")
                    || firstName.equals("FirstName"));
        }
        
        d1.setName("Lisboa");
        
        em.getTransaction().begin();
        em.merge(d1);
        em.getTransaction().commit();
        
        list = em.createQuery("select d from District d")
                .getResultList();
        
        assertEquals(1, list.size());
        for (District current : list) {
            final String firstName = current.getName();
            assertTrue(firstName.equals("Lisboa")
                    || firstName.equals("FirstName"));
        }
        
        em.getTransaction().begin();
        em.remove(d1);
        em.getTransaction().commit();
        
        list = em.createQuery("select d from District d")
                .getResultList();
        
        assertEquals(0, list.size());
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCounty() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.clear();
		transaction.commit();
		
		District d1 = new District();
		d1.setCode(1);
		d1.setName("Porto");
		
		County c1 = new County();
		c1.setCode(1);
		c1.setName("Porto");
		c1.setDistrict(d1);
		
		try {
			transaction.begin();
			em.persist(c1);
			transaction.commit();
			Assert.fail();
		} catch (Exception e) {
			transaction.rollback();
		}
		
		transaction.begin();
		em.persist(d1);
		transaction.commit();
		
		transaction.begin();
		em.merge(c1);
		transaction.commit();
		
		List<County> list = em.createQuery("select c from County c")
				.getResultList();
		
		assertEquals(1, list.size());
		for (County current : list) {
			final String firstName = current.getName();
			assertTrue(firstName.equals("Porto")
					|| firstName.equals("FirstName"));
			c1 = current;
		}
		
		transaction.begin();
		
		System.err.println(c1.getId() + " : " + c1);
		c1 = em.find(County.class, c1.getId());
		
		em.remove(c1);
		transaction.commit();
		
		list = em.createQuery("select c from County c")
				.getResultList();
		
		assertEquals(0, list.size());
		
		list = em.createQuery("select d from District d")
				.getResultList();
		
		assertEquals(1, list.size());	//delete cascade
	}
}
