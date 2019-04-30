package com.virjanand.refactoringMovieRenting;

import static com.virjanand.refactoringMovieRenting.Movie.CHILDRENS;
import static com.virjanand.refactoringMovieRenting.Movie.NEW_RELEASE;
import static com.virjanand.refactoringMovieRenting.Movie.REGULAR;

abstract class Price {
    abstract int getPriceCode();

    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case REGULAR:
                result = getChargeRegular(daysRented);
                break;
            case NEW_RELEASE:
                result = getChargeNewRelease(daysRented);
                break;
            case CHILDRENS:
                result = getChargeChildrens(daysRented);
                break;
        }
        return result;
    }

    private double getChargeNewRelease(int daysRented) {
        return (double) (daysRented * 3);
    }

    private double getChargeChildrens(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }

    private double getChargeRegular(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}
