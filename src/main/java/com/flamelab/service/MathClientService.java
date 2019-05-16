package com.flamelab.service;

import com.flamelab.client.ContractClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathClientService {

    @Autowired
    private ContractClient contractClient;

    public Integer multiplyNumbers(Integer number, Integer multiplier) {
        return contractClient.multiplyNumber(number, multiplier);
    }
}
