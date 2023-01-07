package com.gdsc.skhufp.closet.repository;

import com.gdsc.skhufp.closet.entity.Cloth;
import com.gdsc.skhufp.closet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClothRepository extends JpaRepository<Cloth, Long> {
    List<Cloth> findAllByUser(User user);
}