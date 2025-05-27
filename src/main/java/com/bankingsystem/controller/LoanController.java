package com.bankingsystem.controller;

import com.bankingsystem.form.LoanForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.service.LoanService;
import com.bankingsystem.service.impl.LoanServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class LoanController {
    LoanService service = new LoanServiceImpl();

    public String addLoan(LoanForm form) throws SQLException, ParseException {
        if (form.getLoanTypeId() != null && form.getBranchId() != null && form.getStatusId() != null && form.getPricipalAmount() != null) {
            return service.addLoan(form);
        }
        return null;
    }

    public boolean updateLoan(LoanForm form) {
        return service.updateLoan(form);
    }

    public boolean deleteLoan(String loanId) {
        if (loanId != null && !loanId.isEmpty()) {
            return service.deleteLoan(loanId);
        }
        return false;
    }

    public LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception {
        return service.getLoanDetailById(request);
    }

    public List<LoanResponseForm> getAllLoanDetails() {
        return service.getAllLoanDetails();
    }
}
