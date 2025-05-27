package com.bankingsystem.service.impl;

import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.dao.LoanPaymentDao;
import com.bankingsystem.dao.impl.LoanDaoImpl;
import com.bankingsystem.dao.impl.LoanPaymentDaoImpl;
import com.bankingsystem.entity.LoanPaymentEntity;
import com.bankingsystem.form.EMI;
import com.bankingsystem.form.LoanPaymentForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.service.LoanPaymentService;
import com.bankingsystem.util.EmiCalculatorUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class LoanPaymentServiceImpl implements LoanPaymentService {
    LoanPaymentDao dao = new LoanPaymentDaoImpl();
    @Override
    public String addLoanPayment(int loanId) throws Exception {

        LoanDao loanDao = new LoanDaoImpl();
        LoanPaymentDao loanPaymentDao = new LoanPaymentDaoImpl();
        LoanRequestForm requestForm = new LoanRequestForm();
        requestForm.setLoanId(String.valueOf(loanId));
        LoanResponseForm responseForm = loanDao.getLoanDetailById(requestForm);
        double interestRate = Double.parseDouble(responseForm.getInterestRate());
        double principalAmount = Double.parseDouble(responseForm.getPrincipalAmount());
        int termMonths = Integer.parseInt(responseForm.getTermMonths());
        EmiCalculatorUtil emiCalculatorUtil = new EmiCalculatorUtil();
        List<EMI> emiList = emiCalculatorUtil.calculateEmi(principalAmount,interestRate,termMonths);
        int index = loanPaymentDao.getLoanPaymentCount(loanId);
        if (index >= emiList.size()) {
            return "All EMIs already paid!";
        }
        EMI emi = emiList.get(index);
        LoanPaymentEntity entity = new LoanPaymentEntity();
        entity.setLoanId(loanId);
        entity.setAmountPaid(emi.getInterest() + emi.getPrincipalAmount());
        entity.setPrincipalComponent(emi.getPrincipalAmount());
        entity.setInterestComponent(emi.getInterest());
        Date today = new Date(System.currentTimeMillis());
        entity.setPaymentDate(today);

        String returnedResponse = loanPaymentDao.addLoanPayment(entity);
        return returnedResponse;
    }

    public boolean saveAllLoanPaymentDetails()
    {
        return dao.saveAllLoanPaymentDetails();
    }

    public List<LoanPaymentForm> getAllLoanPayments() {
        LoanPaymentDao dao = new LoanPaymentDaoImpl();
        List<LoanPaymentEntity> entities = dao.getAllLoanPayments();
        List<LoanPaymentForm> forms = new ArrayList<>();

        for (LoanPaymentEntity entity : entities) {
            LoanPaymentForm form = new LoanPaymentForm();
            form.setLoanId(String.valueOf(entity.getLoanId()));
            form.setPaymentDate(entity.getPaymentDate().toString());
            form.setAmountPaid(String.valueOf(entity.getAmountPaid()));
            form.setPricipalComponent(String.valueOf(entity.getPrincipalComponent()));
            form.setInterestComponent(String.valueOf(entity.getInterestComponent()));
            forms.add(form);
        }

        return forms;
    }
}
