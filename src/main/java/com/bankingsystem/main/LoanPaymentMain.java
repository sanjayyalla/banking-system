package com.bankingsystem.main;

import com.bankingsystem.controller.LoanPaymentController;
import com.bankingsystem.form.LoanPaymentForm;

import java.util.List;
import java.util.Scanner;

public class LoanPaymentMain {
    public static void main(String[] args) {
        LoanPaymentController controller = new LoanPaymentController();
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Add Loan EMI Payment 2.Get all Loan Payment Details 3.Save all loan payment details to TXT file 4. Delete payment through ID 5.Save file through loan id");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.print("Enter Loan ID: ");
                    int loanId = sc.nextInt();
                    try {
                        String result = controller.addLoanPayment(loanId);
                        System.out.println(result);
                    } catch (Exception e) {
                        System.out.println("Error adding loan payment: " + e.getMessage());
                    }
                }
                break;
                case 2: {
                    List<LoanPaymentForm> forms = controller.getAllLoanPayments();

                    if (forms.isEmpty()) {
                        System.out.println("No loan payment records found.");
                    } else {
                        System.out.println("LoanID\tPaymentDate\tAmountPaid\tPrincipalComponent\tInterestComponent");
                        for (LoanPaymentForm form : forms) {
                            System.out.println(
                                            form.getLoanId() + "\t" +
                                            form.getPaymentDate() + "\t\t" +
                                            form.getAmountPaid() + "\t\t\t" +
                                            form.getInterestComponent() + "\t\t\t" +
                                            form.getInterestComponent()
                            );
                        }
                    }
                }
                break;
                case 3:{
                    boolean isSavedToFile = controller.saveAllLoanPaymentDetails();
                    if(isSavedToFile){
                        System.out.println("Saved to the text file");
                    }else{
                        System.out.println("Saving to the text file failed");
                    }
                }
                break;
                case 4:{
                    System.out.println("Enter payment id : ");
                    String paymentId = sc.next();
                    boolean isDeleted = controller.deleteLoanPayment(paymentId);
                    if(isDeleted){
                        System.out.println("Deleted the payment");
                    }else{
                        System.out.println("Payment deletion failed");
                    }
                }
                case 5:{
                    System.out.println("Enter loan id to save transaction history: ");
                    String loanId = sc.next();
                    boolean isHistorySaved = controller.saveLoanPaymentTransactionHistory(loanId);
                    if(isHistorySaved)
                    {
                        System.out.println("Data saved of loanId : "+loanId);
                    }else{
                        System.out.println("Data saving failed");
                    }
                }
                break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

