package com.bankingsystem.main;

import com.bankingsystem.controller.LoanController;
import com.bankingsystem.form.LoanForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class LoanMain {
    public static void main(String[] args) throws Exception {
        LoanController controller = new LoanController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add loan 2.Update Loan 3.Get Loan Details By ID 4.Get All Loan details 5.Delete Loan 6.Process Loan");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    LoanForm form = new LoanForm();
                    System.out.println("Enter customer ID:");
                    String customerId = sc.next();
                    form.setCustomerId(customerId);
                    System.out.println("Enter Loan type ID:");
                    String loanTypeId = sc.next();
                    form.setLoanTypeId(loanTypeId);
                    System.out.println("Enter branch ID");
                    String branchId = sc.next();
                    form.setBranchId(branchId);
                    System.out.println("Enter status ID");
                    String statusId = sc.next();
                    form.setStatusId(statusId);
                    System.out.println("Enter principal amount :");
                    String principalAmount = sc.next();
                    form.setPricipalAmount(principalAmount);
                    System.out.println("Enter term months :");
                    String termMonths = sc.next();
                    form.setTermMonths(termMonths);

                    form.setStatusId(statusId);
                    String res = controller.addLoan(form);
                    System.out.println(res);
                }
                    break;
                case 2: {
                    LoanForm form1 = new LoanForm();
                    Scanner scLine = new Scanner(System.in);
                    System.out.print("Enter loan id to update: ");
                    String loanId = scLine.nextLine();
                    form1.setLoanId(loanId);

                    System.out.print("Enter loan type id : ");
                    String loanTypeId = scLine.nextLine();
                    if (!loanTypeId.isEmpty()) {
                        form1.setLoanTypeId(loanTypeId);
                    }

                    System.out.print("Enter branch id : ");
                    String branchId = scLine.nextLine();
                    if (!branchId.isEmpty()) {
                        form1.setBranchId(branchId);
                    }

                    System.out.print("Enter status id : ");
                    String statusId = scLine.nextLine();
                    if (!statusId.isEmpty()) {
                        form1.setStatusId(statusId);
                    }

                    System.out.print("Enter principal amount : ");
                    String principalAmount = scLine.nextLine();
                    if (!principalAmount.isEmpty()) {
                        form1.setPricipalAmount(principalAmount);
                    }


                    System.out.print("Enter term months : ");
                    String termMonths = scLine.nextLine();
                    if (!termMonths.isEmpty()) {
                        form1.setTermMonths(termMonths);
                    }

                    System.out.print("Enter start date (yyyy-mm-dd): ");
                    String startDate = scLine.nextLine();
                    if (!startDate.isEmpty()) {
                        form1.setStartDate(startDate);
                    }

                    System.out.print("Enter end date (yyyy-mm-dd): ");
                    String endDate = scLine.nextLine();
                    if (!endDate.isEmpty()) {
                        form1.setEndDate(endDate);
                    }

                    boolean isUpdated = controller.updateLoan(form1);
                    if (isUpdated) {
                        System.out.println("Loan details updated");
                    } else {
                        System.out.println("Loan details not updated");
                    }

                }
                break;
                case 3: {
                    System.out.print("Enter Loan ID to get details: ");
                    String loanId = sc.next();
                    LoanRequestForm request = new LoanRequestForm();
                    request.setLoanId(loanId);
                    try {
                        LoanResponseForm response = controller.getLoanDetailById(request);
                        if (response != null) {
                            System.out.println("Loan Details:");
                            System.out.println("Loan ID: " + response.getLoanId());
                            System.out.println("Customer Name: " + response.getCustomerName());
                            System.out.println("Loan Type: " + response.getLoanTypeName());
                            System.out.println("Branch: " + response.getBranchName());
                            System.out.println("Status: " + response.getStatusName());
                            System.out.println("Principal Amount: " + response.getPrincipalAmount());
                            System.out.println("Interest Rate: " + response.getInterestRate());
                            System.out.println("Term Months: " + response.getTermMonths());
                            System.out.println("Start Date: " + response.getStartDate());
                            System.out.println("End Date: " + response.getEndDate());
                        } else {
                            System.out.println("Loan not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error fetching loan details: " + e.getMessage());
                    }
                }
                break;
                case 4:{
                    List<LoanResponseForm>  loanResponseFormList =  controller.getAllLoanDetails();
                    for (LoanResponseForm loan : loanResponseFormList) {
                        System.out.println("Loan Details:");
                        System.out.println("Loan ID: " + loan.getLoanId());
                        System.out.println("Customer Name: " + loan.getCustomerName());
                        System.out.println("Loan Type: " + loan.getLoanTypeName());
                        System.out.println("Branch: " + loan.getBranchName());
                        System.out.println("Status: " + loan.getStatusName());
                        System.out.println("Principal Amount: " + loan.getPrincipalAmount());
                        System.out.println("Interest Rate: " + loan.getInterestRate());
                        System.out.println("Term Months: " + loan.getTermMonths());
                        System.out.println("Start Date: " + loan.getStartDate());
                        System.out.println("End Date: " + loan.getEndDate());
                    }

                }
                break;
                case 5:{
                    System.out.print("Enter Loan id to delete :");
                    String loanId =sc.next();
                    boolean isDeleted = controller.deleteLoan(loanId);
                    if(isDeleted)
                    {
                        System.out.println("Loan deleted");
                    }else{
                        System.out.println("Loan deletion failed");
                    }
                }
                break;
                case 6:{
                    LoanForm form = new LoanForm();
                    System.out.println("Enter loan id to process : ");
                    String loanId = sc.next();
                    form.setLoanId(loanId);
                    boolean isUpdated = controller.processLoan(form);
                    if(isUpdated)
                    {
                        System.out.println("Updation success");
                    }else{
                        System.out.println("Updation failed");
                    }
                }

            }
        }

    }
}
