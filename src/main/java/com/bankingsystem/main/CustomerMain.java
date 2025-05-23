package com.bankingsystem.main;

import com.bankingsystem.controller.CustomerController;
import com.bankingsystem.form.CustomerForm;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class CustomerMain {
    public static void main(String[] args) throws SQLException, ParseException {

        CustomerController controller = new CustomerController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice : 1.Add Customer 2.Update Customer 3. Get Customer by ID 4. Get All Customers 5. Delete Customer");
            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    CustomerForm form = new CustomerForm();
                    System.out.print("Enter Customer name: ");
                    String name = sc.next();
                    System.out.print("Enter email : ");
                    String email = sc.next();
                    System.out.print("Enter phone : ");
                    String phone = sc.next();
                    System.out.print("Enter address : ");
                    String address = sc.next();
                    System.out.println("Enter DOB (yyyy-mm-dd)");
                    String dob = sc.next();
                    form.setName(name);
                    form.setEmail(email);
                    form.setPhone(phone);
                    form.setAddress(address);
                    form.setDob(dob);
                    int generatedId = controller.addCustomer(form);
                    if(generatedId!=0&&generatedId!=-1)
                    {
                        System.out.println("Customer created with Id : "+generatedId);
                    }else{
                        System.out.println("Customer not created");
                    }
                }
                break;
                case 2: {
                    CustomerForm form = new CustomerForm();
                    System.out.println("Enter customer id to edit : ");
                    String id = sc.next();
                    System.out.print("Enter Customer name: ");
                    String name = sc.next();
                    System.out.print("Enter email : ");
                    String email = sc.next();
                    System.out.print("Enter phone : ");
                    String phone = sc.next();
                    System.out.print("Enter address : ");
                    String address = sc.next();
                    System.out.println("Enter DOB (yyyy-mm-dd)");
                    String dob = sc.next();
                    form.setCustId(id);
                    form.setName(name);
                    form.setEmail(email);
                    form.setPhone(phone);
                    form.setAddress(address);
                    form.setDob(dob);
                    boolean isUpdated = controller.updateCustomer(form);
                    if (isUpdated) {
                        System.out.println("Updated DB");
                    } else {
                        System.out.println("Not Updated");
                    }
                }
                break;
                case 3: {
                    System.out.println("Enter id to get customer details : ");
                    String custId = sc.next();
                    String customer = controller.getCustomer(custId);
                    System.out.println(customer);
                }
                break;
                case 4:
                    List<CustomerForm> customers = controller.getCustomers();
                    System.out.println("CustID\tName\tEmail\tPhone\tAddress\tDOB");

                    for (CustomerForm customer : customers) {
                        System.out.println(
                                customer.getCustId() + "\t\t" +
                                        customer.getName() + "\t\t" +
                                        customer.getEmail() + "\t\t" +
                                        customer.getPhone() + "\t\t" +
                                        customer.getAddress() + "\t\t" +
                                        customer.getDob()
                        );
                    }
                    break;

                case 5:
                {
                    System.out.println("Enter customer ID to delete : ");
                    String custID = sc.next();
                    boolean isDeleted = controller.deleteCustomer(custID);
                    if(isDeleted)
                    {
                        System.out.println("Customer deleted");
                    }else{
                        System.out.println("Customer not deleted");
                    }
                }

            }

        }
    }
}
