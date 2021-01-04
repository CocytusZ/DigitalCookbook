package com.saint.digitalCookbook.service.recipeBrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Controller class of the recipe cell
 * @author Han Jin
 *
 */
public class RecipeCellController implements EventHandler<MouseEvent> {

	/**
	 *
	 *fields of the controller which point to view and model
	 *
	 */
	private RecipeCellView view;
	private RecipeSuscribeModel model;
	
	/**
	 * Constructor to create controller object
	 * 
	 * @param v view object of editor which is managed by this controller
	 * @param m model object of editor which is managed by this controller
	 */
	public RecipeCellController(RecipeCellView view, RecipeSuscribeModel model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * Used to handle event on the view
	 */
	public void handle(MouseEvent event) {
		if(event.getSource()==view.cellImageEnter) {
			if(event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.buttonShowUpdate();
			if(event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.buttonDisappearUpdate();
		}
		
		for (int i=0; i<10; i++) {
			try {
				if(event.getSource()==view.tag.get(i)) {		
					view.homepage.updateTag(model.searchRecipeByTag(view.tag.get(i).getText()),  model.searchDislikeRecipe(), model.searchRecipeByTag(view.tag.get(i).getText()).size());
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(event.getSource()==view.subscribe) {
			model.subscribe(view.recipeID, view.isSuscribe);
			view.subscribeButtonUpdate(model.updateSubscribeStatus(view.recipeID));

		}
		if(event.getSource()==view.dislike) {
			model.dislike(view.recipeID);
			view.dislikeButtonUpdate(model.updateDislikeStatus(view.recipeID));
		}
		
		if(event.getSource()==view.cellUnit) {
			view.recipeBrowserView = new RecipeBrowserView(view.recipeBasicInfo);
			view.recipeBrowserView.pop();
			model.addClickTimes(view.recipeBasicInfo.getRecipeId());
		}
		
	}
}
