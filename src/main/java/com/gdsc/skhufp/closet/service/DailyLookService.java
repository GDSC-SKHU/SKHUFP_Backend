package com.gdsc.skhufp.closet.service;

import com.gdsc.skhufp.auth.domain.entity.User;
import com.gdsc.skhufp.auth.service.CustomUserDetailService;
import com.gdsc.skhufp.closet.domain.entity.Cloth;
import com.gdsc.skhufp.closet.domain.entity.DailyLook;
import com.gdsc.skhufp.closet.domain.repository.ClothRepository;
import com.gdsc.skhufp.closet.domain.repository.DailyLookRepository;
import com.gdsc.skhufp.closet.dto.request.DailyLookRequest;
import com.gdsc.skhufp.closet.dto.response.DailyLookResponse;
import com.gdsc.skhufp.common.exception.auth.NoPermissionException;
import com.gdsc.skhufp.common.exception.closet.DailyLookNotFoundException;
import com.gdsc.skhufp.common.exception.closet.LimitedNumberOfClothTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DailyLookService {
    private final ClothRepository clothRepository;
    private final DailyLookRepository dailyLookRepository;
    private final CustomUserDetailService customUserDetailService;

    @Transactional
    public DailyLookResponse save(Principal principal, DailyLookRequest request) {
        User user = (User) customUserDetailService.loadUserByUsername(principal.getName());

        List<Cloth> cloths = clothRepository.findAllByUserAndIdIn(user, request.clothIds());
        typeCountCheck(cloths);

        DailyLook dailyLook = DailyLook.builder()
                .name(request.name())
                .comment(request.comment())
                .user(user)
                .cloths(cloths)
                .build();

        return dailyLookRepository.save(dailyLook).toResponseDTO();
    }

    @Transactional(readOnly = true)
    public List<DailyLookResponse> findAll(Principal principal) {
        List<DailyLook> dailyLooks = dailyLookRepository.findAllByUser((User) customUserDetailService.loadUserByUsername(principal.getName()));

        return dailyLooks.stream()
                .map(DailyLook::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DailyLookResponse findById(Long id, Principal principal) {
        DailyLook dailyLook = findEntityById(id);
        permissionCheck(principal, dailyLook);

        return dailyLook.toResponseDTO();
    }

    @Transactional
    public DailyLookResponse updateById(Long id, Principal principal, DailyLookRequest request) {
        DailyLook dailyLook = findEntityById(id);
        permissionCheck(principal, dailyLook);

        List<Cloth> cloths = clothRepository.findAllByUserAndIdIn((User) customUserDetailService.loadUserByUsername(principal.getName()), request.clothIds());
        typeCountCheck(cloths);

        dailyLook.update(request, cloths);

        return dailyLookRepository.save(dailyLook).toResponseDTO();
    }

    @Transactional
    public void deleteById(Long id, Principal principal) {
        DailyLook dailyLook = findEntityById(id);
        permissionCheck(principal, dailyLook);

        dailyLookRepository.delete(dailyLook);
    }

    // id로 DailyLook을 찾으며 못찾으면 404 예외를 발생
    @Transactional(readOnly = true)
    DailyLook findEntityById(Long id) {
        return dailyLookRepository.findById(id)
                .orElseThrow(DailyLookNotFoundException::new);
    }

    // 매개변수로 받은 Principal과 dailyLook의 유저를 비교하여 일치하지않으면 403 예외를 발생
    private void permissionCheck(Principal principal, DailyLook dailyLook) {
        if (!dailyLook.getUser().getUsername().equals(principal.getName())) {
            throw new NoPermissionException();
        }
    }

    // 옷들의 각 타입 개수를 파악하여 2개 이상이면 400 예외를 발생합니다.
    private void typeCountCheck(List<Cloth> cloths) {
        // 각 인덱스 정보 = 0: TOPS, 1: BOTTOMS, 2: OUTERWEAR, 3: SHOES, 4: BAGS
        int[] countTypes = new int[5];

        for (Cloth cloth : cloths) {
            switch (cloth.getType()) {
                case TOPS -> countTypes[0]++;
                case BOTTOMS -> countTypes[1]++;
                case OUTERWEAR -> countTypes[2]++;
                case SHOES -> countTypes[3]++;
                case BAGS -> countTypes[4]++;
            }
        }

        for (int count : countTypes) {
            if (count > 1) {
                throw new LimitedNumberOfClothTypeException();
            }
        }
    }
}
