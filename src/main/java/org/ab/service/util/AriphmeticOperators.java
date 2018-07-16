package org.ab.service.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AriphmeticOperators {
	public static final String SYMBOL_DIVIDE = "/";
	public static final String SYMBOL_MULTIPLY = "*";
	public static final String SYMBOL_MINUS = "-";
	public static final String SYMBOL_PLUS = "+";

	private final Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> OPERATORS = new HashMap<>();

	public AriphmeticOperators() {
		super();
		addOperator(SYMBOL_PLUS, AriphmeticOperators::plus);
		addOperator(SYMBOL_MINUS, AriphmeticOperators::minus);
		addOperator(SYMBOL_MULTIPLY, AriphmeticOperators::multiply);
		addOperator(SYMBOL_DIVIDE, AriphmeticOperators::divide);
	}

	public Set<String> getOperatorSymbols() {
		return OPERATORS.keySet().stream().collect(Collectors.toSet());
	}

	public void addOperator(String symbol, BiFunction<BigDecimal, BigDecimal, BigDecimal> operator) {
		OPERATORS.put(symbol, operator);
	}
	
	public BiFunction<BigDecimal, BigDecimal, BigDecimal> getOperator(String symbol) {
		return OPERATORS.get(symbol);
	}

	public static BigDecimal plus(BigDecimal o1, BigDecimal o2) {
		return o1.add(o2);
	}

	public static BigDecimal minus(BigDecimal o1, BigDecimal o2) {
		return o1.subtract(o2);
	}

	public static BigDecimal multiply(BigDecimal o1, BigDecimal o2) {
		return o1.multiply(o2);
	}

	public static BigDecimal divide(BigDecimal o1, BigDecimal o2) {
		return o1.divide(o2);
	}

}
