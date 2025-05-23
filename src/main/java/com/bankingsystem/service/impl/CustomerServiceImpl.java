package com.bankingsystem.service.impl;

import com.bankingsystem.dao.CustomerDao;
import com.bankingsystem.dao.impl.CustomerDaoImpl;
import com.bankingsystem.entity.CustomerEntity;
import com.bankingsystem.form.CustomerForm;
import com.bankingsystem.service.CustomerService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao dao = new CustomerDaoImpl();
    @Override
    public boolean addCustomer(CustomerForm form) throws ParseException, SQLException {
        CustomerEntity entity = new CustomerEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setEmail(form.getEmail());
        entity.setPhone(form.getPhone());
        java.util.Date utilDate = sdf.parse(form.getDob());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
        entity.setDob(sqlDate);
        return dao.saveCustomer(entity);
    }

    @Override
    public boolean updateCustomer(CustomerForm form) throws ParseException {
        CustomerEntity entity = new CustomerEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        entity.setCustId(Integer.parseInt(form.getCustId()));
        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setEmail(form.getEmail());
        entity.setPhone(form.getPhone());
        java.util.Date utilDate = sdf.parse(form.getDob());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
        entity.setDob(sqlDate);
        return dao.updateCustomer(entity);
    }

    @Override
    public String getCustomer(String custID) {
        return dao.getCustomer(Integer.parseInt(custID));
    }

    @Override
    public List<CustomerForm> getCustomers() throws SQLException {
        List<CustomerEntity> entities = dao.getCustomers(); // Fetch from DAO
        List<CustomerForm> forms = new ArrayList<>();

        for (CustomerEntity entity : entities) {
            CustomerForm form = new CustomerForm();
            form.setCustId(String.valueOf(entity.getCustId()));
            form.setName(entity.getName());
            form.setEmail(entity.getEmail());
            form.setPhone(entity.getPhone());
            form.setAddress(entity.getAddress());
            form.setDob(String.valueOf(entity.getDob()));
            forms.add(form);
        }

        return forms;
    }


    @Override
    public boolean deleteCustomer(String custID) {
        return dao.removeCustomer(Integer.parseInt(custID));
    }
}
