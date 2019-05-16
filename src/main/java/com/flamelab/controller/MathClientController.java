package com.flamelab.controller;

import com.flamelab.service.MathClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mathclient")
public class MathClientController {

    @Autowired
    private MathClientService mathClientService;

    @GetMapping(value = "/multiply", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Integer multiplyNumbers(Integer number, Integer multiplier) {
        return mathClientService.multiplyNumbers(number, multiplier);
    }
}