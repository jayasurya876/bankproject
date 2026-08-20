import java.util.Scanner;

public class LoanProcessingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Input
            System.out.println("===== BANKING LOAN APPROVAL SYSTEM =====");

            System.out.print("Enter Customer ID: ");
            String customerId = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Monthly Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Existing Loan Amount: ");
            double existingLoan = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Credit Score: ");
            int creditScore = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Employment Type: ");
            String employmentType = sc.nextLine();

            System.out.print("Enter Requested Loan Amount: ");
            double requestedLoan = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Loan Tenure (Years): ");
            int tenure = Integer.parseInt(sc.nextLine());

            // Validation
            if (age < 21 || age > 60) {
                System.out.println("Loan Status: REJECTED");
                System.out.println("Reason: Age must be between 21 and 60.");
                return;
            }

            if (salary <= 0) {
                System.out.println("Invalid salary.");
                return;
            }

            if (existingLoan < 0) {
                System.out.println("Invalid existing loan amount.");
                return;
            }

            if (creditScore < 300 || creditScore > 900) {
                System.out.println("Invalid credit score.");
                return;
            }

            if (requestedLoan <= 0) {
                System.out.println("Invalid requested loan amount.");
                return;
            }

            if (tenure <= 0) {
                System.out.println("Invalid loan tenure.");
                return;
            }

            // ------------------------------------------------
            // 1. Debt-to-Income Ratio
            // ------------------------------------------------

            // Existing monthly loan obligation is estimated
            // as 5% of the existing loan amount.
            double existingMonthlyPayment = existingLoan * 0.05;

            // ------------------------------------------------
            // 2. Interest Rate
            // ------------------------------------------------

            double interestRate;

            if (employmentType.equalsIgnoreCase("Government")) {
                interestRate = 9.0;
            } else if (employmentType.equalsIgnoreCase("Private")) {
                interestRate = 10.0;
            } else if (employmentType.equalsIgnoreCase("Self-Employed")
                    || employmentType.equalsIgnoreCase("Self Employed")) {
                interestRate = 11.0;
            } else {
                interestRate = 12.0;
            }

            // ------------------------------------------------
            // 3. Eligible Loan Amount
            // ------------------------------------------------

            double eligibleLoanAmount = salary * 60;

            // Requested loan is limited by eligibility
            double loanForCalculation =
                    Math.min(requestedLoan, eligibleLoanAmount);

            // ------------------------------------------------
            // 4. EMI Calculation
            // ------------------------------------------------

            double monthlyInterestRate =
                    interestRate / 12 / 100;

            int numberOfMonths = tenure * 12;

            double emi;

            if (monthlyInterestRate == 0) {

                emi = loanForCalculation / numberOfMonths;

            } else {

                emi = loanForCalculation
                        * monthlyInterestRate
                        * Math.pow(
                                1 + monthlyInterestRate,
                                numberOfMonths)
                        / (Math.pow(
                                1 + monthlyInterestRate,
                                numberOfMonths) - 1);
            }

            // ------------------------------------------------
            // 5. Debt-to-Income Ratio
            // ------------------------------------------------

            double totalMonthlyDebt =
                    existingMonthlyPayment + emi;

            double dti =
                    (totalMonthlyDebt / salary) * 100;

            // ------------------------------------------------
            // 6. Loan Approval
            // ------------------------------------------------

            boolean approved = true;
            String rejectionReason = "";

            if (creditScore < 650) {
                approved = false;
                rejectionReason = "Poor credit score.";
            }

            else if (existingLoan > salary * 12 * 0.50) {
                approved = false;
                rejectionReason =
                        "Existing loan exceeds allowed threshold.";
            }

            else if (requestedLoan > eligibleLoanAmount) {
                approved = false;
                rejectionReason =
                        "Requested loan exceeds eligible loan amount.";
            }

            else if (dti > 40) {
                approved = false;
                rejectionReason =
                        "Debt-to-income ratio is too high.";
            }

            // ------------------------------------------------
            // Output
            // ------------------------------------------------

            System.out.println();
            System.out.println("======================================");
            System.out.println("          LOAN APPLICATION RESULT");
            System.out.println("======================================");

            System.out.println("Customer ID          : " + customerId);
            System.out.println("Age                  : " + age);
            System.out.printf("Monthly Salary       : %.2f%n", salary);
            System.out.printf("Existing Loan        : %.2f%n", existingLoan);
            System.out.println("Credit Score         : " + creditScore);
            System.out.println("Employment Type      : " + employmentType);
            System.out.printf("Requested Loan       : %.2f%n",
                    requestedLoan);

            System.out.println("--------------------------------------");

            System.out.printf("Debt-to-Income Ratio : %.2f%%%n", dti);

            System.out.printf("Eligible Loan Amount : %.2f%n",
                    eligibleLoanAmount);

            System.out.printf("Interest Rate        : %.2f%%%n",
                    interestRate);

            System.out.printf("EMI                  : %.2f%n", emi);

            System.out.println("--------------------------------------");

            if (approved) {
                System.out.println("Loan Status          : APPROVED");
            } else {
                System.out.println("Loan Status          : REJECTED");
                System.out.println("Reason               : "
                        + rejectionReason);
            }

            System.out.println("======================================");

        } catch (NumberFormatException e) {

            System.out.println(
                    "ERROR: Invalid numeric input.");

        } catch (Exception e) {

            System.out.println(
                    "ERROR: " + e.getMessage());

        } finally {

            sc.close();
        }
    }
}
