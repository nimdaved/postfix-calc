package org.ab.service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.Stack;

import org.ab.service.util.AriphmeticOperators;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

	private final AriphmeticOperators ariphmeticOperators;

	public CalcService(AriphmeticOperators ariphmeticOperators) {
		this.ariphmeticOperators = ariphmeticOperators;
	}

	public BigDecimal calculate(String input) {
		final Set<String> knownOperatorSymbols = ariphmeticOperators.getOperatorSymbols();

		final String[] tokens = StringUtils.split(input, " ");
		Stack<BigDecimal> stack = new Stack<>();
		
		for (String s : tokens) {
			if (NumberUtils.isNumber(s)) {
				stack.push(new BigDecimal(s));
			} else if (knownOperatorSymbols.contains(s)) {
				BigDecimal right = stack.pop();
				BigDecimal left = stack.pop();
				BigDecimal result = ariphmeticOperators.getOperator(s).apply(left, right);
				stack.push(result);
			} else {
				System.out.println("Skipping illegal character: " + s);
			}
		}
	
		return stack.pop();
	}
	
	

}
