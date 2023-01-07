package com.gdsc.skhufp.closet.controller;
import com.gdsc.skhufp.closet.dto.ClothDTO;
import com.gdsc.skhufp.closet.entity.Cloth;
import com.gdsc.skhufp.closet.service.ClothService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClothController {

    private final ClothService clothService;
    // 생성
    //id(user)값으로 Cloth 객체를 build
    @PostMapping("/api/{id}/clothes")
    public ResponseEntity<ClothDTO> saveByUserId(@PathVariable("id")Long id,@RequestBody ClothDTO request)
    {
        ClothDTO response = clothService.saveByUserId(id,request);
        return ResponseEntity.created(URI.create("/api/clothes/"+response.getId()))
                .body(response);
    }
    // 전제 조회
    @GetMapping("api/{id}/clothes")
    public ResponseEntity<List<ClothDTO>> findAllByUserId(@PathVariable("id") Long id){

        List<ClothDTO> response = clothService.findAllByUserId(id);

        if(response.isEmpty()){
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
