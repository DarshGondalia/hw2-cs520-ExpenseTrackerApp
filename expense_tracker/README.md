# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Initial Build
- Initial build with ant
- produced jdocs

## ExpenseTrackerModel.java
- Encapsulation and immutability of transaction list

## Transaction.java
- Encapsulation and immutability of transaction list

## Controller:
ExpenseTrackerController.java
- Added applyFilter function

## Model
TransactionFilter.java
- Created an interface for amount filter and category filter

AmountFilter.java
- implements TransactionFilter interface
- AmountFilter constructor
- Functionality for filter in filter function
    - Parameter takes List<Transaction>
    - Returns List<Transaction> after filter
CategoryFilter.java
- implements TransactionFilter interface
- Category constructor
- Functionality for filter in filter function
    - Parameter takes List<Transaction>
    - Returns List<Transaction> after filter

## View
ExpenseTrackerView.java
- Added UI elements for filter
    - JRadioButton- apply filter with two options categoryFilterRadio and amountFilterRadio
    - JFormattedTextfield- minAmountField, maxAmountField
    - JTextField- categoryFilterField
    - JFormattedTextField- amountFilterFields: minAmountFilter, maxAmountFilter
- Added functionality to the filterPanel
    - Connected to controller using applyfilter function
- Created public getter and setter methods for all variables required for filter functionality
    - getAmountFilterRadio -> JRadioButton
    - getCategoryFilterRadio -> JRadioButton
    - getMinAmountField -> double
    - getMaxAmountField -> double
    - getCategoryFilterField -> double
    - getCategoryFilterField -> String
    - getApplyFilterBtn -> JButton

- highlightTransactions function
    - Parameter: List<Transactions> object which contains filtered transactions
    - Added functionality to highlight
