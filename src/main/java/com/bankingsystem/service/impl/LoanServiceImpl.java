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

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LoanServiceImpl implements LoanService {
    LoanDao dao = new LoanDaoImpl();

    @Override
    public String addLoan(LoanForm form) throws ParseException, SQLException {

        CustomerService service = new CustomerServiceImpl();
        CustomerForm customer = service.getCustomer(form.getCustomerId());
        LoanEntity loanEntity = new LoanEntity();
        if (customer == null) {
            System.out.println("Customer not found with this ID. Do you want to create a new customer? (y/n)");
            Scanner sc = new Scanner(System.in);
            char option = sc.next().charAt(0);

            switch (option) {
                case 'y': {
                    CustomerForm customerForm = new CustomerForm();
                    System.out.print("Enter customer name: ");
                    customerForm.setName(sc.next());
                    System.out.print("Enter email: ");
                    customerForm.setEmail(sc.next());
                    System.out.print("Enter phone: ");
                    customerForm.setPhone(sc.next());
                    System.out.print("Enter address: ");
                    customerForm.setAddress(sc.next());
                    System.out.print("Enter DOB (yyyy-mm-dd): ");
                    customerForm.setDob(sc.next());

                    CustomerEntity entity = new CustomerEntity();
                    entity.setName(customerForm.getName());
                    entity.setEmail(customerForm.getEmail());
                    entity.setPhone(customerForm.getPhone());
                    entity.setAddress(customerForm.getAddress());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(customerForm.getDob());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    entity.setDob(sqlDate);

                    CustomerDao customerDao = new CustomerDaoImpl();
                    int createdCustId = customerDao.saveCustomer(entity);
                    System.out.println("Created customer with ID: " + createdCustId);

                    loanEntity.setCustomerId(createdCustId);
                    break;
                }

                case 'n':
                    return "Loan creation failed";

                default:
                    return "Invalid option";
            }
        } else {
            loanEntity.setCustomerId(Integer.parseInt(customer.getCustId()));
        }

        loanEntity.setLoanTypeId(Integer.parseInt(form.getLoanTypeId()));
        loanEntity.setBranchId(Integer.parseInt(form.getBranchId()));
        loanEntity.setStatusId(Integer.parseInt(form.getStatusId()));
        loanEntity.setPrincipalAmount(Double.parseDouble(form.getPricipalAmount()));
        loanEntity.setTermMonths(Integer.parseInt(form.getTermMonths()));
        LoanTypeService loanTypeService = new LoanTypeServiceImpl();
        LoanTypeForm loanTypeForm = loanTypeService.getLoanType(form.getLoanTypeId());
        double interestRate = Double.parseDouble(loanTypeForm.getInterestRate());
        loanEntity.setInterestRate(interestRate);

        String response = dao.addLoan(loanEntity);
        return response;
    }


    public boolean updateLoan(LoanForm form) throws SQLException {
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

    public boolean deleteLoan(String loanId) {
        return dao.removeLoan(Integer.parseInt(loanId));
    }

    @Override
    public LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception {
        return dao.getLoanDetailById(request);
    }

    @Override
    public List<LoanResponseForm> getAllLoanDetails() {
        return dao.getAllLoanDetails();
    }

    @Override
    public boolean processLoan(LoanForm form) throws Exception {

        LoanRequestForm requestForm = new LoanRequestForm();
        requestForm.setLoanId(form.getLoanId());
        LoanResponseForm responseForm = dao.getLoanDetailById(requestForm);

        LoanEntity updateEntity = new LoanEntity();
        updateEntity.setLoanId(Integer.parseInt(form.getLoanId()));
        int termMonths = Integer.parseInt(responseForm.getTermMonths());
        String statusName = responseForm.getStatusName();
        if(statusName.equals("REQUESTED"))
        {
            updateEntity.setStatusId(3);
            LocalDate startLocalDate = LocalDate.now();
            LocalDate endLocalDate = startLocalDate.plusMonths(termMonths);
            Date startDate = Date.valueOf(startLocalDate);
            Date endDate = Date.valueOf(endLocalDate);
            updateEntity.setStartDate(startDate);
            updateEntity.setEndDate(endDate);
            boolean isUpdated = dao.updateLoan(updateEntity);
            if(isUpdated)
                return true;
            return false;
        }
        return true;

    }


}
