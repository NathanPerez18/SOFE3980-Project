package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

@Test
public void postOROperation() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "0101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111"))
        .andExpect(model().attribute("operand1", "1010"));
}

@Test
public void postOROperationDifferentLength() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1100").param("operator", "|").param("operand2", "11"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111"))
        .andExpect(model().attribute("operand1", "1100"));
}

@Test
public void postANDOperation() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1010").param("operator", "&").param("operand2", "0101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"))
        .andExpect(model().attribute("operand1", "1010"));
}

@Test
public void postANDOperationAllOnes() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1111").param("operator", "&").param("operand2", "1111"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111"))
        .andExpect(model().attribute("operand1", "1111"));
}

@Test
public void postMultiplyOperation() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1010").param("operator", "*").param("operand2", "0101"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "110010"))
        .andExpect(model().attribute("operand1", "1010"));
}

@Test
public void postMultiplyOperationWithZeros() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1111").param("operator", "*").param("operand2", "0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"))
        .andExpect(model().attribute("operand1", "1111"));
}



}