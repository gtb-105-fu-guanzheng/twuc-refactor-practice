package com.twu.refactoring;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }

    double getCharge(int daysRented) {
        if (getPriceCode() == Movie.REGULAR) {
            double result = 2;
            if (daysRented > 2) {
                result += (daysRented - 2) * 1.5;
            }
            return result;
        } else if (getPriceCode() == Movie.CHILDRENS) {
            double result = 1.5;

            if (daysRented > 3) {
                result += (daysRented - 3) * 1.5;
            }

            return result;
        }
        return daysRented * 3;
    }

    int getFrequentRenterPoints(int daysRented) {
        if (getPriceCode() == Movie.NEW_RELEASE) {
            return daysRented > 1 ? 2 : 1;
        }
        return 1;
    }
}

