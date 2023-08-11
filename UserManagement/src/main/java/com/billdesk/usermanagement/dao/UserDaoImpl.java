package com.billdesk.usermanagement.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.billdesk.usermanagement.entity.User;

@Repository
public class UserDaoImpl {
	
	private EntityManager em;
	
	public User save(User user) {
        Session session = em.unwrap(Session.class);
        session.persist(user);
        return user;
    }


}
