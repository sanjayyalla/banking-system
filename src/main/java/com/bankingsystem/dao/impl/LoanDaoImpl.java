package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;
import com.bankingsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDaoImpl implements LoanDao {

    @Override
    public String addLoan(LoanEntity entity) throws SQLException {

            String query = "Insert into Loan(customer_id,loan_type_id,branch_id,status_id,principal_amount,interest_rate) VALUES (?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, entity.getCustomerId());
            statement.setInt(2,entity.getLoanTypeId());
            statement.setInt(3,entity.getBranchId());
            statement.setInt(4,entity.getStatusId());
            statement.setDouble(5,entity.getPrincipalAmount());
            statement.setDouble(6,entity.getInterestRate());

            int isRowInserted = statement.executeUpdate();
            if(isRowInserted==1)
            {
                return "Inserted";
            }
            return "Not inserted";
    }

    @Override
    public boolean updateLoan(LoanEntity entity) {
        String query = "UPDATE Loan SET loan_type_id = ?, branch_id = ?, status_id = ?, principal_amount = ?,interest_rate = ? WHERE loan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
//            System.out.println("I am here");
            statement.setInt(1, entity.getLoanTypeId());
            statement.setInt(2, entity.getBranchId());
            statement.setInt(3, entity.getStatusId());
            statement.setDouble(4, entity.getPrincipalAmount());
            statement.setDouble(5, entity.getInterestRate());
            statement.setInt(6,entity.getLoanId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;

        } catch (SQLException e) {
            e.printStackTrace();
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
}
