package com.bankingsystem.service.impl;

import com.bankingsystem.dao.LoanTypeDao;
import com.bankingsystem.dao.impl.LoanTypeDaoImpl;
import com.bankingsystem.entity.LoanTypeEntity;
import com.bankingsystem.form.LoanTypeForm;
import com.bankingsystem.service.LoanTypeService;

import java.sql.SQLException;
import java.util.List;

public class LoanTypeServiceImpl implements LoanTypeService {
    LoanTypeDao dao = new LoanTypeDaoImpl();
    @Override
    public boolean addLoanType(LoanTypeForm form) throws SQLException {
        LoanTypeEntity entity = new LoanTypeEntity();
        entity.setTypeName(form.getTypeName());
        entity.setInterestRate(Double.parseDouble(form.getInterestRate()));
        return dao.addLoanType(entity);
    }

    @Override
    public boolean updateLoanType(LoanTypeForm form) throws SQLException {
        LoanTypeEntity entity = new LoanTypeEntity();
        entity.setLoanTypeID(Integer.parseInt(form.getLoanTypeID()));
        entity.setTypeName(form.getTypeName());
        entity.setInterestRate(Double.parseDouble(form.getInterestRate()));
        return dao.updateLoanType(entity);
    }

    @Override
    public String getLoanType(String loanId) {
        return dao.getLoanType(Integer.parseInt(loanId));
    }

    @Override
    public List<LoanTypeEntity> getAllLoneTypes() throws SQLException {
        return dao.getAllLoneTypes();
    }
    @Override
    public boolean deleteLoanType(String loanId)
    {
        return dao.removeLoanType(Integer.parseInt(loanId));
    }
}
