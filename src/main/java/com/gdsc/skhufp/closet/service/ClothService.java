package com.gdsc.skhufp.closet.service;
import com.gdsc.skhufp.auth.domain.repository.UserRepository;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.closet.entity.Cloth;
import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.closet.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final UserRepository userRepository;
    private final ClothRepository clothRepository;

    @Transactional
    public ClothDTO save(Principal principal, ClothDTO dto) {
        String user = principal.getName();

        Cloth cloth = Cloth.builder()
                .name(dto.getName())
                .image_url(dto.getImage_url())
                .type(dto.getType())
                .comment(dto.getComment())
                .user(userRepository.findByUsername(user).get())
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


    //User 컬럼으로 id 검색
    @Transactional(readOnly = true)
    public List<ClothDTO> findAllByUserName(Principal principal) {
        String prin = principal.getName();
        //username로 user 찾기
        User user = userRepository.findByUsername(prin)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Name로 User 찾을 수 없음"));
        //획득한 user으로 cloth 찾기
        List<Cloth> clothes = clothRepository.findAllByUser(user);

        return
                clothes.stream()
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
        Cloth cloth = findEntityById(id);
        cloth.update(dto);

        return clothRepository.saveAndFlush(cloth).toDTO();
        //id로 cloth를 찾아 dto로 바꿔서 update 후 리턴
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
