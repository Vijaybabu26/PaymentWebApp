package com.babu.fooddelivery.repository;







import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.babu.fooddelivery.entity.User;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer>{
//	 User findByPhno(String phoneNo);
}
