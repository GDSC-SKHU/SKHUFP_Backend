package com.gdsc.skhufp.closet.repository;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.closet.entity.Cloth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;
import java.util.List;


public interface ClothRepository extends JpaRepository<Cloth, Long> {
    List<Cloth> findAllByUser(User user);

}