package com.virjanand.refactoringMovieRenting;

public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return (double) (daysRented * 3);
    }
}
