package com.gdsc.skhufp.closet.service;
import com.gdsc.skhufp.auth.service.CustomUserDetailService;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.closet.domain.entity.Cloth;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.domain.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothService {
    private final CustomUserDetailService customUserDetailService;
    private final ClothRepository clothRepository;

    @Transactional
    public ClothDTO save(Principal principal, ClothDTO dto) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());

        Cloth cloth = Cloth.builder()
                .imageUrl(dto.imageUrl())
                .type(dto.type())
                .seasons(dto.seasons())
                .user(user)
                .name(dto.name())
                .comment(dto.comment())
                .build();

        return clothRepository.save(cloth).toDTO();
    }

    /*//모든 cloth 검색
    @Transactional(readOnly = true) // 읽기전용
    public List<ClothDTO> findAll() {
        List<Cloth> clothes = clothRepository.findAll();

        return  clothes.stream()// clothes List에서 스트림을 얻어서
                .map(Cloth::toDTO)//toDTO method로 cloth를 ClothDTO로 변환
                .collect(Collectors.toList());// 스트림을 List 형태로 변경
    }*/

    // User 컬럼으로 id 검색
    @Transactional(readOnly = true)
    public List<ClothDTO> findAllByUserName(Principal principal) {
        //username로 user 찾기
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());
        //획득한 user으로 cloth 찾기
        List<Cloth> clothes = clothRepository.findAllByUser(user);

        return clothes.stream()
                .map(Cloth::toDTO)
                .collect(Collectors.toList());
    }

    // cloth 검색
    @Transactional(readOnly = true)
    public ClothDTO findById(Long id) {//id로 cloth 검색

        return findEntityById(id).toDTO();//id로 찾아 DTO로 변환해서 리턴
    }

    //cloth 객체 update
    @Transactional
    public ClothDTO updateById(Long id, ClothDTO dto) {
        //id로 cloth를 찾아 dto로 바꿔서 update 후 리턴
        Cloth cloth = findEntityById(id);
        cloth.update(dto);

        return clothRepository.saveAndFlush(cloth).toDTO();
    }

    //cloth 객체 delete

    @Transactional
    public void deleteById(Long id) {
        Cloth cloth = findEntityById(id);
        clothRepository.delete(cloth);
    }

    @Transactional(readOnly = true)
    Cloth findEntityById(Long id) {
        return clothRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID로 옷을 찾을 수 없음"));
    }
}
