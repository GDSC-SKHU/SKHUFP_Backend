package com.gdsc.skhufp.closet.controller;

import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;
import com.gdsc.skhufp.closet.dto.request.ClothRequest;
import com.gdsc.skhufp.closet.dto.response.ClothResponse;
import com.gdsc.skhufp.closet.service.ClothService;
import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.response.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
public class ClothController {

    private final ClothService clothService;
    // 생성
    @PostMapping
    public ResponseEntity<SuccessResponseBody<ClothResponse>> save(Principal principal, @RequestBody ClothRequest request) {
        ClothResponse response = clothService.save(principal, request);

        return SuccessResponseBody.toResponseEntity(StatusEnum.CREATED_CLOTH, response);
    }
    // 전제 조회
    @GetMapping
    public ResponseEntity<SuccessResponseBody<List<ClothResponse>>> findAll(Principal principal,
                                                       @RequestParam(required = false) ClothType type,
                                                       @RequestParam(required = false) Set<Season> seasons) {
        List<ClothResponse> responses;

        if (type == null && seasons == null) {
            responses = clothService.findAllByUserName(principal);
        } else if (type == null && seasons != null) {
            responses = clothService.findAllByUserNameAndSeasons(principal, seasons);
        } else if (type != null && seasons == null) {
            responses = clothService.findAllByUserNameAndType(principal, type);
        } else {
            responses = clothService.findAllByUserNameAndTypeAndSeasons(principal, type, seasons);
        }

        if (responses.isEmpty()) {
            return SuccessResponseBody.toEmptyResponseEntity();
        }

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_RETURN_DATA, responses);
    }
    // 특정 조회
    // 특정 Cloth를 조회하기 위한 controller, id값으로 검색
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<ClothResponse>> findById(@PathVariable("id") Long id, Principal principal) {
        ClothResponse response = clothService.findById(id, principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_RETURN_DATA, response);
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<ClothResponse>> updateById(@PathVariable("id") Long id, Principal principal, @RequestBody ClothRequest request){

        ClothResponse response = clothService.updateById(id, principal, request);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_UPDATE_DATA, response);
    }
    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<Void>> deleteById(@PathVariable("id") Long id, Principal principal) {
        clothService.deleteById(id, principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_DELETE_DATA, null);
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuccessResponseBody<ClothResponse>> uploadImageById(@PathVariable("id") Long id, Principal principal, @RequestPart MultipartFile file) {
        ClothResponse response = clothService.uploadImageById(id, principal, file);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_UPDATE_DATA, response);
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<SuccessResponseBody<Void>> deleteImageById(@PathVariable("id") Long id, Principal principal) {
        clothService.deleteImageById(id, principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_DELETE_DATA, null);
    }
}
