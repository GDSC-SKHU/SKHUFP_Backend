package com.gdsc.skhufp.closet.domain.repository;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.domain.entity.Cloth;
import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface ClothRepository extends JpaRepository<Cloth, Long> {
    List<Cloth> findAllByUser(User user);
    List<Cloth> findAllByUserAndType(User user, ClothType type);
    List<Cloth> findAllByUserAndSeasons(User user, Set<Season> seasons);
    List<Cloth> findAllByUserAndTypeAndSeasons(User user, ClothType type, Set<Season> seasons);
}