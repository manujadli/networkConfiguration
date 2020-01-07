package com.networks.in.uk;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class JenkinsCalculatorTest {

	@Test
	public void addTwoNumbersTest() {
		JenkinsCalculator calculator = new JenkinsCalculator();
		assertEquals(12, calculator.addTwoNumbers(5, 10));		
	}
	
	@Test
	public void substractTwoNumbersTest() {
		JenkinsCalculator calculator = new JenkinsCalculator();
		assertEquals(5, calculator.substractTwoNumbers(10, 5));
	}
	
}
