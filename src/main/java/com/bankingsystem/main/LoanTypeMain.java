package com.bankingsystem.main;

import com.bankingsystem.controller.LoanTypeController;
import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.entity.LoanTypeEntity;
import com.bankingsystem.form.BranchForm;
import com.bankingsystem.form.LoanTypeForm;
import com.bankingsystem.service.BranchService;
import com.bankingsystem.service.impl.BranchServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoanTypeMain {
    public static void main(String[] args) throws SQLException {

        LoanTypeController controller = new LoanTypeController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice : 1.Add Loan Type 2.Update Loan Type 3. Get Loan Type by ID 4. Get All Loan Types 5. Delete Loan Type");
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    LoanTypeForm form = new LoanTypeForm();
                    System.out.print("Enter Loan Type name: ");
                    String name = sc.next();
                    System.out.print("Enter interest  : ");
                    String interest = sc.next();

                    form.setTypeName(name);
                    form.setInterestRate(interest);

                    boolean isInserted = controller.addLoanType(form);
                    if (isInserted) {
                        System.out.println("Inserted into DB");
                    } else {
                        System.out.println("Not Insterted");
                    }
                }
                break;
                case 2: {
                    LoanTypeForm form = new LoanTypeForm();
                    System.out.println("Enter loan type id that you want to update: ");
                    String loanTypeID = sc.next();
                    form.setLoanTypeID(loanTypeID);
                    System.out.print("Enter Loan type name: ");
                    String name = sc.next();
                    System.out.print("Enter interest : ");
                    String interest = sc.next();
                    form.setTypeName(name);
                    form.setLoanTypeID(loanTypeID);
                    form.setInterestRate(interest);
                    boolean isUpdated = controller.updateLoanType(form);
                    if(isUpdated)
                    {
                        System.out.println("Loan type details details updated");
                    }else{
                        System.out.println("Loan type  details updation failed");
                    }
                }
                break;
                case 3: {
                    System.out.print("Enter loan type ID to get details : ");
                    String loanTypeId = sc.next();
                    String loanTypeDetails = controller.getLoanType(loanTypeId);
                    System.out.println(loanTypeDetails);
                }
                break;
                case 4:{
                    List<LoanTypeEntity> loanTypes = controller.getAllLoneTypes();
                    for (LoanTypeEntity loan : loanTypes) {
                        System.out.println(loan.getLoanTypeID() + " " + loan.getTypeName()+" "+loan.getInterestRate());

                    }
                }
                case 5:{
                    System.out.println("Enter loan type id to delete : ");
                    String id = sc.next();
                    boolean isDeleted = controller.deleteLoanType(id);
                    if(isDeleted)
                    {
                        System.out.println("Branch deleted");
                    }else{
                        System.out.println("Branch Not deleted");
                    }
                }
            }

        }
    }
}
