package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.database.DbUtil;
import com.capgemini.bankapp.repository.BankAccountRepository;

@Repository

public class BankAccountRepositoryImpl implements BankAccountRepository {

/*	private HashSet<BankAccount> bankAccounts;
*/	
	/*public BankAccountRepositoryImpl() {
		super();
		
		bankAccounts=new HashSet<>();
		bankAccounts.add(new BankAccount(1234, "John Doe", "SAVING", 34000));
		bankAccounts.add(new BankAccount(5678, "Keerthana", "CURRENT", 56000));
		bankAccounts.add(new BankAccount(9849, "George", "SAVING", 92000));
	}*/
	
	@Autowired
	DbUtil dbUtil;
	
	@Override
	public double getBalance(long accountId) {
		String query = "SELECT balance FROM bankaccount WHERE accountId = ?";
		try (Connection connection = dbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
	
			statement.setLong(1, accountId);
			try(ResultSet result = statement.executeQuery()){
			if (result.next()) {
				return result.getDouble(1);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountId;
	}

		

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		String query = "UPDATE bankaccount SET balance = ? WHERE accountId = ?";
		try (Connection connection = dbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			if(statement.executeUpdate() != 0) {
				System.out.println("Record inserted successfully");
			return true;}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	


}