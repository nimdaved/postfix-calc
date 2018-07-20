package org.ab.service;

import java.math.BigDecimal;
import java.util.Arrays;
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

		return Arrays.stream(StringUtils.split(input, " ")).map(String::trim)
				.filter(s -> NumberUtils.isNumber(s) || knownOperatorSymbols.contains(s))
				.collect(Stack<BigDecimal>::new,
						(stack, e) -> stack.push(knownOperatorSymbols.contains(e)
								? ariphmeticOperators.invertParamsThenApply(stack.pop(), stack.pop(), e)
								: new BigDecimal(e)),
						(l, r) -> {//NOP combiner
						})
				.pop();

	}

}
