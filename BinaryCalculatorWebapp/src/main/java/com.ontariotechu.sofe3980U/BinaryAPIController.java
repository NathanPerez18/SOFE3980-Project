package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

	@GetMapping("/add")
	public String addString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		Binary number1=new Binary (operand1);
		Binary number2=new Binary (operand2);
        return  Binary.add(number1,number2).getValue();
		// http://localhost:8080/add?operand1=111&operand2=1010
	}
	
	@GetMapping("/add_json")
	public BinaryAPIResult addJSON(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		Binary number1=new Binary (operand1);
		Binary number2=new Binary (operand2);
        return  new BinaryAPIResult(number1,"add",number2,Binary.add(number1,number2));
		// http://localhost:8080/add?operand1=111&operand2=1010
	}

	@GetMapping("/and")
public BinaryAPIResult andOperation(@RequestParam(name="operand1") String operand1,
                                    @RequestParam(name="operand2") String operand2) {
    Binary number1 = new Binary(operand1);
    Binary number2 = new Binary(operand2);
    return new BinaryAPIResult(number1, "and", number2, Binary.AND(number1, number2));
    // http://localhost:8080/and?operand1=1010&operand2=0101
}

@GetMapping("/or")
public BinaryAPIResult orOperation(@RequestParam(name="operand1") String operand1,
                                   @RequestParam(name="operand2") String operand2) {
    Binary number1 = new Binary(operand1);
    Binary number2 = new Binary(operand2);
    return new BinaryAPIResult(number1, "or", number2, Binary.OR(number1, number2));
    // http://localhost:8080/or?operand1=1010&operand2=0101
}

// Assuming you have a multiply method in your Binary class.
@GetMapping("/multiply")
public BinaryAPIResult multiplyOperation(@RequestParam(name="operand1") String operand1,
                                         @RequestParam(name="operand2") String operand2) {
    Binary number1 = new Binary(operand1);
    Binary number2 = new Binary(operand2);
    return new BinaryAPIResult(number1, "multiply", number2, Binary.Multiply(number1, number2));
    // http://localhost:8080/multiply?operand1=1010&operand2=0101
}


}