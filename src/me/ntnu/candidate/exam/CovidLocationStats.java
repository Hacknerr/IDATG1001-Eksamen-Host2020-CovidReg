package me.ntnu.candidate.exam;

import java.time.LocalDate;

/**
 * This class is used to describe some covid location stats.
 * @Author: 10009
 */
public class CovidLocationStats {

    private LocalDate localDate;
    private String country;
    private int numberOfInfected;
    private int numberOfDeaths;

    /**
     * This is a constructor that takes 4 parameters
     * @param localDate
     * @param country
     * @param numberOfInfected
     * @param numberOfDeaths
     */
    public CovidLocationStats(LocalDate localDate, String country, int numberOfInfected, int numberOfDeaths) {
        this.localDate = localDate;
        this.country = country;
        this.numberOfInfected = numberOfInfected;
        this.numberOfDeaths = numberOfDeaths;
    }

    /**
     * This function returns the local date.
     * @return LocalDate
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * This function returns the country.
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * This function gets the number of deaths
     * @return
     */
    public int getNumberOfDeaths() {
        return numberOfDeaths;
    }

    /**
     * This function returns information about the covid location stats.
     * @return a string of information.
     */
    @Override
    public String toString() {
        return localDate + ", " + country + ", Infected: " + numberOfInfected + ", Deaths: " + numberOfDeaths;
    }

}
