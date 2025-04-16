
package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;

    private boolean isForeigner;
    private boolean gender; //true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(EmployeeBuilder builder) {
        this.employeeId = builder.employeeId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.idNumber = builder.idNumber;
        this.address = builder.address;
        this.yearJoined = builder.yearJoined;
        this.monthJoined = builder.monthJoined;
        this.dayJoined = builder.dayJoined;
        this.isForeigner = builder.isForeigner;
        this.gender = builder.gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }

    public void setMonthlySalary(int grade) {
        int baseSalary;
        switch (grade) {
            case 1: baseSalary = 3000000; break;
            case 2: baseSalary = 5000000; break;
            case 3: baseSalary = 7000000; break;
            default: baseSalary = 0;
        }
        this.monthlySalary = calculateSalary(baseSalary);
    }

    private int calculateSalary(int baseSalary) {
        return isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        int monthWorkingInYear;
        LocalDate date = LocalDate.now();

        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber == null || spouseIdNumber.isEmpty(), childIdNumbers.size());
    }

    public static class EmployeeBuilder {
        private String employeeId;
        private String firstName;
        private String lastName;
        private String idNumber;
        private String address;

        private int yearJoined;
        private int monthJoined;
        private int dayJoined;

        private boolean isForeigner;
        private boolean gender;

        public EmployeeBuilder(String employeeId, String firstName, String lastName) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public EmployeeBuilder idNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public EmployeeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder yearJoined(int year) {
            this.yearJoined = year;
            return this;
        }

        public EmployeeBuilder monthJoined(int month) {
            this.monthJoined = month;
            return this;
        }

        public EmployeeBuilder dayJoined(int day) {
            this.dayJoined = day;
            return this;
        }

        public EmployeeBuilder isForeigner(boolean isForeigner) {
            this.isForeigner = isForeigner;
            return this;
        }

        public EmployeeBuilder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
