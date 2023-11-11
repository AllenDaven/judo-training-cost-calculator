package EduClaaS_Programming;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException; 

// Company - AQ Digital Solutions (AQDS).
// Position - Junior Software Developer.
// Client - North Sussex Judo Training Center.
// Project - Monthy fee calculation for the training center.
// Objective - Compute the monthly fees to pay by each athlete.
// The initial number of athletes is six.

//"Alex",   'I', 2, 5, 98.5, "Light Heavyweight"
//"Bob",    'E', 3, 5, 110.6, "Heavyweight"
//"Charlie",'E', 1, 0, 96.4, "Light Heavyweight"
//"Danny",  'B', 0, 0, 60.5, "Flyweight"
//"Edward", 'E', 2, 5, 105.7, "Heavyweight"
//"Frank",  'I', 1, 5, 86.2, "Middleweight"

class Athletes {
    int numAthlete = 6;

    private String athleteName;
    private String weightCategory;
    private char trainingPlan;
    private double weight;
    int noOfHours;
    private int noOfCompetitions;

    public Athletes(String athleteName, char trainingPlan, int noOfHours, int noOfCompetitions, double weight,
            String weightCategory) {
        this.athleteName = athleteName;
        this.trainingPlan = Character.toUpperCase(trainingPlan);
        this.weight = weight;
        this.weightCategory = weightCategory;
        this.noOfCompetitions = noOfCompetitions;
        this.noOfHours = 0;

        // Athletes can receive a maximum of five hours’ private coaching a week,
        // Only Intermediate and Elite athletes can enter competitions.
        // For Beginners, all competition-related fields to 0.
        if (this.trainingPlan == 'B') {
            this.noOfCompetitions = 0;
        }
    }

    public String getWeightCategory() {
        if (Double.parseDouble(weightCategory) >= 101) {
            return "Heavyweight";
        } else if (Double.parseDouble(weightCategory) <= 100 && Double.parseDouble(weightCategory) >= 91) {
            return "Light-Heavyweight";
        } else if (Double.parseDouble(weightCategory) <= 90 && Double.parseDouble(weightCategory) >= 82) {
            return "Middleweight";
        } else if (Double.parseDouble(weightCategory) <= 81 && Double.parseDouble(weightCategory) >= 74) {
            return "Light-Middleweight";
        } else if (Double.parseDouble(weightCategory) <= 73 && Double.parseDouble(weightCategory) >= 67) {
            return "Lightweight";
        } else if (Double.parseDouble(weightCategory) <= 66) {
            return "Flyweight";
        } else {
            return "!!Weight should be at least 66 kilograms!!";
        }
    }

    public double calculateMonthlyFee() {
        double feePerSession;
        switch (trainingPlan) {
            // B and b = Beginner (Weekly fee of $25.00).
            case 'B':
            case 'b':
                feePerSession = 25.00;
                break;
            // I and i = Intermediate (Weekly fee of $30.00).
            case 'I':
            case 'i':
                feePerSession = 30.00;
                break;
            // E and e = Elite (Weekly fee of $35.00).
            case 'E':
            case 'e':
                feePerSession = 35.00;
                break;
            default:
                feePerSession = 0.00;
        }
        // There are four weeks in a month, so multiply by four.
        double totalFee = feePerSession * 4.00;
        // Private tuition is $9.00 per hour.
        totalFee += noOfHours * 9.00;
        if (trainingPlan == 'B' || trainingPlan == 'b') {
            return totalFee;
        } else {
            // Competition entry fee of $22.00 per competition.
            totalFee += noOfCompetitions * 22.00;
        }
        return totalFee;
    }

