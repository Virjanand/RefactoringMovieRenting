package com.virjanand.refactoringMovieRenting;

class Rental {
    private Movie _movie;
    private int _daysRented;

    Rental(Movie movie, int daysRented) {
            _movie = movie;
        _daysRented = daysRented;
    }

    Movie getMovie() {
        return _movie;
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }

    double getCharge() {
        return _movie.getCharge(_daysRented);
    }
}
