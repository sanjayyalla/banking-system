package com.bankingsystem.controller;
import com.bankingsystem.form.LoanStatusForm;
import com.bankingsystem.service.LoanStatusService;
import com.bankingsystem.service.impl.LoanStatusServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class LoanStatusController {

    LoanStatusService service = new LoanStatusServiceImpl();

    public boolean addStatus(LoanStatusForm form) throws SQLException {

        if ( form.getStatusName()!=null) {
            boolean isInserted = service.addStatus(form);
            return isInserted;
        }
        return false;
    }

    public boolean updateStatus(LoanStatusForm form) throws SQLException
    {
        if(!form.getStatusId().isEmpty() && form.getStatusName()!=null && !form.getStatusName().isEmpty())
        {
            boolean isUpdated =  service.updateStatus(form);
            return isUpdated;
        }
        return false;
    }

    public String getStatus(String id) throws SQLException {
        if(id!=null && !id.isEmpty())
        {
            return service.getStatus(id);
        }
        return "";
    }

    public List<LoanStatusForm> getAllStatus() throws SQLException
    {
        return service.getAllStatus();
    }

    public boolean deleteStatus(String id) throws SQLException {
        return service.deleteStatus(id);
    }

}
