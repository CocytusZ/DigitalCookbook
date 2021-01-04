package com.saint.digitalCookbook.service.recipeEditor;


import com.saint.digitalCookbook.constant.RecipeTagEnum;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Controller of Recipe Editor View
 * @author Zhouyao Yu
 */
public class RecipeEditorController implements EventHandler<MouseEvent>{

	private static final int LABEL_SIGN_SIZE = 1;
	/**
	 *
	 *fields of the controller which point to view and model
	 *
	 */
	private RecipeEditorModel model;
	private RecipeEditorView view;

	/**
	 * Field of index, which represented the ingredient being edited
	 */
	private int index;

	/**
	 * Constructor to create controller object
	 * 
	 * @param v view object of editor which is managed by this controller
	 * @param m model object of editor which is managed by this controller
	 */
	public RecipeEditorController(RecipeEditorModel model, RecipeEditorView view) {
		this.model = model;
		this.view = view;
	}

	public void handle(MouseEvent event) {
		// Component on Main Stage
		if (event.getSource() == view.saveRecipe) 	 { saveRecipe(); }
		if (event.getSource() == view.imageView) 	 { onPictureClicked(); }
		if (event.getSource() == view.newTagIcon) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) { onMouseEnterNewTag(); }
			if (event.getEventType() == MouseEvent.MOUSE_EXITED)  { onMouseExitNewTag(); }
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) { onMouseClickNewTag(); }
		}
		for (int i = 0; i < view.tagList.size(); i++) {
			if (event.getSource() == view.tagList.get(i)) {
				if (event.getEventType() == MouseEvent.MOUSE_ENTERED) { onMouseEnterTag(i); }
				if (event.getEventType() == MouseEvent.MOUSE_EXITED)  { onMouseExitTag(i); }
				if (event.getEventType() == MouseEvent.MOUSE_CLICKED) { onMouseClickTag(i); }
			}
		}
		for (int i = 0; i < view.editCol.size(); i++) {
			if (event.getSource() == view.editCol.get(i)) { editIngrePop(i); }
		}
		for (int i = 0; i < view.deleteCol.size(); i++) {
			if (event.getSource() == view.deleteCol.get(i)) { deleteIngredient(i); }
		}
		// Component on Ingredient Stage
		if (event.getSource() == view.addIngre)		 { addIngrePop(); }
		if (event.getSource() == view.deleteIngre)	 { deleteIngrePop(); }
		if (event.getSource() == view.addConfirm)	 { addConfirm(); }
		if (event.getSource() == view.editConfirm)	 { editConfirm(); }
		if (event.getSource() == view.deleteConfirm) { deleteAllConfirm(); }
		if (event.getSource() == view.deleteCancel)	 { deleteCancel(); }
		// Component on Tag Stage
		if (event.getSource() == view.newTagConfirm) { newTagConfirm(); }

	}

	/**
	 *will be called when 'add ingredients' button is clicked, provide another window to add ingredients 
	 */
	private void addIngrePop() {
		view.addIngrePop();
	}

	/**
	 * will be called when 'edit ingredients' button is clicked, provide another window to edit ingredients
	 */
	private void editIngrePop(int index) {
		this.index = index;
		view.editIngrePop(index);
	}

	/**
	 * will be called when 'delete ingredients' button is clicked, provide another window to delete selected ingredients
	 */
	private void deleteIngrePop() {
		view.deleteIngrePop();
	}

	/**
	 * Called when delete a single ingredient
	 * @param index
	 */
	private void deleteIngredient(int index) {
		model.deleteIngredient(index);
		view.loadIngredientTable();
	}

	/**
	 * after clicking confirm of adding ingredients, save the ingredient
	 */
	private void addConfirm() {		
		model.addIngredient(
				view.ingreNameText.getText(), 
				Float.valueOf(view.ingreAmountText.getText()), 
				view.ingreUnitBox.getValue());
		view.loadIngredientTable();
		view.addPop.close();
	}

	/**
	 * save the edited ingredient after clicking confirm button
	 */
	private void editConfirm() {
		model.changeIngredient( 
				index,
				view.ingreNameText.getText(),
				Float.valueOf(view.ingreAmountText.getText()),
				view.ingreUnitBox.getValue());
		view.loadIngredientTable();
		view.editPop.close();
	}

	/**
	 * delete the ingredients after clicking confirm button
	 */
	private void deleteAllConfirm() {
		model.clearIngredients();
		view.loadIngredientTable();
		view.deletePop.close();
	}

	/**
	 * cancel any operation after clicking confirm button
	 */
	private void deleteCancel() {
		view.deletePop.close();
	}

	/**
	 * Judge if the amount Text is numeric
	 * if so, Save recipe info
	 */
	private void saveRecipe() {
		boolean isNum = true;
		String amount = view.amountText.getText();
		for (int i = 0; i< amount.length(); i++) {
			if (!Character.isDigit(amount.charAt(i))) {
				isNum = false;
			} 
		}
		
		if(isNum && amount != null) {
			model.saveEditRecipe(
					view.nameText.getText(), 
					amount, 
					view.getTagsFromLabel(), 
					view.prepText.getText(), 
					view.cookText.getText(), 
					view.stepTArea.getText());
			view.editStage.close();
		} else {
			view.amountText.setText("Amount must be a Number");
		}
	}

	/**
	 * Called when picture is clicked
	 */
	private void onPictureClicked() {
		model.setPicture(view.editStage);
		view.loadImage();
	}

	/**
	 * Called when mouse touch tag
	 * @param index
	 */
	private void onMouseEnterTag(int index) {
		Label tag = view.tagList.get(index);
		tag.setText(tag.getText() + "×" );
		tag.setUnderline(true);
	}

	/**
	 * Called when mouse leave tag
	 * @param index
	 */
	private void onMouseExitTag(int index) {
		Label tag = view.tagList.get(index);
		tag.setText(tag.getText().substring(0, tag.getText().length() - LABEL_SIGN_SIZE));
		tag.setUnderline(false);
	}

	/**
	 * Called when tag is clicked
	 * @param index
	 */
	private void onMouseClickTag(int index) {
		Label tag = view.tagList.get(index);
		view.tagList.remove(tag);
		view.loadTags();
	}

	/**
	 * Called when mouse touch newTag
	 */
	private void onMouseEnterNewTag() {
		view.newTagIcon.setUnderline(true);
	}

	/**
	 * Called when mouse leave touch newTag
	 */
	private void onMouseExitNewTag() {
		view.newTagIcon.setUnderline(false);
	}

	/**
	 * Called when click newTag
	 */
	private void onMouseClickNewTag() {
		view.newTagOptionPop();
	}

	/**
	 * Click to save tags
	 */
	private void newTagConfirm() {
		if (!RecipeTagEnum.contains(view.newTagBox.getValue())) {
			view.newTagBox.setValue("ERROR");
		} else {
			view.tagList.add(new Label(view.newTagBox.getValue()));
			view.loadTags();
			view.newTagPop.close();
		}
	}
}
