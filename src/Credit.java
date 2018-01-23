/**
 * @author Hrushikesh Shah
 */

package common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Credit {

	private String customerName;
	private String cardNum;
	private double limit;
	private double balance = 0.00;
	private Date anniversaryDate;
	private boolean account_activation_state = true;
	private String SIN;
	private final double CREDIT_EXCEED_LIMIT = 29.00;

	private static List<String> SINList = new ArrayList<String>();

	public Credit(String sin, String custName, String cardNum, double limit) {
		if (SINList.contains(sin)) {
			throw new IllegalArgumentException("Customer already has an account.");
		}
		Credit.SINList.add(sin);
		this.setSIN(sin);
		this.setCustomerName(custName);
		this.setCardNum(cardNum);
		this.setLimit(limit);
		this.anniversaryDate = new Date();
	}

	/**
	 * @pre FOR ALL METHODS, we require them to not be on the "suspended" state by
	 *      calling the "checkAccountStatus" method.
	 */
	/**
	 * @return the limit
	 */
	public double getLimit() {
		this.check_Account_Status();
		return this.limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(double limit) {
		this.check_Account_Status();
		if (this.getLimit() <= 0) {
			throw new IllegalArgumentException("Credit limit cannot be less than 0");
		}
		this.limit = limit;
	}

	/**
	 * @return the anniversaryDate
	 */
	public Date getAnniversaryDate() {
		this.check_Account_Status();
		return anniversaryDate;
	}

	/**
	 * @return the SIN
	 */
	public String getSIN() {
		this.check_Account_Status();
		return this.SIN;
	}

	/**
	 * @param set
	 *            the SIN
	 * @pre We require the length of SIN to be 9 digits and ensure that those digits
	 *      only include numbers.
	 */
	public void setSIN(String sin) {
		this.check_Account_Status();
		if (sin.length() != 9 || sin.matches(".*[a-zA-Z].*")) {
			throw new IllegalArgumentException("Invalid SIN");
		}
		this.SIN = sin;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		this.check_Account_Status();
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 * @pre We require the string to not be empty or contain any numbers.
	 */
	public void setCustomerName(String customerName) {
		this.check_Account_Status();
		if (customerName.isEmpty() || customerName.matches(".*\\d.*")) {
			throw new IllegalArgumentException("Please input a name.");
		}
		this.customerName = customerName;
	}

	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		this.check_Account_Status();
		return cardNum;
	}

	/**
	 * @param cardNum
	 *            the cardNum to set
	 * @pre We require the string to be 16 digits long and ensure that the string
	 *      only consists of numbers.
	 */
	public void setCardNum(String cardNum) {
		this.check_Account_Status();
		if (cardNum.length() != 16 || cardNum.matches(".*[a-zA-Z].*")) {
			throw new IllegalArgumentException("Enter a valid card number.");
		}
		this.cardNum = cardNum;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		this.check_Account_Status();
		return balance;
	}

	/**
	 * @param amount
	 *            the amount to deposit
	 * @pre We require the amount of deposit to be above 0.
	 */
	public void depositAmount(double amount) {
		this.check_Account_Status();
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit cannot be 0 or less.");
		}
		this.balance += amount;
	}

	/**
	 * @param amount
	 *            the amount to withdraw
	 * @pre the amount is less than limit + balance
	 */
	public boolean withdrawAmount(double amount) {
		this.check_Account_Status();
		boolean success = false;
		if (this.getBalance() + this.getLimit() >= amount) {
			this.balance -= amount;
			success = true;
		} else {
			if (this.getLimit() > 1000) {
				this.balance -= this.CREDIT_EXCEED_LIMIT;
			}
		}
		return success;
	}

	/**
	 * @throws exception
	 *             if the account is not active
	 */
	private void check_Account_Status() {
		if (!this.account_activation_state) {
			throw new IllegalArgumentException("This account has been suspended");
		}
	}

	/**
	 * suspends the account
	 */
	public void suspendAccount() {
		if (!this.account_activation_state) {
			throw new IllegalArgumentException("Account is already inactive");
		}
		this.account_activation_state = false;
	}

	/**
	 * reactivates the account
	 */
	public void reactivateAccount() {
		if (!this.account_activation_state) {
			throw new IllegalArgumentException("Account is already active.");
		}
		this.account_activation_state = true;
	}

}