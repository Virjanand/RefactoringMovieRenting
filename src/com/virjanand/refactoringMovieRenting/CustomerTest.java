package com.virjanand.refactoringMovieRenting;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void oneRegularMovieOneDay_givesAmount2Points1() {
        String movieName = "regularMovie";
        Customer customer = createCustomer(movieName, 0, 1);
        assertEquals(generateExpectedString(movieName, 2.0, 2.0, 1), customer.statement());
    }

    @Test
    public void oneNewReleaseMovieOneDay_givesAmount3Points1() {
        String movieName = "newReleaseMovie";
        Customer customer = createCustomer(movieName, 1, 1);
        assertEquals(generateExpectedString(movieName, 3.0, 3.0, 1), customer.statement());
    }

    @Test
    public void oneChildrensMovieOneDay_givesAmount1_5Points1() {
        String movieName = "childrensMovie";
        Customer customer = createCustomer(movieName, 2, 1);
        assertEquals(generateExpectedString(movieName, 1.5, 1.5, 1), customer.statement());
    }

    @Test
    public void oneNewReleaseMovieTwoDays_givesAmount6Points2() {
        String movieName = "newReleaseMovie";
        Customer customer = createCustomer(movieName, 1, 2);
        assertEquals(generateExpectedString(movieName, 6.0, 6.0, 2), customer.statement());
    }

    @Test
    public void twoNewReleaseMovieTwoDays_givesAmount12Points4() {
        String movieName = "newReleaseMovie";
        Customer customer = createCustomer(movieName, 1, 2);
        Movie secondMovie = new Movie(movieName, 1);
        Rental secondRental = new Rental(secondMovie, 2);
        customer.addRental(secondRental);
        assertEquals("Rental Record for customer\n"
                + "\t" + movieName + "\t" + 6.0 + "\n"
                + "\t" + movieName + "\t" + 6.0 + "\n"
                + "Amount owed is " + 12.0 + "\n"
                + "You earned " + 4 + " frequent renter points", customer.statement());
    }

    private String generateExpectedString(String movieName, double amountPerMovie, double totalAmount, int frequentRenterPoints) {
        return "Rental Record for customer\n"
                + "\t" + movieName + "\t" + amountPerMovie + "\n"
                + "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private Customer createCustomer(String movieName, int priceCode, int daysRented) {
        Customer customer = new Customer("customer");
        Movie movie = new Movie(movieName, priceCode);
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);
        return customer;
    }
}