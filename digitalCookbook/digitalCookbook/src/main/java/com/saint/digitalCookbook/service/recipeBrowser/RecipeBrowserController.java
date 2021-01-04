package com.saint.digitalCookbook.service.recipeBrowser;

import java.util.List;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.service.recipeEditor.RecipeEditorView;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Controller class of the recipe browser
 * @author Zhouyao Yu
 *
 */
public class RecipeBrowserController implements EventHandler<Event>{
	
	/**
	 *
	 *fields of the controller which point to view and model
	 *
	 */
	private RecipeBrowserModel model;
	private RecipeBrowserView view;
	
	/**
	 * Constructor to create controller object
	 * 
	 * @param v view object of editor which is managed by this controller
	 * @param m model object of editor which is managed by this controller
	 */
	public RecipeBrowserController(RecipeBrowserModel m, RecipeBrowserView v) {
		this.model = m;
		this.view = v;
	}

	/**
	 * Used to handle event on the view
	 */
	public void handle(Event event) {
		if (event.getSource() == view.amountInc)  	 { addAmount(); }
		if (event.getSource() == view.amountDec)	 { reduceAmount(); }
		if (event.getSource() == view.editBtn) 	 	 { editPagePop(); } 
		if (event.getSource() == view.deleteBtn) 	 { deletePop(); }
		if (event.getSource() == view.amountCalc)	 { calcAmount(); }
		if (event.getSource() == view.deleteConfirm) { confirmDelete(); }
		if (event.getSource() == view.deleteCancel)  { cancelDelete(); }
	}
	


	/**
	 * add 1 to the amount on clicking '+' button
	 */
	private void addAmount() {
		String str = view.amountText.getText();
		int amount = Integer.parseInt(str);
		view.amountText.setText(String.valueOf(++amount));
	}

	/**
	 * minus 1 to the amount on clicking '+' button
	 */
	private void reduceAmount() {
		String str = view.amountText.getText();
		int amount = Integer.parseInt(str);
		view.amountText.setText(String.valueOf(++amount));
	}

	/**
	 * Calculate the amount of ingredients according the serve amount given by user 
	 * and then display it on the ingredient table
	 * 
	 */
	private void calcAmount() {
		float amount = Float.valueOf(view.amountText.getText());
		List<Ingredient> ingredientList = model.getIngredients();
		for (Ingredient in : ingredientList) {
			in.setAmount(amount * in.getAmount());
		}
		
		view.loadIngredientTable(ingredientList);
	}

	/**
	 * open the edit page
	 */
	private void editPagePop() {
		new RecipeEditorView(model.getRecipe().getRecipeBasicInfo()).pop();
		view.browserStage.close();
	}
	
	/**
	 * will be called when 'delete' button is clicked, provide another window to delete selected ingredients
	 */
	private void deletePop() {
		view.deletePop();
	}	
	
	/**
	 * Called when the "confirm" clicked on DeletePopWindow
	 */
	private void confirmDelete() {
		model.deleteRecipe();
		view.deletePopStage.close();
		view.browserStage.close();
	}
	
	/**
	 * Called when the "cancel" clicked on DeletePopWindow
	 */
	private void cancelDelete() {
		view.deletePopStage.close();
	}
}
