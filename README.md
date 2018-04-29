# Bank-Backend
An attempt to recreate CIBC's back-end infrastructure based on publicly available information

The Checking Accounts and Credit Accounts Banking System includes at least 3 different kinds of accounts: checking accounts, credit accounts, and demand loan accounts .
1. Checking accounts
For each checking account one of the following 3 options regarding overdraft
protection must be selected:
   - No Overdraft Protection: with this option, if a withdrawal from the checking account would cause the balance to be less than 0, then the withdrawal will be declined, and a Non-Sufficient Funds (NSF) penalty will be charged.
   - Pay Per Use Overdraft Protection.
   - Monthly Fixed Fee Overdraft Protection.
The rules for checking accounts with these 3 overdraft protection options are explained in detail in the document
https://www.cibc.com/en/personal-banking/bank-accounts/overdraft-protection.html

2. Credit accounts
For credit accounts the following rules related to the credit limit of a credit account will be enforced: 
   - High Credit Limit with Credit Limit Exceed Penalty: according to this rule, if the credit limit is greater than $1000, and a withdrawal from the credit account
would cause the sum of the balance and credit limit to be less than 0, then the withdrawal will be declined, and a Credit Exceed Limit (CLE) penalty will be charged.
   - Low Credit Limit with No Credit Limit Exceed Penalty: according to this rule, if the credit limit is less than or equal to $1000, and a withdrawal from the credit account would cause the sum of the balance and credit limit to be less than 0,
then the withdrawal will be declined, but no Credit Exceed Limit (CLE) penalty will be charged.

3. Demand loan accounts
A demand loan account corresponds to a demand loan as described in the document
https://www.cibc.com/content/dam/personal_banking/bank_accounts/pdfs/COPS_EN.pdf

===============================================================================

To run the program:
1. Create a package called "common"
2. Copy all the java files into it.
3. Use Tester class to test the system.
