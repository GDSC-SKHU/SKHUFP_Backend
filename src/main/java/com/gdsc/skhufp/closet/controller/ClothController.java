package com.gdsc.skhufp.closet.controller;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.closet.entity.Cloth;
import com.gdsc.skhufp.closet.service.ClothService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.security.Principal;
@RestController
@RequiredArgsConstructor
public class ClothController {

    private final ClothService clothService;
    // 생성
    @PostMapping("/api/clothes")
    public ResponseEntity<ClothDTO> save(Principal principal,@RequestBody ClothDTO request)
    {
        ClothDTO response =clothService.save(principal,request);
        return ResponseEntity.created(URI.create("/api/clothes/"+response.getId()))
                .body(response);
    }
    // 전제 조회
    @GetMapping("api/clothes")
    public ResponseEntity<List<ClothDTO>> findAllByUserName(Principal principal) {
        List<ClothDTO> response = clothService.findAllByUserName(principal);

        if (response.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }
        return ResponseEntity.ok(response);
    }
    // 특정 조회
    // 특정 Cloth를 조회하기 위한 controller, id값으로 검색
    @GetMapping("api/clothes/{id}")
    public ResponseEntity<ClothDTO> findById(@PathVariable("id") Long id) {
        ClothDTO response = clothService.findById(id);

        return ResponseEntity.ok(response);
    }

    // 수정
    @PatchMapping("api/clothes/{id}")
    public ResponseEntity<ClothDTO> updateById(@PathVariable("id")Long id,@RequestBody ClothDTO request){

        ClothDTO response = clothService.updateById(id,request);

        return ResponseEntity.ok(response);
    }
    // 삭제
    @DeleteMapping("api/clothes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        clothService.deleteById(id);

        return ResponseEntity.ok(null);
    }

}
