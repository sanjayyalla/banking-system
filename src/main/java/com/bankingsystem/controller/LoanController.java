package com.bankingsystem.controller;

import com.bankingsystem.form.LoanForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.service.LoanService;
import com.bankingsystem.service.impl.LoanServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;

public class LoanController {
    LoanService service = new LoanServiceImpl();
    public String addLoan(LoanForm form) throws SQLException, ParseException {
        return  service.addLoan(form);
    }
    public boolean updateLoan(LoanForm form){
        return service.updateLoan(form);
    }
    public boolean deleteLoan(String loanId)
    {
        if(loanId!=null && !loanId.isEmpty())
        {
            return service.deleteLoan(loanId);
        }
        return false;
    }
    public LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception {
        return service.getLoanDetailById(request);
    }
}
