package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void addMissingOperand() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1010")) // Test for API Addition with One Operand Missing, Missing operand2
            .andExpect(status().isBadRequest()) // Assuming the server responds with 400 Bad Request for missing parameters
            .andExpect(content().string(containsString("Missing operand"))); // Assuming the response contains a message about the missing operand
    }
    
    @Test
    public void addOverflow() throws Exception {
    this.mvc.perform(get("/add").param("operand1", "1111").param("operand2", "1")) // Test for API Addition Resulting in Overflow, Resulting in overflow
        .andExpect(status().isOk())
        .andExpect(content().string("10000")); // Assuming the result is correct and overflow is handled
    }

    @Test
    public void addJsonIncorrectOperands() throws Exception {
    this.mvc.perform(get("/add_json").param("operand1", "1234").param("operand2", "ABCD")) //Test for JSON Response with Incorrect Operand Values, Non-binary operands
        .andExpect(status().isBadRequest()) // Assuming the server responds with 400 Bad Request for invalid parameters
        .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Invalid operands")); // Assuming the JSON contains an error field
}

@Test
public void orOperation() throws Exception {
    this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "0101"))
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
}

@Test
public void orOperationDifferentLength() throws Exception {
    this.mvc.perform(get("/or").param("operand1", "1100").param("operand2", "11"))
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
}

@Test
public void andOperation() throws Exception {
    this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "0101"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
}

@Test
public void andOperationAllOnes() throws Exception {
    this.mvc.perform(get("/and").param("operand1", "1111").param("operand2", "1111"))
        .andExpect(status().isOk())
        .andExpect(content().string("1111"));
}

@Test
public void multiplyOperation() throws Exception {
    this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "0101"))
        .andExpect(status().isOk())
        .andExpect(content().string("110010"));
}

@Test
public void multiplyOperationWithZeros() throws Exception {
    this.mvc.perform(get("/multiply").param("operand1", "1111").param("operand2", "0"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
}


}