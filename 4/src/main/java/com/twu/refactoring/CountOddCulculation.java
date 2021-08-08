package com.twu.refactoring;

public class CountOddCulculation implements Calculation {
    @Override
    public int CalculationResult(int... numbers) {
        int count = 0;
        for (int number : numbers) {
            if (number % 2 == 1) count++;
        }
        return count;
    }
}
