package com.Bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bank.DTO.Transaction;
import com.Bank.connection.ConnectionFactory;

public class TransactionDAOImpl implements TransactionDAO {
    private Connection con;

    public TransactionDAOImpl() {
        this.con = ConnectionFactory.requestConnection();
    }

    @Override
    public boolean insertTransaction(Transaction t) {
        PreparedStatement ps = null;
        String query = "INSERT INTO PASSBOOK(TRAN_ID, USER_ACC, REC_ACC, TRAN_DATE, TRAN_TYPE, AMOUNT, BALANCE) VALUES(?, ?, ?, sysdate(), ?, ?, ?)";
        int res = 0;
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, t.getTransactionId());
            ps.setLong(2, t.getUser ());
            ps.setLong(3, t.getRec_acc());
            ps.setString(4, t.getTransaction());
            ps.setDouble(5, t.getAmount());
            ps.setDouble(6, t.getBalance());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res > 0;
    }

    @Override
    public List<Transaction> getTransaction(long user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM PASSBOOK WHERE USER_ACC=? ORDER BY TRAN_DATE desc";
        Transaction t = null;
        ArrayList<Transaction> passbook = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new Transaction();
                t.setTransactionId(rs.getLong(1));
                t.setUser (rs.getLong(2));
                t.setRec_acc(rs.getLong(3));
                t.setDate(rs.getDate(4));
                t.setTransaction(rs.getString(5));
                t.setAmount(rs.getDouble(6));
                t.setBalance(rs.getDouble(7));
                passbook.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passbook;
    }

    @Override
    public List<Transaction> getTransaction() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM PASSBOOK ORDER BY TRAN_DATE desc";
        Transaction t = null;
        ArrayList<Transaction> passbook = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new Transaction();
                t.setTransactionId(rs.getLong(1));
                t.setUser (rs.getLong(2));
                t.setRec_acc(rs.getLong(3));
                t.setDate(rs.getDate(4));
                t.setTransaction(rs.getString(5));
                t.setAmount(rs.getDouble(6));
                t.setBalance(rs.getDouble(7));
                passbook.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passbook;
    }
}