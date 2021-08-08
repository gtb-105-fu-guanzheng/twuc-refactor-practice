package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;
    private Calculation calculation;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        calculation = new CountEvenCalculation();
        return calculation.CalculationResult(numbers);
    }

    public int countOdd() {
        calculation = new CountOddCulculation();
        return calculation.CalculationResult(numbers);
    }

    public int countPositive() {
        calculation = new CountPositiveCalculation();
        return calculation.CalculationResult(numbers);
    }

    public int countNegative() {
        calculation = new CountNegativeCalcalation();
        return calculation.CalculationResult(numbers);
    }
}
