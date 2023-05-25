package com.practice.spring_boot_app.javatpoint.repositories;

import com.practice.spring_boot_app.javatpoint.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
