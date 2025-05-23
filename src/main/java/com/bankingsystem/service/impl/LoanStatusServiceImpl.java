package com.bankingsystem.service.impl;

import com.bankingsystem.dao.LoanStatusDao;
import com.bankingsystem.dao.impl.LoanStatusDaoImpl;
import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.form.LoanStatusForm;
import com.bankingsystem.service.LoanStatusService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanStatusServiceImpl implements LoanStatusService {

    LoanStatusDao loanStatusDao = new LoanStatusDaoImpl();

    @Override
    public boolean addStatus(LoanStatusForm loanStatusForm) throws SQLException {

        LoanStatusEntity entity = new LoanStatusEntity();
        entity.setStatusName(loanStatusForm.getStatusName());
        boolean status = loanStatusDao.saveStatus(entity);
        return status;
    }

    @Override
    public boolean updateStatus(LoanStatusForm loanStatusForm) throws SQLException {
        LoanStatusEntity entity = new LoanStatusEntity();
        entity.setStatusId(Integer.parseInt(loanStatusForm.getStatusId()));
        entity.setStatusName(loanStatusForm.getStatusName());
        boolean status = loanStatusDao.updateStatus(entity);
        return status;
    }

    @Override
    public String getStatus(String id) throws SQLException {
        return loanStatusDao.getStatus(Integer.parseInt(id));
    }

    @Override
    public List<LoanStatusForm> getAllStatus() throws SQLException {
        List<LoanStatusEntity> entities = loanStatusDao.getAllStatus(); // Fetch from DAO
        List<LoanStatusForm> forms = new ArrayList<>();

        for (LoanStatusEntity entity : entities) {
            LoanStatusForm form = new LoanStatusForm();
            form.setStatusId(String.valueOf(entity.getStatusId())); // Convert int to String
            form.setStatusName(entity.getStatusName());
            forms.add(form);
        }

        return forms;
    }
    @Override
    public boolean deleteStatus(String id) throws SQLException{
        return loanStatusDao.removeStatus(Integer.parseInt(id));
    }
}
