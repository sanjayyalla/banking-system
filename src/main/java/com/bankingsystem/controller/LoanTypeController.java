package com.bankingsystem.controller;
import com.bankingsystem.form.LoanTypeForm;
import com.bankingsystem.service.LoanTypeService;
import com.bankingsystem.service.impl.LoanTypeServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class LoanTypeController {

    LoanTypeService service = new LoanTypeServiceImpl();

    public boolean addLoanType(LoanTypeForm form) throws SQLException {
        return service.addLoanType(form);
    }
    public boolean updateLoanType(LoanTypeForm form) throws SQLException {
        return service.updateLoanType(form);
    }

    public String getLoanType(String loanId)
    {
        return service.getLoanType(loanId);
    }

    public List<LoanTypeForm> getAllLoneTypes() throws SQLException {
        return service.getAllLoneTypes();
    }

    public boolean deleteLoanType(String loanId)
    {
        return service.deleteLoanType(loanId);
    }
}