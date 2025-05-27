package com.bankingsystem.service;
import com.bankingsystem.form.CustomerForm;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface CustomerService {

    int addCustomer(CustomerForm form) throws ParseException, SQLException;

    boolean updateCustomer(CustomerForm form) throws ParseException;

    CustomerForm getCustomer(String custID);

    List<CustomerForm> getCustomers() throws SQLException;

    boolean deleteCustomer(String custID);
}
