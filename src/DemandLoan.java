/**
 * @author Hrushikesh Shah
 */

package common;

import java.util.ArrayList;
import java.util.List;

public class DemandLoan {

	private String SIN;
	private String customerName;
	private double balance;

	private static List<String> SINList = new ArrayList<String>();

	public DemandLoan(String sin, String custName, double bal) {
		if (SINList.contains(sin)) {
			throw new IllegalArgumentException("Customer already has an account.");
		}
		DemandLoan.SINList.add(sin);
		this.setSIN(sin);
		this.setCustomerName(custName);
		this.balance = bal;
	}

	/**
	 * @return the SIN
	 */
	public String getSIN() {

		return SIN;
	}

	/**
	 * @param set
	 *            the SIN
	 */
	public void setSIN(String sin) {

		if (sin.length() != 9 || sin.matches(".*[a-zA-Z].*")) {
			throw new IllegalArgumentException("Invalid SIN");
		}
		this.SIN = sin;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {

		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {

		if (customerName.isEmpty() || customerName.matches(".*\\d.*")) {
			throw new IllegalArgumentException("Please input a name.");
		}
		this.customerName = customerName;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/*
	 * @param amount the amount to deposit
	 */
	public void depositAmount(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit cannot be 0 or less.");
		}
		this.balance += amount;
	}

	/*
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		if (amount > 0) {
			throw new IllegalArgumentException("Loans cannot have a positive balance.");
		}
		this.balance = amount;
	}
}
