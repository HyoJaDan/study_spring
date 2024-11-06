package com.example.springjwt.controller;

import com.example.springjwt.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ReissueController {
    private final ReissueService reissueService;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // Service에서 새로운 access token을 생성하고 응답 반환
        ResponseEntity<?> result = reissueService.reissueAccessToken(request);

        // JWTService에서 반환된 ResponseEntity 객체의 상태 코드가 성공(2xx)라면
        if (result.getStatusCode().is2xxSuccessful()) {
            String newAccessToken = (String) result.getBody();
            response.setHeader("access", newAccessToken);
        }

        return result;
    }
}