    public int getSessionsPerWeek() {
        switch (trainingPlan) {
            // Press B or b for Beginner.
            case 'B':
            case 'b':
                return 2;
            // Press I or i for Intermediate.
            case 'I':
            case 'i':
                return 3;
            // Press E or e for Elite.
            case 'E':
            case 'e':
                return 5;
            default:
                return 0;
        }
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formattedWeight = decimalFormat.format(weight);

        double costAsPerPlan = calculateMonthlyFee() - (noOfHours * 9.00) - (noOfCompetitions * 22.00);
        double totalMonthlyCost = costAsPerPlan;

        System.out.println(
                "\n==========================================================================================");
        System.out.println("\t\t\t\t   ATHLETE INFORMATION");
        String feeDetails = "\n\tAthlete Name: \t\t\t\t\t\t\t" + athleteName +
                "\n\tTraining Plan ([B] Beginner [I] Intermediate [E] Elite):\t" + trainingPlan +
                "\n\tNumber of Competitions Entered:\t\t\t\t\t" + noOfCompetitions +
                "\n\tNumber of Private Coaching Hours:\t\t\t\t" + noOfHours + " hr/hrs " +
                "\n\tCurrent Weight:\t\t\t\t\t\t\t" + formattedWeight + " kg " +
                "\n\tWeight Category:\t\t\t\t\t\t" + getWeightCategory() +

                "\n\n\n\t\t\t\tITEMIZED COSTS FOR THE MONTH\n" +
                "\n\tCosts as per the Plan:------------------------------------------$"
                + decimalFormat.format(costAsPerPlan);
        if (trainingPlan == 'B' || trainingPlan == 'b') {
            feeDetails += "\n\tCosts for competition:----------------------------!!NOT APPLICABLE FOR Beginners!!";
        } else {
            double competitionCost = noOfCompetitions * 22.00;
            feeDetails += "\n\tCosts for competition:------------------------------------------$"
                    + decimalFormat.format(competitionCost);
            totalMonthlyCost += competitionCost;
        }

        if (noOfHours > 5) {
            feeDetails += "\n\tCosts for private training:---------------------------!!Maximum of 5 hours only!!";
        } else {
            double privateTrainingCost = noOfHours * 9.00;
            if (noOfHours > 0) {
                feeDetails += "\n\tCosts for private training:-------------------------------------$"
                        + decimalFormat.format(privateTrainingCost);
                totalMonthlyCost += privateTrainingCost;
            }
        }

        feeDetails += "\n\n\tMONTHLY TOTAL FEES:---------------------------------------------$"
                + decimalFormat.format(totalMonthlyCost);

        return feeDetails;
    }

}

public class JudoTrainingCostCalculator {
    // private static String weightCategory;

    public static void main(String[] args) {
        List<Athletes> athletes = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "\n==========================================================================================");
            System.out.println("\r\n"
                    + "  _   _            _   _       ____                                _           _       \r\n"
                    + " | \\ | | ___  _ __| |_| |__   / ___| _   _ ___ ___  _____  __     | |_   _  __| | ___  \r\n"
                    + " |  \\| |/ _ \\| '__| __| '_ \\  \\___ \\| | | / __/ __|/ _ \\ \\/ /  _  | | | | |/ _` |/ _ \\ \r\n"
                    + " | |\\  | (_) | |  | |_| | | |  ___) | |_| \\__ \\__ \\  __/>  <  | |_| | |_| | (_| | (_) |\r\n"
                    + " |_| \\_|\\___/|_|   \\__|_| |_| |____/ \\__,_|___/___/\\___/_/\\_\\  \\___/ \\__,_|\\__,_|\\___/ \r\n"
                    + "                                                                                       \r\n"
                    + "");
            System.out.println("\t\t     !! WELCOME TO THE JUDO TRAINING COST CALCULATOR !!");
            System.out.println(
                    "  This will assist the athlete in determining the AQDS Judo Training Center Monthly Fee.");
            // Press [1] to add an athlete or more.
            System.out.println("\nPress [1] Add Athlete");
            // Press [2] to calculate each athlete's monthly fee.
            System.out.println("Press [2] Calculate Monthly Fees");
            // Press [3] to exit the calculator.
            System.out.println("Press [3] Exit the Calculator");
            System.out.println("\nSELECT YOUR OPTION [1] [2] [3]: ");

            String choice = scan.nextLine();

