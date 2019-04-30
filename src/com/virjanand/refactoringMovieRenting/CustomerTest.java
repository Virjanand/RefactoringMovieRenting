package com.virjanand.refactoringMovieRenting;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void oneRegularMovieOneDay_givesCharge2Points1() {
        String movieName = "regularMovie";
        Customer customer = createCustomer(movieName, 0, 1);
        assertEquals(generateExpectedString(movieName, 2.0, 2.0, 1), customer.statement());
    }

    @Test
    public void oneRegularMovie2Days_stillGivesCharge2Points1() {
        String movieName = "regularMovie";
        Customer customer = createCustomer(movieName, 0, 2);
        assertEquals(generateExpectedString(movieName, 2.0, 2.0, 1), customer.statement());
    }

    @Test
    public void oneRegularMovie3Days_givesAmount3_5Points1() {
        String movieName = "regularMovie";
        int daysRented = 3;
        Customer customer = createCustomer(movieName, 0, daysRented);
        int numberOfMovies = 1;
        double basePrice = 2.0;
        assertEquals(generateExpectedString(movieName, (daysRented - 2) * 1.5 + basePrice, numberOfMovies * (daysRented - 2) * 1.5 + basePrice, 1), customer.statement());
    }

    @Test
    public void oneChildrensMovieOneDay_givesCharge1_5Points1() {
        String movieName = "childrensMovie";
        Customer customer = createCustomer(movieName, 2, 1);
        assertEquals(generateExpectedString(movieName, 1.5, 1.5, 1), customer.statement());
    }

    @Test
    public void oneChildrensMovie3Days_stillGivesCharge1_5Points1() {
        String movieName = "childrensMovie";
        Customer customer = createCustomer(movieName, 2, 3);
        assertEquals(generateExpectedString(movieName, 1.5, 1.5, 1), customer.statement());
    }

    @Test
    public void oneChildrensMovie4Days_givesCharge3Points1() {
        String movieName = "childrensMovie";
        int daysRented = 4;
        Customer customer = createCustomer(movieName, 2, daysRented);
        int numberOfMovies = 1;
        double basePrice = 1.5;
        assertEquals(generateExpectedString(movieName, (daysRented - 3) * 1.5 + basePrice, numberOfMovies * (daysRented - 3) * 1.5 + basePrice, 1), customer.statement());
    }

    @Test
    public void oneNewReleaseMovieOneDay_givesCharge3Points1() {
        String movieName = "newReleaseMovie";
        Customer customer = createCustomer(movieName, 1, 1);
        assertEquals(generateExpectedString(movieName, 3.0, 3.0, 1), customer.statement());
    }

    @Test
    public void oneNewReleaseMovie2Days_givesCharge6Points2() {
        String movieName = "newReleaseMovie";
        int daysRented = 2;
        Customer customer = createCustomer(movieName, 1, daysRented);
        int numberOfMovies = 1;
        assertEquals(generateExpectedString(movieName, (daysRented * 3), numberOfMovies * (daysRented * 3), 2), customer.statement());
    }

    @Test
    public void twoNewReleaseMovieTwoDays_givesCharge12Points4() {
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

    @Test
    public void htmlStatement() {
        String movieName = "newReleaseMovie";
        Customer customer = createCustomer(movieName, 1, 2);
        assertEquals("<H1>Rentals for <EM>customer</EM></H1><P>\n"
                + movieName + ": " + 6.0 + "<BR>\n"
                + "<P>You owe <EM>" + 6.0 + "</EM><P>\n"
                + "On this rental you earned <EM>" + 2 + "</EM> frequent renter points<P>", customer.htmlStatement());
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