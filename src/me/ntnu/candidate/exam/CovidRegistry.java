package me.ntnu.candidate.exam;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class is used to manage and run a Covid Location Stats-registry application.
 * @Author: 10009
 */
public class CovidRegistry {

    //Using HashSet because a location stat is uniq and a hashset doesn't allow duplicate values.
    private HashSet<CovidLocationStats> covidRegister;

    /**
     * This is a constructor.
     */
    public CovidRegistry() {
        covidRegister = new HashSet<>();
    }

    /**
     * Helper method for initializing objects of class CovidLocationStats.
     */
    public void initializeCovidLocationStats(){
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.JANUARY, 19), "CHINA", 136, 1));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.FEBRUARY, 05), "CHINA", 3872, 66));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 7), "NORGE", 3, 0));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 9), "USA", 136, 4));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 9), "CHINA", 136, 23));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 22), "NORGE", 136, 8));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 24), "USA", 136, 119));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.MARCH, 25), "CHINA", 136, 4));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.APRIL, 06), "NORGE", 136, 3));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.APRIL, 10), "USA", 136, 2087));
        covidRegister.add(new CovidLocationStats(LocalDate.of(2020, Month.APRIL, 10), "CHINA", 136, 1));
    }

    /**
     * Adds a CovidLocationStats-object to the HashSet covidRegister.
     * @param covidLocationStatsToBeAdded
     * @return boolean with info if object was added or not.
     */
    public boolean regNewCovidLocationStat(CovidLocationStats covidLocationStatsToBeAdded){

        for(CovidLocationStats list : covidRegister){
            if(list.equals(covidLocationStatsToBeAdded)){
                return false;
            }
        }
        covidRegister.add(covidLocationStatsToBeAdded);
        return true;
    }

    /**
     * Searches for the first registration based on input date from user.
     * @param toBeFound
     * @return String with stat-info
     */
    public String searchFirstRegistrationsByDate(CovidLocationStats toBeFound){

        for(CovidLocationStats list : covidRegister){
            if(list.getLocalDate().equals(toBeFound.getLocalDate())){
                return list.toString();
            }
        }
        return null;
    }

    /**
     * Searches for all CovidLocationStats after a given date from user.
     * @param toBeFound
     * @return ArrayList with all CovidLocationStats-objects.
     */
    public ArrayList<CovidLocationStats> searchAllByAfterDate(CovidLocationStats toBeFound){
        ArrayList<CovidLocationStats> obtainedCasesList = new ArrayList<>();

        for(CovidLocationStats list : covidRegister){
            if(list.getLocalDate().equals(toBeFound.getLocalDate()) || list.getLocalDate().isAfter(toBeFound.getLocalDate())){
                obtainedCasesList.add(list);
            }
        }
        return obtainedCasesList;
    }


    /**
     * Calculates the total amount of deceased in a given country from user.
     * @param toBeFound
     * @return int with number of deceased.
     */
    public int numberOfDeceased(CovidLocationStats toBeFound){
        int totalInSpecificCountry = 0;

        for(CovidLocationStats list : covidRegister){
            if(list.getCountry().equalsIgnoreCase(toBeFound.getCountry())){
                totalInSpecificCountry += list.getNumberOfDeaths();
            }
        }

        return totalInSpecificCountry;
    }

    /**
     * Lists the total amount of entries in the register.
     * @return int with amount of entries
     */
    public int listTotalAmountOfEntries(){
        return covidRegister.size();
    }

    /**
     * Converts the contents of an ArrayList to a string.
     * @param listToPrint ArrayList with results from a search.
     */
    public void printResult(ArrayList<CovidLocationStats> listToPrint){

        if(listToPrint.isEmpty()){
            System.out.println("No stats were found");
        }else{
            for(CovidLocationStats list : listToPrint){
                System.out.println(list.toString());
            }
        }

    }

    /**
     * Creates an iterator
     * @return iterator over HashSet covidRegister
     */
    public Iterator<CovidLocationStats> getIterator(){
        return covidRegister.iterator();
    }

    /**
     * Creates a list with all the properties within the registry.
     * @return ArrayList with all Property-Objects.
     */
    public void listAllCovidLocationStats(){
        Iterator it = getIterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

}
