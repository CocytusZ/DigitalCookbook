package com.saint.digitalCookbook.service.homePage;

import com.saint.digitalCookbook.App;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.service.recipeEditor.RecipeEditorView;
import com.saint.digitalCookbook.ui.tutorial.homepage.HomePageTutorialView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Controller class of the home page
 * @author Han Jin
 *
 */
public class HomePageController implements EventHandler<MouseEvent>{

	/**
	 *
	 *fields of the controller which point to view and model
	 *
	 */
	private HomePageView view;
	private RecipeSearchModel model;
	
	/**
	 * Constructor to create controller object
	 * 
	 * @param v view object of editor which is managed by this controller
	 * @param m model object of editor which is managed by this controller
	 */
	public HomePageController(HomePageView view, RecipeSearchModel model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * Used to handle event on the view
	 */
	public void handle(MouseEvent event) {
		
		if(event.getSource()==view.exitButton) 
			System.exit(0);
		if(event.getSource()==view.searchConfirm) 
			view.updateSearch(model.searchRecipeByName(view.searchBar.getText()), model.searchDislikeRecipe(), model.searchRecipeByName(view.searchBar.getText()).size());
				
		if(event.getSource()==view.homepage) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.homepage.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.homepage.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED)
				view.returnHomepage();
		}
		if(event.getSource()==view.browse) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.browse.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.browse.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED)if (event.getEventType()==MouseEvent.MOUSE_CLICKED)
				view.updateBrowse(model.searchRecipeByName(""),  model.searchDislikeRecipe(), model.searchRecipeByName("").size());
		}
		if(event.getSource()==view.subscribe) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.subscribe.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.subscribe.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED)
					view.updateSubscribe(model.searchSubscribeRecipe(),  model.searchDislikeRecipe(), model.searchSubscribeRecipe().size());
		}
		if(event.getSource()==view.dislike) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.dislike.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.dislike.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED)
				view.updateDislike(model.searchDislikeRecipe(), model.searchDislikeRecipe().size());
		}
		if(event.getSource()==view.createMyRecipe) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.createMyRecipe.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.createMyRecipe.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED) {
				new RecipeEditorView(Recipe.create().getRecipeBasicInfo()).pop();;
			}
		}
		if(event.getSource()==view.tutorial) {
			if (event.getEventType()==MouseEvent.MOUSE_ENTERED)
				view.tutorial.setStyle("-fx-underline: true; -fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_EXITED)
				view.tutorial.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			if (event.getEventType()==MouseEvent.MOUSE_CLICKED) {
				HomePageTutorialView.activateTutorial();
				App.loadHomePage();
			}
		}
	}
}
