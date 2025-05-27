package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDaoImpl implements LoanDao {

    @Override
    public String addLoan(LoanEntity entity) throws SQLException {

            String query = "Insert into Loan(customer_id,loan_type_id,branch_id,status_id,principal_amount,interest_rate,term_months) VALUES (?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, entity.getCustomerId());
            statement.setInt(2,entity.getLoanTypeId());
            statement.setInt(3,entity.getBranchId());
            statement.setInt(4,entity.getStatusId());
            statement.setDouble(5,entity.getPrincipalAmount());
            statement.setDouble(6,entity.getInterestRate());
            statement.setInt(7,entity.getTermMonths());
            int isRowInserted = statement.executeUpdate();
            if(isRowInserted==1)
            {
                return "Inserted";
            }
            return "Not inserted";
    }

    @Override
    public boolean updateLoan(LoanEntity entity) throws SQLException {
        Connection conn = DBConnection.getConnection();
        int customerID = entity.getCustomerId();
        int loanTypeID = entity.getLoanTypeId();
        int branchID = entity.getBranchId();
        int statusID = entity.getStatusId();
        double interestRate = entity.getInterestRate();
        double principalAmount = entity.getPrincipalAmount();
        int termMonths = entity.getTermMonths();
        Date startDate = entity.getStartDate();
        Date endDate = entity.getEndDate();

        StringBuilder query = new StringBuilder("update loan set ");
        if (customerID != 0) {
            query.append(String.format("customer_id=%d,", customerID));
        }
        if (loanTypeID != 0) {
            query.append(String.format("loan_type_id=%d,", loanTypeID));
        }
        if (branchID != 0) {
            query.append(String.format("branch_id=%d,", branchID));
        }
        if (statusID != 0) {
            query.append(String.format("status_id=%d,", statusID));
        }
        if (principalAmount != 0.0d) {
            query.append(String.format("principal_amount=%.2f,", principalAmount));
        }
        if (interestRate != 0.0d) {
            query.append(String.format("interest_rate=%.2f,", interestRate));
        }
        if (principalAmount != 0.0d) {
            query.append(String.format("principal_amount=%.2f,", principalAmount));
        }
        if (termMonths != 0) {
            query.append(String.format("term_months=%d,", termMonths));

        }
        if (startDate != null) {
            query.append(String.format("start_date='%s',", new java.sql.Date(startDate.getTime())));
        }
        if (endDate != null) {
            query.append(String.format("end_date='%s',", new java.sql.Date(endDate.getTime())));
        }

        query.setLength(query.length() - 1);
        query.append(String.format(" where loan_id=%d;", entity.getLoanId()));
        try {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(query.toString());
            return result != 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean removeLoan(int loanId)
    {
        String query = "DELETE FROM Loan WHERE loan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, loanId);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
            return false;
        }
    }

    @Override
    public LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception {
        String sql = "SELECT l.loan_id, c.name as customer_name, lt.type_name as loan_type_name, " +
                "b.name as branch_name, s.status_name, l.principal_amount, l.interest_rate, " +
                "l.term_months, l.start_date, l.end_date " +
                "FROM Loan l " +
                "JOIN Customer c ON l.customer_id = c.cust_id " +
                "JOIN Loan_Type lt ON l.loan_type_id = lt.loan_type_id " +
                "JOIN Branch b ON l.branch_id = b.branch_id " +
                "JOIN Loan_Status s ON l.status_id = s.status_id " +
                "WHERE l.loan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(request.getLoanId()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LoanResponseForm resp = new LoanResponseForm();
                resp.setLoanId(String.valueOf(rs.getInt("loan_id")));
                resp.setCustomerName(rs.getString("customer_name"));
                resp.setLoanTypeName(rs.getString("loan_type_name"));
                resp.setBranchName(rs.getString("branch_name"));
                resp.setStatusName(rs.getString("status_name"));
                resp.setPrincipalAmount(rs.getString("principal_amount"));
                resp.setInterestRate(rs.getString("interest_rate"));
                resp.setTermMonths(rs.getString("term_months"));
                resp.setStartDate(String.valueOf(rs.getDate("start_date")));
                resp.setEndDate(String.valueOf(rs.getDate("end_date")));
                return resp;
            }
        }
        return null;
    }

    @Override
    public List<LoanResponseForm> getAllLoanDetails() {
        List<LoanResponseForm> loanList = new ArrayList<>();

        String sql = "SELECT l.loan_id, c.name as customer_name, lt.type_name as loan_type_name, " +
                "b.name as branch_name, s.status_name, l.principal_amount, l.interest_rate, " +
                "l.term_months, l.start_date, l.end_date " +
                "FROM Loan l " +
                "JOIN Customer c ON l.customer_id = c.cust_id " +
                "JOIN Loan_Type lt ON l.loan_type_id = lt.loan_type_id " +
                "JOIN Branch b ON l.branch_id = b.branch_id " +
                "JOIN Loan_Status s ON l.status_id = s.status_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoanResponseForm resp = new LoanResponseForm();
                resp.setLoanId(String.valueOf(rs.getInt("loan_id")));
                resp.setCustomerName(rs.getString("customer_name"));
                resp.setLoanTypeName(rs.getString("loan_type_name"));
                resp.setBranchName(rs.getString("branch_name"));
                resp.setStatusName(rs.getString("status_name"));
                resp.setPrincipalAmount(rs.getString("principal_amount"));
                resp.setInterestRate(rs.getString("interest_rate"));
                resp.setTermMonths(rs.getString("term_months"));
                resp.setStartDate(String.valueOf(rs.getDate("start_date")));
                resp.setEndDate(String.valueOf(rs.getDate("end_date")));

                loanList.add(resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loanList;
    }

    public boolean processLoan(int id)
    {
        return false;
    }

}
