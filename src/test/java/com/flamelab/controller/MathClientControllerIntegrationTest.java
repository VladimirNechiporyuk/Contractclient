package com.flamelab.controller;

import com.flamelab.client.ContractFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "com.flamelab:contract-server:+:stubs:8181")
public class MathClientControllerIntegrationTest {

    private int positiveNumber = 5;
    private int negativeNumber = -5;
    private int positiveMultiplier = 4;
    private int negativeMultiplier = -10;
    private int zero = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContractFeignClient contractFeignClient;

    @Test
    public void multiplyPositiveNumbers() throws Exception {
        int resultOfMultiplying = positiveNumber * positiveMultiplier;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(positiveNumber),
                        Integer.toString(positiveMultiplier))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void multiplyPositiveAndNegativeNumbers() throws Exception {
        int resultOfMultiplying = positiveNumber * negativeMultiplier;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(positiveNumber),
                        Integer.toString(negativeMultiplier))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void multiplyNegativeNumbers() throws Exception {
        int resultOfMultiplying = negativeNumber * negativeMultiplier;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(negativeNumber),
                        Integer.toString(negativeMultiplier))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void multiplyNumberAndZero() throws Exception {
        int resultOfMultiplying = positiveNumber * zero;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(positiveNumber),
                        Integer.toString(zero))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void clientMultiplyPositiveNumbers() {
        Integer resultOfMultiplying = positiveNumber * positiveMultiplier;

        Integer restMultiplying = contractFeignClient.multiplyNumber(positiveNumber, positiveMultiplier);

        assertEquals(restMultiplying, resultOfMultiplying);
    }

    @Test
    public void clientMultiplyPositiveAndNegativeNumbers() {
        Integer resultOfMultiplying = positiveNumber * negativeMultiplier;
        Integer restMultiplying = contractFeignClient.multiplyNumber(positiveNumber, negativeMultiplier);

        assertEquals(restMultiplying, resultOfMultiplying);
    }

    @Test
    public void clientMultiplyNegativeNumbers() {
        Integer resultOfMultiplying = negativeNumber * negativeMultiplier;
        Integer restMultiplying = contractFeignClient.multiplyNumber(negativeNumber, negativeMultiplier);

        assertEquals(restMultiplying, resultOfMultiplying);
    }

    @Test
    public void clientMultiplyNumberAndZero() {
        Integer resultOfMultiplying = positiveNumber * zero;
        Integer restMultiplying = contractFeignClient.multiplyNumber(positiveNumber, zero);

        assertEquals(restMultiplying, resultOfMultiplying);
    }

}