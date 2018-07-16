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
	public void testCalculateSimpleAddCorrectInput() {
		final String input = "4 5 +";
		final BigDecimal expectedOutput = new BigDecimal("9");
		BigDecimal output = calcService.calculate(input);
		assertEquals(expectedOutput, output);
		
	}

}
