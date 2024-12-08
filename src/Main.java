
import java.util.*;
// Method to calculate depreciation cost (Using straight-line depreciation for simplicity)

public class Main {
    private static double calculateEMI(double principal, double annualInterestRate, int tenureYears) {
        double monthlyInterestRate = annualInterestRate / (12 * 100);
        int tenureMonths = tenureYears * 12; return (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) / (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);
    }

    private static double calculateTotalInterest(double principal, double emi, int tenureYears) {
        int tenureMonths = tenureYears * 12;
        return (emi * tenureMonths) - principal;
    }

    private static double calculateDepreciation(double initialCost, int tenureYears) {
        int usefulLifeYears = 20;
        return (initialCost / usefulLifeYears) * tenureYears;
    }

    public static double calculateAppreciation(double initialCost, int tenureYears) {

        double appreciationRate = 0.03;
        return initialCost * Math.pow(1 + appreciationRate, tenureYears) - initialCost;
    }

    public static double calculateSIPReturns(double monthlyInvestment, double annualReturnRate, int tenureYears) {
        double monthlyReturnRate = annualReturnRate / 12 / 100;
        int tenureMonths = tenureYears * 12; double futureValue = 0;
        for (int i = 0; i < tenureMonths; i++) {
            futureValue += monthlyInvestment * Math.pow(1 + monthlyReturnRate, tenureMonths - i);
        }
        return futureValue;
    }


    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    // Input for home loan
    System.out.print("Enter home loan amount (in Lakhs): ");
    double homeLoanAmount = scanner.nextDouble() * 100000;
    System.out.print("Enter annual interest rate for home loan (in %): ");
    double homeLoanInterestRate = scanner.nextDouble();
    System.out.print("Enter loan tenure (in years): ");
    int loanTenure = scanner.nextInt();
    // Input for SIP
    System.out.print("Enter SIP monthly investment amount (in ₹): ");
    double sipMonthlyInvestment = scanner.nextDouble();
    System.out.print("Enter expected annual return rate for SIP (in %): ");
    double sipReturnRate = scanner.nextDouble();
    // Calculate EMI, total interest, and depreciation
    double emi = calculateEMI(homeLoanAmount, homeLoanInterestRate, loanTenure);
    double totalInterestPaid = calculateTotalInterest(homeLoanAmount, emi, loanTenure);
    double depreciationCost = calculateDepreciation(homeLoanAmount, loanTenure);
    double assetAppreciation = calculateAppreciation(homeLoanAmount, loanTenure);
    // Calculate SIP returns
    double sipTotalReturns = calculateSIPReturns(sipMonthlyInvestment, sipReturnRate, loanTenure);
    // Print results
    System.out.printf("Home Loan EMI: ₹%.2f\n", emi);
    System.out.printf("Total Interest Paid: ₹%.2f\n", totalInterestPaid);
    System.out.printf("Depreciation Cost: ₹%.2f\n", depreciationCost);
    System.out.printf("Asset Price Appreciation: ₹%.2f\n", assetAppreciation);
    System.out.printf("Total SIP Returns: ₹%.2f\n", sipTotalReturns);
    // Summary
    System.out.println("Summary of Calculation:");
    System.out.printf("Total cost of home ownership after %d years: ₹%.2f\n", loanTenure, homeLoanAmount + totalInterestPaid + depreciationCost - assetAppreciation);
    System.out.printf("Total value of SIP investment after %d years: ₹%.2f\n", loanTenure, sipTotalReturns); }

}