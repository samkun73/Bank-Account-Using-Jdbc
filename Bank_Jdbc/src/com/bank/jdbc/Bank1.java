package com.bank.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank1 {

	public void createAccount(int accountNo, String name) {
		double initalbalance = 0.0;
		Account account = new Account();
		account.setAccountNumber(accountNo);
		account.setName(name);
		account.setBalance(initalbalance);

		String SQL = "INSERT INTO bank_details(accountNo, name,balance)VALUES(?,?,?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL)) {
			ps.setInt(1, account.getAccountNumber());
			ps.setString(2, account.getName());
			ps.setDouble(3, account.getBalance());
			int executeUpdate = ps.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Account is created..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deposit(double amount, Integer accno) {

		String SQL = "UPDATE bank_details set balance=balance+? WHERE accountno=?;";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL)) {

			ps.setDouble(1, amount);
			ps.setInt(2, accno);

			int executeUpdate = ps.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("deposit Done..");
			} else {
				System.out.println("deposit Not Done..");
			}
		} catch (Exception e) {
			System.out.println("deposit Not Done..");
			e.printStackTrace();
		}
	}

	public void withdraw(double amount, Integer accno) {

		String SQL = "UPDATE bank_details set balance=balance-? WHERE accountno=?;";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL)) {

			ps.setDouble(1, amount);
			ps.setInt(2, accno);

			int executeUpdate = ps.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("withdraw Done..");
			} else {
				System.out.println("deposit Not Done..");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void display_By_Accno(int accno) {

		String SQL = "SELECT *FROM bank_details WHERE accountno=?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL)) {
			ps.setInt(1, accno);

			ResultSet result = ps.executeQuery();

			if (result.next()) {
				int accountno = result.getInt(1);
				String name = result.getString(2);
				double balance = result.getDouble(3);

				String output = " %s - %s - %s";
				System.out.println(String.format(output, accountno, name, balance));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void display() {

		String SQL = "SELECT * FROM bank_details";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL)) {

			ResultSet result = ps.executeQuery(SQL);

			while (result.next()) {
				int accountno = result.getInt(1);
				String name = result.getString(2);
				double balance = result.getDouble(3);

				String output = " %s - %s - %s";
				System.out.println(String.format(output, accountno, name, balance));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}