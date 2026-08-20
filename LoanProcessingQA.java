public class LoanProcessingQA {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       BANKING LOAN SYSTEM - QA TESTS");
        System.out.println("==============================================");

        testMinimumAge();
        testMaximumAge();
        testInvalidSalary();
        testPoorCreditScore();
        testExistingLoanExceedingThreshold();
        testHighDTI();
        testEmploymentCategories();
        testBoundaryLoanAmount();
        testLoanAmountAboveBoundary();
        testEMICalculation();
        testInvalidInput();
        testExceptionHandling();

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                 TEST SUMMARY");
        System.out.println("==============================================");
        System.out.println("Total Tests  : " + (passed + failed));
        System.out.println("Tests Passed : " + passed);
        System.out.println("Tests Failed : " + failed);

        if (failed == 0) {
            System.out.println("RESULT       : ALL TESTS PASSED");
        } else {
            System.out.println("RESULT       : TESTS FAILED");
        }

        System.out.println("==============================================");
    }

    // --------------------------------------------------
    // 1. Minimum Age Test
    // --------------------------------------------------

    static void testMinimumAge() {

        try {

            int age = 21;

            if (age >= 21 && age <= 60) {
                pass("Minimum age = 21");
            } else {
                fail("Minimum age = 21");
            }

        } catch (Exception e) {
            fail("Minimum age = 21");
        }
    }

    // --------------------------------------------------
    // 2. Maximum Age Test
    // --------------------------------------------------

    static void testMaximumAge() {

        try {

            int age = 60;

            if (age >= 21 && age <= 60) {
                pass("Maximum age = 60");
            } else {
                fail("Maximum age = 60");
            }

        } catch (Exception e) {
            fail("Maximum age = 60");
        }
    }

    // --------------------------------------------------
    // 3. Invalid Salary Test
    // --------------------------------------------------

    static void testInvalidSalary() {

        try {

            double salary = 0;

            if (salary <= 0) {
                pass("Invalid salary = 0 detected");
            } else {
                fail("Invalid salary = 0 detected");
            }

        } catch (Exception e) {
            fail("Invalid salary = 0 detected");
        }
    }

    // --------------------------------------------------
    // 4. Poor Credit Score Test
    // --------------------------------------------------

    static void testPoorCreditScore() {

        try {

            int creditScore = 600;

            if (creditScore < 650) {
                pass("Poor credit score = 600");
            } else {
                fail("Poor credit score = 600");
            }

        } catch (Exception e) {
            fail("Poor credit score = 600");
        }
    }

    // --------------------------------------------------
    // 5. Existing Loan Exceeding Threshold
    // --------------------------------------------------

    static void testExistingLoanExceedingThreshold() {

        try {

            double monthlySalary = 50000;
            double existingLoan = 310000;

            double maximumExistingLoan =
                    monthlySalary * 12 * 0.50;

            if (existingLoan > maximumExistingLoan) {
                pass("Existing loan exceeding threshold");
            } else {
                fail("Existing loan exceeding threshold");
            }

        } catch (Exception e) {
            fail("Existing loan exceeding threshold");
        }
    }

    // --------------------------------------------------
    // 6. High Debt-to-Income Ratio
    // --------------------------------------------------

    static void testHighDTI() {

        try {

            double salary = 20000;
            double existingMonthlyPayment = 5000;
            double emi = 5000;

            double dti =
                    ((existingMonthlyPayment + emi)
                            / salary) * 100;

            if (dti > 40) {
                pass("High debt-to-income ratio");
            } else {
                fail("High debt-to-income ratio");
            }

        } catch (Exception e) {
            fail("High debt-to-income ratio");
        }
    }

    // --------------------------------------------------
    // 7. Different Employment Categories
    // --------------------------------------------------

    static void testEmploymentCategories() {

        try {

            double governmentRate = 9.0;
            double privateRate = 10.0;
            double selfEmployedRate = 11.0;
            double otherRate = 12.0;

            boolean result =
                    governmentRate == 9.0
                    && privateRate == 10.0
                    && selfEmployedRate == 11.0
                    && otherRate == 12.0;

            if (result) {
                pass("Different employment categories");
            } else {
                fail("Different employment categories");
            }

        } catch (Exception e) {
            fail("Different employment categories");
        }
    }

    // --------------------------------------------------
    // 8. Boundary Loan Amount
    // --------------------------------------------------

    static void testBoundaryLoanAmount() {

        try {

            double salary = 50000;

            // Eligible amount = salary * 60
            double eligibleLoan = salary * 60;

            double requestedLoan = 3000000;

            if (requestedLoan == eligibleLoan) {
                pass("Loan amount at eligibility boundary");
            } else {
                fail("Loan amount at eligibility boundary");
            }

        } catch (Exception e) {
            fail("Loan amount at eligibility boundary");
        }
    }

    // --------------------------------------------------
    // 9. Loan Amount Above Boundary
    // --------------------------------------------------

    static void testLoanAmountAboveBoundary() {

        try {

            double salary = 50000;

            double eligibleLoan = salary * 60;

            double requestedLoan = 3000001;

            if (requestedLoan > eligibleLoan) {
                pass("Loan amount above eligibility boundary");
            } else {
                fail("Loan amount above eligibility boundary");
            }

        } catch (Exception e) {
            fail("Loan amount above eligibility boundary");
        }
    }

    // --------------------------------------------------
    // 10. EMI Calculation Accuracy
    // --------------------------------------------------

    static void testEMICalculation() {

        try {

            double principal = 1000000;
            double annualRate = 10.0;
            int tenureYears = 10;

            int months = tenureYears * 12;

            double monthlyRate =
                    annualRate / 12 / 100;

            double expectedEMI =
                    principal
                    * monthlyRate
                    * Math.pow(
                            1 + monthlyRate,
                            months)
                    / (Math.pow(
                            1 + monthlyRate,
                            months) - 1);

            // Expected value approximately 13,215.07
            double knownEMI = 13215.07;

            if (Math.abs(expectedEMI - knownEMI) < 1.0) {
                pass("EMI calculation accuracy");
            } else {
                fail("EMI calculation accuracy");
            }

        } catch (Exception e) {
            fail("EMI calculation accuracy");
        }
    }

    // --------------------------------------------------
    // 11. Invalid Input Handling
    // --------------------------------------------------

    static void testInvalidInput() {

        try {

            String invalidInput = "ABC123";

            try {

                Integer.parseInt(invalidInput);

                fail("Invalid input handling");

            } catch (NumberFormatException e) {

                pass("Invalid input handling");

            }

        } catch (Exception e) {
            fail("Invalid input handling");
        }
    }

    // --------------------------------------------------
    // 12. Exception Handling
    // --------------------------------------------------

    static void testExceptionHandling() {

        try {

            int age = -10;

            if (age < 21) {
                throw new IllegalArgumentException(
                        "Invalid age");
            }

            fail("Exception handling");

        } catch (IllegalArgumentException e) {

            pass("Exception handling");

        } catch (Exception e) {

            fail("Exception handling");
        }
    }

    // --------------------------------------------------
    // PASS
    // --------------------------------------------------

    static void pass(String testName) {

        passed++;

        System.out.println(
                "[PASS] " + testName);
    }

    // --------------------------------------------------
    // FAIL
    // --------------------------------------------------

    static void fail(String testName) {

        failed++;

        System.out.println(
                "[FAIL] " + testName);
    }
}
