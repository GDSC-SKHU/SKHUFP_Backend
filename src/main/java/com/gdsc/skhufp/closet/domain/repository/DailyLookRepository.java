package com.gdsc.skhufp.closet.domain.repository;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.domain.entity.DailyLook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyLookRepository extends JpaRepository<DailyLook, Long> {
    List<DailyLook> findAllByUser(User user);
}