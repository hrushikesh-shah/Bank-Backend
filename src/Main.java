/**
 * @author Hrushikesh Shah
 */

package common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import common.Chequing;
import common.Credit;

public final class Main {

	private static Map<String, Chequing> mapChequing = new HashMap<String, Chequing>();
	private static Map<String, Credit> mapCredit = new HashMap<String, Credit>();
	private static Map<String, DemandLoan> mapDemandLoan = new HashMap<String, DemandLoan>();

	private Main() {
	}

	/**
	 * @throws error
	 *             if invalid input
	 */
	private static void checkType(String type) {
		if (!(type.equals("chequing") || type.equals("credit") || type.equals("demand loan"))) {
			throw new IllegalArgumentException("Please input: chequing or credit.");
		}
	}

	/**
	 * create a chequing account
	 */
	public static void createChequingAccount(String sin, String custName, String cardNum, double bal,
			String overdraft) {
		Chequing chequing = new Chequing(sin, custName, cardNum, bal, overdraft);
		mapChequing.put(sin, chequing);
	}

	/**
	 * create a credit account
	 */
	public static void createCreditAccount(String sin, String custName, String cardNum, double limit) {
		Credit credit = new Credit(sin, custName, cardNum, limit);
		mapCredit.put(sin, credit);
	}

	/**
	 * check if the sin number is valid
	 */
	public static String checkClient(String sin) {
		if ((mapChequing.get(sin) != null) && (mapCredit.get(sin) != null)) {
			return "both";
		} else if ((mapChequing.get(sin) != null)) {
			return "chq";
		} else if ((mapCredit.get(sin) != null)) {
			return "crd";
		} else if ((mapDemandLoan.get(sin) != null)) {
			return "dl";
		}
		throw new IllegalArgumentException("No such client exists");
	}

	/**
	 * @return the customerName
	 * @pre Valid SIN number
	 */

	public static String getCustomerName(String sin) {
		if (checkClient(sin).equals("chq")) {
			return mapChequing.get(sin).getCustomerName();
		} else if (checkClient(sin).equals("cre")) {
			return mapCredit.get(sin).getCustomerName();
		} else {
			return mapDemandLoan.get(sin).getCustomerName();
		}
	}

	/**
	 * @return the overdraft_Amount
	 * @pre Valid SIN number
	 */
	public String getOverdraftOption(String sin) {
		checkClient(sin);
		return mapChequing.get(sin).getOverdraftOption();
	}

	/**
	 * @param overdraftOption
	 *            the overdraftOption to set
	 * @pre Valid SIN number
	 */
	public void setOverdraftOption(String sin, String overdraft) {
		checkClient(sin);
		mapChequing.get(sin).setOverdraftOption(overdraft);
	}

	/**
	 * @return the anniversaryDate
	 * @pre Valid SIN number
	 */
	public Date getAnniversaryDate(String type, String sin) {
		checkClient(sin);
		checkType(type);
		if (type.equals("chequing")) {
			return mapChequing.get(sin).getAnniversaryDate();
		} else {
			return mapCredit.get(sin).getAnniversaryDate();
		}
	}

	/**
	 * @return the SIN
	 * @pre Valid SIN number
	 */
	public String getSIN(String sin) {
		if (checkClient(sin).equals("chq")) {
			return mapChequing.get(sin).getSIN();
		} else if (checkClient(sin).equals("cre")) {
			return mapCredit.get(sin).getSIN();
		} else {
			return mapDemandLoan.get(sin).getSIN();
		}
	}

