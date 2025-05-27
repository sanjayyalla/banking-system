package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.dao.LoanPaymentDao;
import com.bankingsystem.entity.LoanPaymentEntity;
import com.bankingsystem.util.DBConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanPaymentDaoImpl implements LoanPaymentDao {
    @Override
    public String addLoanPayment(LoanPaymentEntity entity) {
        String query = "INSERT INTO Loan_Payment (loan_id, payment_date, amount_paid, principal_component, interest_component) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, entity.getLoanId());
            pstmt.setDate(2, entity.getPaymentDate());
            pstmt.setDouble(3, entity.getAmountPaid());
            pstmt.setDouble(4, entity.getPrincipalComponent());
            pstmt.setDouble(5, entity.getInterestComponent());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                return "Loan payment added successfully";
            } else {
                return "Failed to add loan payment.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLoanPaymentCount(int loanId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Loan_Payment WHERE loan_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, loanId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<LoanPaymentEntity> getAllLoanPayments() {
        List<LoanPaymentEntity> list = new ArrayList<>();
        String query = "SELECT loan_id, payment_date, amount_paid, principal_component, interest_component FROM Loan_Payment";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LoanPaymentEntity entity = new LoanPaymentEntity();
                entity.setLoanId(rs.getInt("loan_id"));
                entity.setPaymentDate(rs.getDate("payment_date"));
                entity.setAmountPaid(rs.getDouble("amount_paid"));
                entity.setPrincipalComponent(rs.getDouble("principal_component"));
                entity.setInterestComponent(rs.getDouble("interest_component"));

                list.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean saveAllLoanPaymentDetails() {
        List<LoanPaymentEntity> payments = getAllLoanPayments();
        Path path = Paths.get("LoanPaymentDetails.txt");

        try {
            List<String> lines = new ArrayList<>();
            for (LoanPaymentEntity payment : payments) {
                String line = payment.getLoanId() + "\t" +
                        payment.getPaymentDate() + "\t" +
                        payment.getAmountPaid() + "\t" +
                        payment.getPrincipalComponent() + "\t" +
                        payment.getInterestComponent();
                lines.add(line);
            }

            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}

