package com.bankingsystem.main;
import com.bankingsystem.controller.BranchController;
import com.bankingsystem.form.BranchForm;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BranchMain {

    public static void main(String[] args) throws SQLException {

        BranchController controller = new BranchController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice : 1.Add Branch 2.Update Branch 3. Get Branch by ID 4. Get All Branches 5. Delete Branch");
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    BranchForm form = new BranchForm();
                    System.out.print("Enter Branch name: ");
                    String name = sc.next();
                    System.out.print("Enter address : ");
                    String address = sc.next();
                    System.out.print("Enter City : ");
                    String city = sc.next();
                    System.out.print("Enter state : ");
                    String state = sc.next();
                    form.setName(name);
                    form.setAddress(address);
                    form.setCity(city);
                    form.setState(state);
                    boolean isInserted = controller.addBranch(form);
                    if (isInserted) {
                        System.out.println("Inserted into DB");
                    } else {
                        System.out.println("Not Insterted");
                    }
                }
                break;

                case 2: {
                    BranchForm branchForm = new BranchForm();
                    System.out.println("Enter branch id that you want to update: ");
                    String branchId = sc.next();
                    branchForm.setBranchId(branchId);
                    System.out.print("Enter Branch name: ");
                    String name = sc.next();
                    System.out.print("Enter address : ");
                    String address = sc.next();
                    System.out.print("Enter City : ");
                    String city = sc.next();
                    System.out.print("Enter state : ");
                    String state = sc.next();
                    branchForm.setName(name);
                    branchForm.setAddress(address);
                    branchForm.setCity(city);
                    branchForm.setState(state);
                    boolean isUpdated = controller.updateBranch(branchForm);
                    if (isUpdated) {
                        System.out.println("Branch details updated");
                    } else {
                        System.out.println("Branch details updation failed");
                    }
                }
                break;

                case 3: {
                    System.out.print("Enter Branch ID to get details : ");
                    String branchId = sc.next();
                    String branchDetails = controller.getBranch(branchId);
                    System.out.println(branchDetails);
                }
                break;

                case 4: {
                    List<BranchForm> branches = controller.getAllBranches();
                    for (BranchForm branch : branches) {
                        System.out.println(branch.getBranchId() + " " + branch.getName() + " " +
                                branch.getAddress() + " " + branch.getCity() + " " + branch.getState());
                    }
                }
                break;

                case 5: {
                    System.out.println("Enter id to delete : ");
                    String id = sc.next();
                    boolean isDeleted = controller.deleteBranch(id);
                    if (isDeleted) {
                        System.out.println("Branch deleted");
                    } else {
                        System.out.println("Branch Not deleted");
                    }
                }
            }

        }
    }
}
