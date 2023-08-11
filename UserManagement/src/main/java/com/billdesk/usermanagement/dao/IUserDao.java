	package com.billdesk.usermanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.billdesk.usermanagement.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User,Integer>{

	
	public User findByEmail(String email);

	public User save(Optional<User> user);
	
	@Query(value="select * from users1 where email=?1",nativeQuery=true)
	Optional<User> findUserByEmail(String email);
	
	
}
