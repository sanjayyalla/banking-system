package com.bankingsystem.controller;
import com.bankingsystem.form.CustomerForm;
import com.bankingsystem.service.CustomerService;
import com.bankingsystem.service.impl.CustomerServiceImpl;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CustomerController {

        CustomerService service = new CustomerServiceImpl();
        public int addCustomer(CustomerForm form) throws SQLException, ParseException {

            if(form.getName() != null &&form.getEmail() != null && form.getPhone() != null &&form.getAddress() != null && form.getDob() != null)
            {
//                System.out.println("I am in service");
                return service.addCustomer(form);
            }
            return 0;
        }

        public boolean updateCustomer(CustomerForm form) throws ParseException {
            if(form.getName() != null &&form.getEmail() != null && form.getPhone() != null &&form.getAddress() != null && form.getDob() != null)
            {
                //System.out.println("I am in service");
                return service.updateCustomer(form);
            }
            return false;
        }

        public CustomerForm getCustomer(String custID)
        {
            return service.getCustomer(custID);
        }

        public List<CustomerForm> getCustomers() throws SQLException {
            return service.getCustomers();
        }

        public boolean deleteCustomer(String id)
        {
            if(id!=null && !id.isEmpty())
            {
                return service.deleteCustomer(id);
            }
            return false;
        }

}
