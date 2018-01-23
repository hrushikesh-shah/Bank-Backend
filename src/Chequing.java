/**
 * @author Hrushikesh Shah
 */
package common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chequing {

	private String SIN;
	private String customerName;
	private String cardNum;
	private double balance;
	private String overdraftOption;
	private Date anniversaryDate;
	private final double NON_SUFFICIENT_FUNDS = 5.00;
	private double overdraft_Amount = 100.00;
	private final double PAY_PER_USE_FEE = 5.00;
	private boolean account_activation_state = true;

	private static List<String> SINList = new ArrayList<String>();

	public Chequing(String sin, String custName, String cardNum, double bal, String overdraft) {
		if (SINList.contains(sin)) {
			throw new IllegalArgumentException("Customer already has an account.");
		}
		Chequing.SINList.add(sin);
		this.setSIN(sin);
		this.setCustomerName(custName);
		this.setCardNum(cardNum);
		this.depositAmount(bal);
		this.anniversaryDate = new Date();
		this.setOverdraftOption(overdraft);
	}

	/**
	 * @return the overdraftOption
	 */
	public String getOverdraftOption() {
		this.check_Account_Status();
		return overdraftOption;
	}

	/**
	 * @pre FOR ALL METHODS, we require them to not be on the "suspended" state by
	 *      calling the "checkAccountStatus" method.
	 */
	/**
	 * @param overdraftOption
	 *            the overdraftOption to set
	 * 
	 * @pre The method requires an int as the option number, so the only possible
	 *      options are 1, 2, or 3.
	 */
	public void setOverdraftOption(String overdraft) {
		this.check_Account_Status();
		if (!((overdraft.equals("1")) || (overdraft.equals("2")) || (overdraft.equals("3")))) {
			throw new IllegalArgumentException("Please input 1, 2, or 3 to get set option.");
		}
		this.overdraftOption = overdraft;
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
		return SIN;
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
	 * 
	 */
	public boolean withdrawAmount(double amount) {
		this.check_Account_Status();
		boolean success = false;
		if (this.balance > amount) {
			this.balance -= amount;
			success = true;
		} else {
			switch (this.getOverdraftOption()) {
			case "1":
				this.balance -= this.NON_SUFFICIENT_FUNDS;
				break;

			case "2":
				if (this.getBalance() + this.overdraft_Amount >= amount) {
					this.balance = this.balance - amount - this.PAY_PER_USE_FEE;
					success = true;
				}
				break;

			case "3":
				if (this.getBalance() + this.overdraft_Amount >= amount) {
					this.balance = this.balance - amount;
					success = true;
				}
				break;
			}
		}
		return success;
	}

	/**
	 * @return the overdraft_Amount
	 */
	public double getOverdraft_Amount() {
		return overdraft_Amount;
	}

	/**
	 * @param overdraft_Amount
	 *            the overdraft_Amount to set
	 * @pre We require the overdraft limit amount to be above 0
	 */

	public void setOverdraft_Amount(double amount) {
		this.check_Account_Status();
		if (amount < 0) {
			throw new IllegalArgumentException("Amount cannot be less than zero.");
		}
		this.overdraft_Amount = amount;
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
