package com.bankingsystem.dao;

import com.bankingsystem.entity.CustomerEntity;
import com.bankingsystem.form.CustomerForm;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    int saveCustomer(CustomerEntity entity) throws SQLException;

    boolean updateCustomer(CustomerEntity entity);

    String getCustomer(int custID);

    List<CustomerEntity> getCustomers() throws SQLException;

    boolean removeCustomer(int custID);
}
