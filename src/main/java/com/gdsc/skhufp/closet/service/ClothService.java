package com.gdsc.skhufp.closet.service;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.auth.service.CustomUserDetailService;
import com.gdsc.skhufp.closet.domain.entity.Cloth;
import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;
import com.gdsc.skhufp.closet.domain.repository.ClothRepository;
import com.gdsc.skhufp.closet.dto.request.ClothRequest;
import com.gdsc.skhufp.closet.dto.response.ClothResponse;
import com.gdsc.skhufp.common.exception.auth.NoPermissionException;
import com.gdsc.skhufp.common.exception.closet.ClothNotFoundException;
import com.gdsc.skhufp.common.exception.closet.ImageAlreadyOnClothException;
import com.gdsc.skhufp.common.exception.closet.NoImageOnClothException;
import com.gdsc.skhufp.storage.domain.model.ImageFile;
import com.gdsc.skhufp.storage.service.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothService {
    private final CustomUserDetailService customUserDetailService;
    private final ImageFileService imageFileService;
    private final ClothRepository clothRepository;

    @Transactional
    public ClothResponse save(Principal principal, ClothRequest dto) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());

        Cloth cloth = Cloth.builder()
                .type(dto.type())
                .seasons(dto.seasons())
                .user(user)
                .name(dto.name())
                .comment(dto.comment())
                .build();

        return clothRepository.save(cloth).toResponseDTO();
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
    public List<ClothResponse> findAllByUserName(Principal principal) {
        //username로 user 찾기
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());
        //획득한 user으로 cloth 찾기
        List<Cloth> clothes = clothRepository.findAllByUser(user);

        return clothes.stream()
                .map(Cloth::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClothResponse> findAllByUserNameAndType(Principal principal, ClothType type) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());
        List<Cloth> cloths = clothRepository.findAllByUserAndType(user, type);

        return cloths.stream()
                .map(Cloth::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClothResponse> findAllByUserNameAndSeasons(Principal principal, Set<Season> seasons) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());
        List<Cloth> cloths = clothRepository.findAllByUserAndSeasonsIn(user, seasons);

        return cloths.stream()
                .map(Cloth::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClothResponse> findAllByUserNameAndTypeAndSeasons(Principal principal, ClothType type, Set<Season> seasons) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());
        List<Cloth> cloths = clothRepository.findAllByUserAndTypeAndSeasonsIn(user, type, seasons);

        return cloths.stream()
                .map(Cloth::toResponseDTO)
                .collect(Collectors.toList());
    }

    // cloth 검색
    @Transactional(readOnly = true)
    public ClothResponse findById(Long id, Principal principal) {//id로 cloth 검색
        Cloth cloth = findEntityById(id);

        permissionCheck(principal, cloth);

        return findEntityById(id).toResponseDTO();//id로 찾아 DTO로 변환해서 리턴
    }

    //cloth 객체 update
    @Transactional
    public ClothResponse updateById(Long id, Principal principal, ClothRequest request) {
        //id로 cloth를 찾아 dto로 바꿔서 update 후 리턴
        Cloth cloth = findEntityById(id);

        permissionCheck(principal, cloth);

        cloth.update(request);

        return clothRepository.save(cloth).toResponseDTO();
    }

    //cloth 객체 delete

    @Transactional
    public void deleteById(Long id, Principal principal) {
        Cloth cloth = findEntityById(id);

        permissionCheck(principal, cloth);

        if (cloth.getImage() != null) {
            imageFileService.deleteById(cloth.getImage().getId());
        }
        clothRepository.delete(cloth);
    }

    @Transactional
    public ClothResponse uploadImageById(Long id, Principal principal, MultipartFile file) {
        Cloth cloth = findEntityById(id);

        permissionCheck(principal, cloth);

        ImageFile image = imageFileService.save(file);

        if (cloth.getImage() != null) {
            throw new ImageAlreadyOnClothException();
        }

        cloth.setImage(image);

        return clothRepository.save(cloth).toResponseDTO();
    }

    @Transactional
    public void deleteImageById(Long id, Principal principal) {
        Cloth cloth = findEntityById(id);

        permissionCheck(principal, cloth);

        if (cloth.getImage() == null) {
            throw new NoImageOnClothException();
        }

        imageFileService.deleteById(cloth.getImage().getId());
    }

    @Transactional(readOnly = true)
    Cloth findEntityById(Long id) {
        return clothRepository.findById(id)
                .orElseThrow(ClothNotFoundException::new);
    }

    private void permissionCheck(Principal principal, Cloth cloth) {
        if (!cloth.getUser().getUsername().equals(principal.getName())) {
            throw new NoPermissionException();
        }
    }
}
