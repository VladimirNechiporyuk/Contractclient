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

    private int number = 5;
    private int multiplier = 4;
    private int zero = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContractFeignClient contractFeignClient;

    @Test
    public void multiplyNumbers() throws Exception {
        int resultOfMultiplying = number * multiplier;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(number),
                        Integer.toString(multiplier))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void multiplyNumberAndZero() throws Exception {
        int resultOfMultiplying = number * zero;
        mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/math/multiply?number=%s&multiplier=%s",
                        Integer.toString(number),
                        Integer.toString(zero))))
                .andExpect(status().isOk())
                .andExpect(content().string(Integer.toString(resultOfMultiplying)));
    }

    @Test
    public void clientMultiplyNumbers() {
        Integer resultOfMultiplying = number * multiplier;

        Integer resultOfMultiplyingFromProducer = contractFeignClient.multiplyNumber(number, multiplier);

        assertEquals(resultOfMultiplyingFromProducer, resultOfMultiplying);
    }

    @Test
    public void clientMultiplyNumberAndZero() {
        Integer resultOfMultiplying = number * zero;
        Integer resultOfMultiplyingFromProducer = contractFeignClient.multiplyNumber(number, zero);

        assertEquals(resultOfMultiplyingFromProducer, resultOfMultiplying);
    }
}