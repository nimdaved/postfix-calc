package org.ab.service.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Component;

@Component
public class AriphmeticOperators {
	public static final String SYMBOL_DIVIDE = "/";
	public static final String SYMBOL_MULTIPLY = "*";
	public static final String SYMBOL_MINUS = "-";
	public static final String SYMBOL_PLUS = "+";

	private final Map<String, BinaryOperator<BigDecimal>> OPERATORS = new ConcurrentHashMap<>();

	public AriphmeticOperators() {
		super();
		addOperator(SYMBOL_PLUS, BigDecimal::add);
		addOperator(SYMBOL_MINUS, BigDecimal::subtract);
		addOperator(SYMBOL_MULTIPLY, BigDecimal::multiply);
		addOperator(SYMBOL_DIVIDE, BigDecimal::divide);		

	}

	public Set<String> getOperatorSymbols() {
		return Collections.unmodifiableSet(OPERATORS.keySet());
	}

	public final void addOperator(String symbol, BinaryOperator<BigDecimal> operator) {
		OPERATORS.put(symbol, operator);
	}

	private BinaryOperator<BigDecimal> getOperator(String symbol) {
		return OPERATORS.get(symbol);
	}

	public BigDecimal invertParamsThenApply(BigDecimal left, BigDecimal right, String symbol)  {
		return getOperator(symbol).apply(right, left);
	}

}
