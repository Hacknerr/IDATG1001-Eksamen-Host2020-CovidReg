import me.ntnu.candidate.exam.CovidLocationStats;
import me.ntnu.candidate.exam.CovidRegistry;
import me.ntnu.candidate.exam.InputHandler;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class is used to manage a text-based user interface for a Covid Location Stats-registry application.
 * @Author: 10009
 * @version 1.0
 */
public class App {

    private final int ADD_COVID_LOCATION_STAT = 1;
    private final int SEARCH_ONE_STAT_BASED_ON_DATE = 2;
    private final int SEARCH_ALL_STATS_BY_AFTER_DATE = 3;
    private final int CALCULATE_AMOUNT_OF_DEATHS_IN_A_COUNTRY = 4;
    private final int PRINT_TOTAL_AMOUNT_OF_COVID_CASES = 5;
    private final int PRINT_ALL_INFO = 6;
    private final int EXIT = 7;

    private CovidRegistry covidRegistry;
    private InputHandler inputHandler;
    private Scanner scn;

    /**
     * Constructor that initializes a Scanner and objects of classes.
     * Also takes the user to the start-menu.
     */
    public App() {
        covidRegistry = new CovidRegistry();
        inputHandler = new InputHandler();
        scn = new Scanner(System.in);
        covidRegistry.initializeCovidLocationStats();
        start();
    }

    /**
     * Creates an object of the App class.
     * Is the first function that runs when the application starts.
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_COVID_LOCATION_STAT:
                    addCovidLocationStatMenu();
                    break;
                case SEARCH_ONE_STAT_BASED_ON_DATE:
                    searchOneStatBasedOnDateMenu();
                    break;
                case SEARCH_ALL_STATS_BY_AFTER_DATE:
                    searchAllStatByAfterDateMenu();
                    break;
                case CALCULATE_AMOUNT_OF_DEATHS_IN_A_COUNTRY:
                    calculateAmountOfDeathsInACountryMenu();
                    break;
                case PRINT_TOTAL_AMOUNT_OF_COVID_CASES:
                    printTotalAmountOfCovidCasesMenu();
                    break;
                case PRINT_ALL_INFO:
                    printAllInfoMenu();
                    break;
                case EXIT:
                    System.out.println("Thank you for using the app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     * If 0 is returned, the user has entered a wrong value
     */
    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n***** Covid Location Stats Application v0.1 *****\n");
        System.out.println("1. Add covid location stat");
        System.out.println("2. Search for first stat on a specific date");
        System.out.println("3. Search for all stats after a specific date");
        System.out.println("4. Calculate amount of deaths in a given country");
        System.out.println("5. Print total amount of Covid-19 registrations in the register");
        System.out.println("6. Print all info");
        System.out.println("7. Quit");
        System.out.println("\nPlease enter a number between 1 and 7.\n");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * This method lets the user input information to register a new location stat. Done with a Scanner.
     * All inputs have to be of a certain data-type.
     */
    public void addCovidLocationStatMenu(){
        System.out.println("-----------Please enter date:-----------");

        System.out.println("Please enter a year.");
        int year = inputHandler.getIntInput(2019, 2021);

        System.out.println("Please enter a month.");
        int month = inputHandler.getIntInput(1, 12);

        System.out.println("Please enter a day.");
        int day = inputHandler.getIntInput(1, 31);

        LocalDate date = LocalDate.of(year, month, day);

        System.out.println("Please enter the name of the country: ");
        String countryName = scn.nextLine();
        countryName = inputHandler.checkString(countryName);

        System.out.println("Please enter the number of infected: ");
        int infected = inputHandler.getIntInput(0, 99999);

        System.out.println("Please enter the number of deaths: ");
        int deaths = inputHandler.getIntInput(0, 99999);

        if(covidRegistry.regNewCovidLocationStat(new CovidLocationStats(date, countryName, infected, deaths))){
            System.out.println("New registry was successfully added");
        }else{
            System.out.println("The registry was not added");
        }

    }

    /**
     * Lets the user search for stats in the registry. Done with a Scanner.
     */
    public void searchOneStatBasedOnDateMenu(){
        System.out.println("-----------Please enter date:-----------");
        System.out.println("Example: 2020, March, 9");

        System.out.println("Please enter a year.");
        int year = inputHandler.getIntInput(2019, 2021);

        System.out.println("Please enter a month.");
        int month = inputHandler.getIntInput(1, 12);

        System.out.println("Please enter a day.");
        int day = inputHandler.getIntInput(1, 31);

        LocalDate date = LocalDate.of(year, month, day);

        CovidLocationStats covidLocationStats = new CovidLocationStats(date, null, 0, 0);

        if(covidRegistry.searchFirstRegistrationsByDate(covidLocationStats) == null){
            System.out.println("Didn't find any entries.");
        }else{
            System.out.println(covidRegistry.searchFirstRegistrationsByDate(covidLocationStats));
        }
    }

    /**
     * Lets the user search for all stats in the registry. Done with a Scanner.
     */
    public void searchAllStatByAfterDateMenu(){
        System.out.println("-----------Please enter date:-----------");
        System.out.println("Example: 2020, 03, 09");

        System.out.println("Please enter a year.");
        int year = inputHandler.getIntInput(2019, 2021);

        System.out.println("Please enter a month.");
        int month = inputHandler.getIntInput(1, 12);

        System.out.println("Please enter a day.");
        int day = inputHandler.getIntInput(1, 31);

        LocalDate date = LocalDate.of(year, month, day);

        covidRegistry.printResult(covidRegistry.searchAllByAfterDate(new CovidLocationStats(date, null, 0,0)));
    }

    /**
     * Gives the user information about the amount of deaths in a given country.
     */
    public void calculateAmountOfDeathsInACountryMenu(){
        System.out.println("Please enter the name of the country: ");
        String countryName = scn.nextLine();
        countryName = inputHandler.checkString(countryName);
        System.out.println(covidRegistry.numberOfDeceased(new CovidLocationStats(null, countryName, 0, 0)));
    }

    /**
     * Prints a number to the user of all Covid entries within the register.
     */
    public void printTotalAmountOfCovidCasesMenu(){
        System.out.println("-----------Registrations in the register-----------");
        System.out.println(covidRegistry.listTotalAmountOfEntries());
    }

    /**
     * Prints a list to the user of all Covid entries within the register.
     */
    public void printAllInfoMenu(){
        System.out.println("-----------All registered Covid stats-----------");
        covidRegistry.listAllCovidLocationStats();
    }

}
