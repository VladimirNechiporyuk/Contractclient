package com.flamelab.service;

import com.flamelab.client.ContractFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathClientService {

    @Autowired
    private ContractFeignClient contractFeignClient;

    public Integer multiplyNumbers(Integer number, Integer multiplier) {
        return contractFeignClient.multiplyNumber(number, multiplier);
    }
}
