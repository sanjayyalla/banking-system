package com.bankingsystem.main;

import com.bankingsystem.controller.LoanStatusController;
import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.form.LoanStatusForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoanStatusMain {

    public static void main(String[] args) throws SQLException {

        LoanStatusController controller = new LoanStatusController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice : 1.Add Status 2.Update Status 3. Get status by ID 4. Get All Statuses 5. Delete status");
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    LoanStatusForm form = new LoanStatusForm();
                    System.out.println("Enter status");
                    String status = sc.next();
                    form.setStatusName(status);
                    boolean isInserted = controller.addStatus(form);
                    if (isInserted) {
                        System.out.println("Inserted into DB");
                    } else {
                        System.out.println("Not Insterted");
                    }
                }
                    break;
                case 2: {
                    LoanStatusForm form1 = new LoanStatusForm();
                    System.out.println("Enter status id to update : ");
                    String id = sc.next();
                    form1.setStatusId(id);
                    System.out.println("Enter the updated string :");
                    String text = sc.next();
                    form1.setStatusName(text);
                    boolean isUpdated = controller.updateStatus(form1);
                    if (isUpdated) {
                        System.out.println("Row updated with id: " + id);
                    } else {
                        System.out.println("Row not updated try again");
                    }
                }
                case 3 : {
                    System.out.print("Enter ID : ");
                    String id = sc.next();
                    System.out.println(controller.getStatus(id));
                }
                    break;
                case 4:
                    List<LoanStatusForm> statuses = controller.getAllStatus();
                    for (LoanStatusForm status : statuses) {
                        System.out.println(status.getStatusId() + " " + status.getStatusName());
                    }
                    break;
                case 5: {
                    System.out.println("Enter id to delete : ");
                    String id = sc.next();
                    boolean isDeleted = controller.deleteStatus(id);
                    if(isDeleted)
                    {
                        System.out.println("Status deleted");
                    }else{
                        System.out.println("Not deleted");
                    }
                }
            }

        }
    }
}