	/**
	 * @param sin
	 *            the SIN to find
	 * @param setSIN
	 *            SIN to set
	 * @pre Valid SIN number
	 */
	public void setSIN(String sin, String setSIN) {
		if (checkClient(sin).equals("both")) {
			mapChequing.get(sin).setSIN(setSIN);
			mapCredit.get(sin).setSIN(setSIN);
		} else if (checkClient(sin).equals("chq")) {
			mapChequing.get(sin).setSIN(setSIN);
		} else if (checkClient(sin).equals("crd")) {
			mapCredit.get(sin).setSIN(setSIN);
		} else {
			mapDemandLoan.get(sin).setSIN(setSIN);
		}
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 * @pre Valid SIN number
	 */
	public void setCustomerName(String sin, String customerName) {
		if (checkClient(sin).equals("both")) {
			mapChequing.get(sin).setCustomerName(customerName);
			mapCredit.get(sin).setCustomerName(customerName);
		} else if (checkClient(sin).equals("chq")) {
			mapChequing.get(sin).setCustomerName(customerName);
		} else if (checkClient(sin).equals("crd")) {
			mapCredit.get(sin).setCustomerName(customerName);
		} else {
			mapDemandLoan.get(sin).setCustomerName(customerName);
		}
	}

	/**
	 * @return the cardNum
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public String getCardNum(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			return mapChequing.get(sin).getCardNum();
		} else {
			return mapCredit.get(sin).getCardNum();
		}
	}

	/**
	 * @param cardNum
	 *            the cardNum to set
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void setCardNum(String type, String sin, String cardNum) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).setCardNum(cardNum);
		}
		if (type.equals("credit")) {
			mapCredit.get(sin).setCardNum(cardNum);
		}
	}

	/**
	 * @return the balance
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public double getBalance(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			return mapChequing.get(sin).getBalance();
		} else if (type.equals("credit")) {
			return mapCredit.get(sin).getBalance();
		} else {
			return mapDemandLoan.get(sin).getBalance();
		}
	}

	/**
	 * @param amount
	 *            the amount to deposit
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void depositAmount(String type, String sin, double amount) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).depositAmount(amount);
		} else if (type.equals("credit")) {
			mapCredit.get(sin).depositAmount(amount);
		} else {
			mapDemandLoan.get(sin).depositAmount(amount);
		}
	}

	/**
	 * @param amount
	 *            the amount to withdraw
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void withdrawAmount(String type, String sin, double amount) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).withdrawAmount(amount);
		} else {
			mapCredit.get(sin).withdrawAmount(amount);
		}
	}

	/**
	 * @param amount
	 *            the Demand Loan amount
	 * @pre Valid SIN number
	 */
	public void setDemandLoanAmount(String sin, double amount) {
		if (mapDemandLoan.get(sin) == null) {
			throw new IllegalArgumentException("Not a valid account.");
		}
		mapDemandLoan.get(sin).setAmount(amount);
	}

	/**
	 * @return the overdraft_Amount
	 * @pre Valid SIN number
	 * @pre Valid type
	 */

	public double getLimit(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			return mapChequing.get(sin).getOverdraft_Amount();
		} else {
			return mapCredit.get(sin).getLimit();
		}
	}

	/**
	 * @param limit
	 *            the limit to set
	 * @pre Valid SIN number
	 * @pre Valid type
	 */

	public void setLimit(String type, String sin, double limit) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).setOverdraft_Amount(limit);
		} else {
			mapCredit.get(sin).setLimit(limit);
		}
	}

	/**
	 * suspends the account
	 * 
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void suspendAccount(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).suspendAccount();
		} else {
			mapCredit.get(sin).suspendAccount();
		}
	}

	/**
	 * reactivates the account
	 * 
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void reactivateAccount(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.get(sin).reactivateAccount();
		} else {
			mapCredit.get(sin).reactivateAccount();
		}
	}

	/**
	 * cancels the account
	 * 
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void cancelAccount(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("chequing")) {
			mapChequing.remove(sin);
		} else if (type.equals("credit")) {
			mapCredit.remove(sin);
		} else {
			mapDemandLoan.remove(sin);
		}
	}

	/**
	 * terminates the account
	 * 
	 * @pre Valid SIN number
	 * @pre Valid type
	 */
	public void terminateAccount(String type, String sin) {
		checkType(type);
		checkClient(sin);
		if (type.equals("demand loan")) {
			throw new IllegalArgumentException("Please cancel the account, not terminate.");
		} else if (type.equals("chequing")) {
			DemandLoan demand = new DemandLoan(sin, mapChequing.get(sin).getCustomerName(),
					mapChequing.get(sin).getBalance());
			mapDemandLoan.put(sin, demand);
		} else {
			DemandLoan demand = new DemandLoan(sin, mapCredit.get(sin).getCustomerName(),
					mapCredit.get(sin).getBalance());
			mapDemandLoan.put(sin, demand);
		}
		cancelAccount(type, sin);
	}

	/**
	 * Transfers money from one account to another account
	 * 
	 * @pre Valid SIN number
	 * @pre Valid type
	 * @pre Enough balance in the from account
	 * @pre From and to not be same
	 * @pre From not be a demand loan account
	 */
	public void transferAmount(String from, String to, String sin, double amount) {
		checkType(from);
		checkType(to);
		if (from.equals(to)) {
			throw new IllegalArgumentException("You cannot transfer amount to the same account");
		}
		if (from.equals("demand loan")) {
			throw new IllegalArgumentException("You cannot transfer amount from a loan account");
		}
		checkClient(sin);
		if (amount <= 0) {
			throw new IllegalArgumentException("Invalid amount");
		}

		boolean success = false;
		switch (from) {
		case "chequing":
			if (mapChequing.get(sin).withdrawAmount(amount)) {
				success = true;
			}
			break;

		case "credit":
			if (mapCredit.get(sin).withdrawAmount(amount)) {
				success = true;
			}
			break;
		}

		switch (to) {
		case "chequing":
			if (success) {
				mapChequing.get(sin).depositAmount(amount);
			}
			break;
		case "credit":
			if (success) {
				mapCredit.get(sin).depositAmount(amount);
			}
			break;
		case "demand loan":
			if (success) {
				mapDemandLoan.get(sin).depositAmount(amount);
			}
			break;
		}
	}
}
