package com.gdsc.skhufp.closet.domain.repository;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.domain.entity.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClothRepository extends JpaRepository<Cloth, Long> {
    List<Cloth> findAllByUser(User user);

}