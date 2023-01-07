package com.gdsc.skhufp.closet.repository;

import com.gdsc.skhufp.closet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {
        //Optional<User> findById(Long id);
}