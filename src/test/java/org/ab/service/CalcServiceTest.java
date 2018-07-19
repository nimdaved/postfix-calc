package org.ab.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalcServiceTest {
	@Autowired
	private CalcService calcService;

	@Test
	public void testCalculate() {
		evaluate("4 5 +", "9");
		evaluate("1 2 3 * + 4 +", "11");
		evaluate("6 8 2 / 1 - *", "18");
		evaluate("8 5 * 7 4 2 + * +", "82");
		evaluate("2 3 + 4 5 * +", "25");
		
	}
	
	
	
	private void evaluate(String input, String expected) {
		final BigDecimal expectedOutput = new BigDecimal(expected);
		BigDecimal output = calcService.calculate(input);
		assertEquals("failed on input: " + input, expectedOutput, output);
	}

}
