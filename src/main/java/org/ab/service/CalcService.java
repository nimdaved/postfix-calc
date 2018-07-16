package org.ab.service;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.ab.service.util.AriphmeticOperators;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

	private static final String DELIMITER_REGEX = "\\s+";

	private final AriphmeticOperators ariphmeticOperators;

	public CalcService(AriphmeticOperators ariphmeticOperators) {
		this.ariphmeticOperators = ariphmeticOperators;
	}

	public BigDecimal calculate(String input) {
		Map.Entry<List<BigDecimal>, List<String>> parsed = parseInput(input);

		return calculateWithOperands(parsed.getKey(), parsed.getValue());
	}

	private BigDecimal calculateWithOperands(List<BigDecimal> numbers, List<String> operatorSymbols) {
		final Iterator<String> i = operatorSymbols.iterator();
		
		return numbers.stream().reduce((a,b) -> nextCalculation(a, b, i)).get();		
		
	}

	private BigDecimal nextCalculation(BigDecimal accumulator, BigDecimal identity, Iterator<String> symbol) {		
		return accumulator == null ? identity : ariphmeticOperators.getOperator(symbol.next()).apply(accumulator, identity);
	}

	private Entry<List<BigDecimal>, List<String>> parseInput(String input) {
		String[] tokens = input.split(DELIMITER_REGEX);
		final Set<String> knownOperatorSymbols = ariphmeticOperators.getOperatorSymbols();

		Map<Boolean, List<String>> operatorsWithNumbers = Arrays.stream(tokens).map(String::trim)
				.collect(Collectors.partitioningBy(s -> knownOperatorSymbols.contains(s)));
		List<String> suppliedOperators = operatorsWithNumbers.get(Boolean.TRUE);
		List<BigDecimal> suppliedNumbers = operatorsWithNumbers.get(Boolean.FALSE).stream().map(s -> new BigDecimal(s))
				.collect(Collectors.toList());
		// TODO: validate entries: presence, not nulls, sizes of lists (operators 1 less than numbers)

		return new AbstractMap.SimpleEntry<>(suppliedNumbers, suppliedOperators);
	}

}