            if (choice.equals("1")) {
                // Inquire about the athletes' information.
                // Enter Athlete's Name.
                // A number entered by the athlete will result in an error.
                String athleteName;
                while (true) {
                    try {
                        System.out.println("Athlete Name: ");
                        athleteName = scan.nextLine();
                        if (athleteName.matches(".*\\d.*")) {
                            throw new IOException("\t\t    ERROR: No numbers should appear in an athlete's name.");
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // Enter athlete's training plan by choosing between [B] for Beginner, [I] for
                // Intermediate, and [E] for Elite.
                System.out.println("\t=========================================================================");
                System.out.println("\t|                     TRAINING PLAN                     | PRICES ($USD) |");
                System.out.println("\t|=======================================================|===============|");
                System.out.println("\t|Beginner-Weekly fee            (2 sessions per week)   |     $25.00    |");
                System.out.println("\t|Intermediate-Weekly fee        (3 sessions per week)   |     $30.00    |");
                System.out.println("\t|Elite-Weekly fee               (5 sessions per week)   |     $35.00    |");
                System.out.println("\t|Private tuition per hour       (5 hours is the maximum)|     $ 9.00    |");
                System.out.println("\t|Competition entry fee per competition                  |     $22.00    |");
                System.out.println("\t|_______________________________________________________|_______________|");
                char trainingPlan;
                do {
                    System.out.println("\nTraining plan ([B] for Beginner, [I] for Intermediate, and [E] for Elite): ");
                    String planInput = scan.nextLine().toUpperCase();
                    if (planInput.equals("B") || planInput.equals("I") || planInput.equals("E")) {
                        trainingPlan = planInput.charAt(0);
                        break;
                    } else {
                        System.out.println(
                                "\tERROR:Please choose [B] for Beginner, [I] for Intermediate, or [E] for Elite.");
                    }
                } while (true);

                // A word enter by the athlete in current weight will error.
                double weight = 0.0;
                try {
                    System.out.println("Current Weight in Kilograms (kg): ");
                    weight = Double.parseDouble(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("\t   ERROR: Current Weight input is invalid. Please enter a valid number.");
                    continue;
                }
                String weightCategory;
                try {
                    // Enter athlete's competition weight category in kilogram.
                    System.out.println("\t=========================================================================");
                    System.out.println("\t|             WEIGHT CATEGORY                |  UPPER WEIGHT LIMIT (Kg) |");
                    System.out.println("\t|============================================|==========================|");
                    System.out.println("\t|                Heavyweight                 |   Unlimited (Over 100)   |");
                    System.out.println("\t|             Light-Heavyweight              |             100          |");
                    System.out.println("\t|               Middleweight                 |             90           |");
                    System.out.println("\t|            Light-Middleweight              |             81           |");
                    System.out.println("\t|                Lightweight                 |             73           |");
                    System.out.println("\t|                 Flyweight                  |             66           |");
                    System.out.println("\t|____________________________________________|__________________________|");
                    System.out.println("\t________________________________________________________________________");
                    System.out.println("\t|                            !! CONDITIONS !!                           |");
                    System.out.println("\t|         Maximum of five hours private coaching per week only.         |");
                    System.out.println("\t|      Only Intermediate and Elite athletes can enter competitions.     |");
                    System.out.println("\t|       Competitions are held on the second Saturday of each month.     |");
                    System.out.println("\t|_______________________________________________________________________|");
                    System.out.println("\nCompetition Weight Category (kg): ");
                    weightCategory = scan.nextLine();
                    double weightCategoryValue = Double.parseDouble(weightCategory);
                    if (weightCategoryValue < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\t    ERROR: Weight Category input is invalid. Please enter a valid number.");
                    continue;
                }
                int noOfCompetitions = 0;
                int noOfHours = 0;

                try {
                    // Enter the total number of competitions entered this month at a cost of $22.00
                    // per competition.
                    System.out.println("Number of competitions entered this month: ");
                    noOfCompetitions = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(
                            "\tERROR: Invalid input for the number of competitions. Please enter a valid number.");
                    continue;
                }

                // Does the athlete have private coaching hours?
                String privateCoachingOption;
                do {
                    System.out.println("Option to add the number of hours private coaching. Type [yes] or [no]): ");
                    privateCoachingOption = scan.nextLine();
                    if (privateCoachingOption.equalsIgnoreCase("yes")) {
                        try {
                            System.out.println("Enter the number of private coaching hours: ");
                            noOfHours = Integer.parseInt(scan.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "\t  ERROR: Invalid input for the number of hours. Please enter a valid number.");
                        }
                    } else if (privateCoachingOption.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("\t\t\tERROR: Invalid input. Please type [yes] or [no].");
                    }
                } while (true);

                Athletes athlete = new Athletes(athleteName, trainingPlan, noOfHours, noOfCompetitions, weight,
                        weightCategory);
                athlete.noOfHours = noOfHours;
                athletes.add(athlete);

                System.out.println(
                        "-------------------------------ATHLETE ADDED SUCCESSFULLY---------------------------------");
            } else if (choice.equals("2")) {
                if (athletes.isEmpty()) {
                    System.out.println("\n\t\t\t!! NO ATHLETES ADDED. ADD ATHLETES FIRST !!");
                } else {
                    System.out.println("\n\n\n\t\t      MONTHLY FEE DETAILS FOR ALL REGISTERED ATHLETES: ");
                    for (Athletes athlete : athletes) {
                        System.out.println(athlete);
                    }
                }

            } else if (choice.equals("3")) {
                System.out.print(
                        "\n================================EXIT THE CALCULATOR.OFF===================================");
                break;
            } else {
                System.out.println("\t\t\tINVALID CHOICE. PLEASE CHOOSE [1] [2] OR [3]");

            }
        }
    }
}
