package com.bankingsystem.service.impl;

import com.bankingsystem.dao.CustomerDao;
import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.dao.impl.CustomerDaoImpl;
import com.bankingsystem.dao.impl.LoanDaoImpl;
import com.bankingsystem.entity.CustomerEntity;
import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.entity.LoanTypeEntity;
import com.bankingsystem.form.CustomerForm;
import com.bankingsystem.form.LoanForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.form.LoanTypeForm;
import com.bankingsystem.service.CustomerService;
import com.bankingsystem.service.LoanService;
import com.bankingsystem.service.LoanTypeService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LoanServiceImpl implements LoanService {
    LoanDao dao = new LoanDaoImpl();

    @Override
    public String addLoan(LoanForm form) throws ParseException, SQLException {
        CustomerService service = new CustomerServiceImpl();
        String returnedString = service.getCustomer(form.getCustomerId());
        LoanEntity loanEntity = new LoanEntity();
        if(returnedString==null)
        {
            System.out.println("Customer not found with this id do you want to create new customer(y/n)");
            Scanner sc = new Scanner(System.in);
            char option = sc.next().charAt(0);
            switch (option)
            {
                case 'y':
                    CustomerForm customerForm = new CustomerForm();
                    System.out.print("Enter customer name:");
                    String name = sc.next();
                    System.out.print("Enter email:");
                    String email = sc.next();
                    System.out.print("Enter phone:");
                    String phone = sc.next();
                    System.out.print("Enter address:");
                    String address = sc.next();
                    System.out.print("Enter DOB:(yyyy-mm-dd)");
                    String dob = sc.next();
                    customerForm.setName(name);
                    customerForm.setEmail(email);
                    customerForm.setPhone(phone);
                    customerForm.setAddress(address);
                    customerForm.setDob(dob);

                    CustomerEntity entity = new CustomerEntity();
                    CustomerDao customerDao = new CustomerDaoImpl();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    entity.setName(customerForm.getName());
                    entity.setEmail(customerForm.getEmail());
                    entity.setPhone(customerForm.getPhone());
                    entity.setAddress(customerForm.getAddress());
                    java.util.Date utilDate = sdf.parse(customerForm.getDob());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    entity.setDob(sqlDate);
                    int createdCustId =  customerDao.saveCustomer(entity);
                    System.out.println("Created customer with ID : "+createdCustId);
                    loanEntity.setCustomerId(createdCustId);
                    loanEntity.setLoanTypeId(Integer.parseInt(form.getLoanTypeId()));
                    loanEntity.setBranchId(Integer.parseInt(form.getBranchId()));
                    loanEntity.setStatusId(Integer.parseInt(form.getStatusId()));
                    loanEntity.setPrincipalAmount(Double.parseDouble(form.getPricipalAmount()));
                    LoanTypeService loanTypeService = new LoanTypeServiceImpl();
                    LoanTypeForm loanTypeForm = loanTypeService.getLoanType(form.getLoanTypeId());
                    double interestRate = Double.parseDouble(loanTypeForm.getInterestRate());
                    loanEntity.setInterestRate(interestRate);
                    String response = dao.addLoan(loanEntity);
                    return response;
                case 'n': return "Loan creation failed";
            }
        }else{
            loanEntity.setCustomerId(Integer.parseInt(form.getCustomerId()));
            loanEntity.setLoanTypeId(Integer.parseInt(form.getLoanTypeId()));
            loanEntity.setBranchId(Integer.parseInt(form.getBranchId()));
            loanEntity.setStatusId(Integer.parseInt(form.getStatusId()));
            loanEntity.setPrincipalAmount(Double.parseDouble(form.getPricipalAmount()));
            LoanTypeService loanTypeService = new LoanTypeServiceImpl();
            LoanTypeForm loanTypeForm = loanTypeService.getLoanType(form.getLoanTypeId());
            double interestRate = Double.parseDouble(loanTypeForm.getInterestRate());
            loanEntity.setInterestRate(interestRate);
            String res = dao.addLoan(loanEntity);
            return res;

        }
        return null;
    }
    public boolean updateLoan(LoanForm form)
    {
        LoanEntity entity = new LoanEntity();
        LoanTypeService loanTypeService = new LoanTypeServiceImpl();
        entity.setLoanId(Integer.parseInt(form.getLoanId()));
        entity.setLoanTypeId(Integer.parseInt(form.getLoanTypeId()));
        entity.setBranchId(Integer.parseInt(form.getBranchId()));
        entity.setStatusId(Integer.parseInt(form.getStatusId()));
        entity.setPrincipalAmount(Integer.parseInt(form.getPricipalAmount()));
        LoanTypeForm loanTypeForm = loanTypeService.getLoanType(form.getLoanTypeId());
        double interestRate = Double.parseDouble(loanTypeForm.getInterestRate());
        entity.setInterestRate(interestRate);
        return dao.updateLoan(entity);
    }
    public boolean deleteLoan(String loanId)
    {
        return dao.removeLoan(Integer.parseInt(loanId));
    }
    @Override
    public LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception {
        return dao.getLoanDetailById(request);
    }

}
