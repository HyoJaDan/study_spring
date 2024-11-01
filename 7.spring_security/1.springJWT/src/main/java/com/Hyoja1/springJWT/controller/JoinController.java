package com.Hyoja1.springJWT.controller;

import com.Hyoja1.springJWT.dto.JoinDTO;
import com.Hyoja1.springJWT.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/join")
    public boolean joinProcess(@RequestBody JoinDTO joinDTO){
        return joinService.joinProcess(joinDTO);
    }
}
