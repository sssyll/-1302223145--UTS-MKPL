package lib;

public class TaxFunction {

    private static final int BASIC_NONTAXABLE = 54000000;
    private static final int MARRIED_ALLOWANCE = 4500000;
    private static final int CHILD_ALLOWANCE = 1500000;
    private static final double TAX_RATE = 0.05;
    private static final int MAX_CHILDREN = 3;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int annualIncome = getAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = getNonTaxableIncome(isMarried, numberOfChildren);
        int taxableIncome = annualIncome - deductible - nonTaxableIncome;

        return Math.max(0, (int) Math.round(taxableIncome * TAX_RATE));
    }

    private static int getAnnualIncome(int salary, int otherIncome, int months) {
        return (salary + otherIncome) * months;
    }

    private static int getNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = BASIC_NONTAXABLE;
        if (isMarried) {
            nonTaxable += MARRIED_ALLOWANCE;
        }
        nonTaxable += numberOfChildren * CHILD_ALLOWANCE;
        return nonTaxable;
    }
}
