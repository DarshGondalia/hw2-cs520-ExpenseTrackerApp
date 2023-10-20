import javax.swing.JOptionPane;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import model.TransactionFilter;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle "Apply Filter" button clicks
    view.getApplyFilterBtn().addActionListener(e -> {
      // Check which filter type is selected (Amount or Category)
      if (view.getAmountFilterRadio().isSelected()) {
        double minAmount = view.getMinAmountField();
        double maxAmount = view.getMaxAmountField();
        // String categoryFilter = view.getCategoryFilterField();
        TransactionFilter filter = new AmountFilter(minAmount, maxAmount);
        controller.applyFilter(filter);
      }
      if (view.getCategoryFilterRadio().isSelected()) {
        String category = view.getCategoryFilterField();
        TransactionFilter filter = new CategoryFilter(category);
        controller.applyFilter(filter);
      }
    });

  }

}