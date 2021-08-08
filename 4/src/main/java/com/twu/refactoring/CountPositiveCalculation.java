package com.twu.refactoring;

public class CountPositiveCalculation implements Calculation {
    @Override
    public int CalculationResult(int... numbers) {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }
}
