package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'

    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code

		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
	
    }

	public String getValue()
	{
		return this.number;
	}

	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

    // OR operation
    public static Binary OR(Binary num1, Binary num2) {
        String result = "";
        String a = new StringBuilder(num1.getValue()).reverse().toString();
        String b = new StringBuilder(num2.getValue()).reverse().toString();
        int maxLength = Math.max(a.length(), b.length());

        for (int i = 0; i < maxLength; i++) {
            char bitA = i < a.length() ? a.charAt(i) : '0';
            char bitB = i < b.length() ? b.charAt(i) : '0';
            result = ((bitA == '1' || bitB == '1') ? "1" : "0") + result;
        }

        return new Binary(result);
    }

    // AND operation
public static Binary AND(Binary num1, Binary num2) {
    String result = "";
    String a = new StringBuilder(num1.getValue()).reverse().toString();
    String b = new StringBuilder(num2.getValue()).reverse().toString();
    int maxLength = Math.max(a.length(), b.length());

    for (int i = 0; i < maxLength; i++) {
        char bitA = i < a.length() ? a.charAt(i) : '0';
        char bitB = i < b.length() ? b.charAt(i) : '0';
        result = ((bitA == '1' && bitB == '1') ? "1" : "0") + result;
    }

    // Pad result with leading zeros if necessary
    while (result.length() < maxLength) {
        result = "0" + result;
    }

    return new Binary(result);
}

    // Multiply operation
public static Binary Multiply(Binary num1, Binary num2) {
    Binary result = new Binary("0");
    String b = new StringBuilder(num2.getValue()).reverse().toString();

    for (int i = 0; i < b.length(); i++) {
        if (b.charAt(i) == '1') {
            String shifted = num1.getValue() + "0".repeat(i);
            result = add(result, new Binary(shifted));
        }
    }

    // Adjust result to maintain correct length
    int expectedLength = num1.getValue().length() + num2.getValue().length() - 1;
    String resultValue = result.getValue();
    while (resultValue.length() < expectedLength) {
        resultValue = "0" + resultValue;
    }

    return new Binary(resultValue);
}

}
	