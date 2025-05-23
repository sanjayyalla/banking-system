package com.bankingsystem.main;
import com.bankingsystem.controller.LoanTypeController;
import com.bankingsystem.form.LoanTypeForm;
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
                    LoanTypeForm loan = controller.getLoanType(loanTypeId);

                    if (loan != null) {
                        System.out.println("Loan Type Details:");
                        System.out.println("ID: " + loan.getLoanTypeID());
                        System.out.println("Name: " + loan.getTypeName());
                        System.out.println("Interest Rate: " + loan.getInterestRate());
                    } else {
                        System.out.println("Loan Type not found.");
                    }
                }
                break;
                case 4: {
                    List<LoanTypeForm> loanTypes = controller.getAllLoneTypes();
                    System.out.println("Loan Type ID\t Loan Type Name\t Interest Rate");
                    for (LoanTypeForm loan : loanTypes) {
                        System.out.println(loan.getLoanTypeID() + "\t\t\t\t" + loan.getTypeName() + "\t\t\t\t " + loan.getInterestRate());
                    }
                }
                break;
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
