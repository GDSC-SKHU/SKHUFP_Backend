package com.gdsc.skhufp.closet.controller;

import com.gdsc.skhufp.closet.dto.request.DailyLookRequest;
import com.gdsc.skhufp.closet.dto.response.DailyLookResponse;
import com.gdsc.skhufp.closet.service.DailyLookService;
import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.response.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/daily-looks")
@RequiredArgsConstructor
public class DailyLookController {
    private final DailyLookService dailyLookService;

    @PostMapping
    public ResponseEntity<SuccessResponseBody<DailyLookResponse>> save(Principal principal, @RequestBody DailyLookRequest request) {
        DailyLookResponse response = dailyLookService.save(principal, request);

        return SuccessResponseBody.toResponseEntity(StatusEnum.CREATED_DAILY_LOOK, response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseBody<List<DailyLookResponse>>> findAll(Principal principal) {
        List<DailyLookResponse> responses = dailyLookService.findAll(principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_RETURN_DATA, responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<DailyLookResponse>> findById(@PathVariable Long id, Principal principal) {
        DailyLookResponse response = dailyLookService.findById(id, principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_RETURN_DATA, response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<DailyLookResponse>> updateById(@PathVariable Long id, Principal principal, @RequestBody DailyLookRequest request) {
        DailyLookResponse response = dailyLookService.updateById(id, principal, request);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_UPDATE_DATA, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseBody<DailyLookResponse>> deleteById(@PathVariable Long id, Principal principal) {
        dailyLookService.deleteById(id, principal);

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_DELETE_DATA, null);
    }
}
