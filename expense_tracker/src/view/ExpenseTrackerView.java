package view;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Transaction;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JRadioButton amountFilterRadio;
  private JRadioButton categoryFilterRadio;
  private JFormattedTextField minAmountField;
  private JFormattedTextField maxAmountField;
  private JTextField categoryFilterField;
  private JButton applyFilterBtn;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker");
    setSize(600, 400);

    String[] columnNames = {"Serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    transactionsTable = new JTable(model);

    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel);
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    JPanel filterPanel = new JPanel();
    JLabel filterLabel = new JLabel("Filter by:");
    amountFilterRadio = new JRadioButton("Amount");
    categoryFilterRadio = new JRadioButton("Category");
    minAmountField = new JFormattedTextField(format);
    minAmountField.setColumns(10);
    maxAmountField = new JFormattedTextField(format);
    maxAmountField.setColumns(10);
    categoryFilterField = new JTextField(10);
    applyFilterBtn = new JButton("Apply Filter");
    ButtonGroup filterTypeGroup = new ButtonGroup();
    filterTypeGroup.add(amountFilterRadio);
    filterTypeGroup.add(categoryFilterRadio);
    filterPanel.add(filterLabel);
    filterPanel.add(amountFilterRadio);
    filterPanel.add(minAmountField);
    filterPanel.add(new JLabel("to"));
    filterPanel.add(maxAmountField);
    filterPanel.add(categoryFilterRadio);
    filterPanel.add(categoryFilterField);
    filterPanel.add(applyFilterBtn);

    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    add(filterPanel, BorderLayout.EAST);

    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void refreshTable(List<Transaction> transactions) {
    model.setRowCount(0);
    int rowNum = 1;
    double totalCost = 0;

    for (Transaction t : transactions) {
      model.addRow(new Object[]{rowNum++, t.getAmount(), t.getCategory(), t.getTimestamp()});
      totalCost += t.getAmount();
    }

    model.addRow(new Object[]{"Total", null, null, totalCost});
    transactionsTable.updateUI();
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }

  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      return Double.parseDouble(amountField.getText());
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public void highlightTransactions(List<Transaction> transactions) {
    System.out.println(transactions);
    transactionsTable.clearSelection();
    if(this.getCategoryFilterRadio().isSelected()) {
      for (int i = 0; i < transactionsTable.getRowCount(); i++) {
        int row = transactionsTable.convertRowIndexToModel(i);
        String category = (String) model.getValueAt(row, 2);

        if (transactions.stream().anyMatch(t -> t.getCategory().equalsIgnoreCase(category))) {
          transactionsTable.addRowSelectionInterval(i, i);
        }
      }
    }

    if (this.getAmountFilterRadio().isSelected()) {
      double minAmount = getMinAmountField();
      double maxAmount = getMaxAmountField();

      for (int i = 0; i < transactionsTable.getRowCount(); i++) {
          int row = transactionsTable.convertRowIndexToModel(i);
          double amount = (double) model.getValueAt(row, 1);

          if (amount >= minAmount && amount <= maxAmount) {
              transactionsTable.addRowSelectionInterval(i, i);
          }
      }
    }
  }

  public JRadioButton getAmountFilterRadio() {
    return amountFilterRadio;
  }

  public JRadioButton getCategoryFilterRadio() {
    return categoryFilterRadio;
  }

  public double getMinAmountField() {
    if (minAmountField.getText().isEmpty()) {
      return 0;
    } else {
      return Double.parseDouble(minAmountField.getText());
    }
  }

  public double getMaxAmountField() {
    if (maxAmountField.getText().isEmpty()) {
      return Double.MAX_VALUE;
    } else {
      return Double.parseDouble(maxAmountField.getText());
    }
  }

  public String getCategoryFilterField() {
    return categoryFilterField.getText();
  }

  public JButton getApplyFilterBtn() {
    return applyFilterBtn;
  }
}
