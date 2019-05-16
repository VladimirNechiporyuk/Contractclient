package com.flamelab.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "contract-server")
public interface ContractClient {

    @GetMapping("math/")
    @ResponseBody
    Integer multiplyNumber(@RequestParam("number") Integer number, @RequestParam("multiplier") Integer multiplier);
}
