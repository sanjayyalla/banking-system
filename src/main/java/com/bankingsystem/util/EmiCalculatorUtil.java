package com.bankingsystem.util;

import com.bankingsystem.form.EMI;

import java.util.ArrayList;
import java.util.List;

public class EmiCalculatorUtil {
    public List<EMI> calculateEmi(double principal, double annualRate, int termMonths){

        List<EMI> emiList = new ArrayList<>();
        double monthlyRate = annualRate / (12 * 100);
        double emiAmount = (principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
                (Math.pow(1 + monthlyRate, termMonths) - 1);
        double remainingPrincipal = principal;

        for (int i = 1; i <= termMonths; i++) {
            EMI emi = new EMI();
            double interest = remainingPrincipal * monthlyRate;
            double principalComponent = emiAmount - interest;

            emi.setInterest(round(interest));
            emi.setPrincipalAmount(round(principalComponent));

            remainingPrincipal -= principalComponent;

            emiList.add(emi);
        }

        return emiList;
    }
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

//
//package com.bankingsystem.util;
//
//import com.bankingsystem.form.EMI;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmiCalculatorUtil {
//
//    public List<EMI> calculateEmi(double principal, double annualRate, int termMonths) {
//
//        List<EMI> emiList = new ArrayList<>();
//        double monthlyRate = annualRate / (12 * 100);
//        double emiAmount = (principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
//                (Math.pow(1 + monthlyRate, termMonths) - 1);
//        double remainingPrincipal = principal;
//
//        for (int i = 1; i <= termMonths; i++) {
//            EMI emi = new EMI();
//            double interest = remainingPrincipal * monthlyRate;
//            double principalComponent = emiAmount - interest;
//
//            emi.setInterest(round(interest));
//            emi.setPrincipalAmount(round(principalComponent));
//
//            remainingPrincipal -= principalComponent;
//
//            emiList.add(emi);
//        }
//
//        return emiList;
//    }
//
//    private double round(double value) {
//        return Math.round(value * 100.0) / 100.0;
//    }
//
//    public static void main(String[] args) {
//        EmiCalculatorUtil util = new EmiCalculatorUtil();
//
//        double principal = 500000;
//        double annualRate = 10;
//        int termMonths = 12;
//
//        List<EMI> emiList = util.calculateEmi(principal, annualRate, termMonths);
//
//        System.out.printf("%-10s %-15s %-15s%n", "Month", "Interest", "Principal");
//
//        int month = 1;
//        for (EMI emi : emiList) {
//            System.out.printf("%-10d %-15.2f %-15.2f%n", month++, emi.getInterest(), emi.getPrincipalAmount());
//        }
//    }
//}
