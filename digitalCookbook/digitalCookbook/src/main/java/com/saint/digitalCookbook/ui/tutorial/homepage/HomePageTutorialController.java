package com.saint.digitalCookbook.ui.tutorial.homepage;

import com.saint.digitalCookbook.App;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Controller of a simple tutorial of home page and some basic operation
 * @author Zhouyao Yu
 */
public class HomePageTutorialController implements EventHandler<MouseEvent>{
	private HomePageTutorialView view;
	public static final int SEARCHBAR_STAGE = 1;
	public static final int CELL_STAGE = 2;
	public static final int OPERATE_STAGE = 3;
	public static final int HOMEPAGE_STAGE = 4;
	public static final int BROWSE_STAGE = 5;
	public static final int SUBSCRIBE_STAGE = 6;
	public static final int DISLIKE_STAGE = 7;
	public static final int CREATE_STAGE = 8;
	public static final int TUTORIAL_STAGE = 9;
	public static final int FINAL_STAGE = 10;
	public static final int CLOSE_STAGE = 11;

	public HomePageTutorialController(HomePageTutorialView view) {
		this.view = view;
	}

	public void handle(MouseEvent event) {
		if (event.getSource() == view.nextBtn) {
			switch (HomePageTutorialView.stage) {
			case SEARCHBAR_STAGE:{
				view.showSearchBar();
				break;
			}

			case CELL_STAGE:{
				view.showRecipeCell();
				break;
			}

			case OPERATE_STAGE:{
				view.showOperate();
				break;
			}

			case HOMEPAGE_STAGE:{
				view.showHomepage();
				break;
			}

			case BROWSE_STAGE:{
				view.showBrowse();
				break;
			}

			case SUBSCRIBE_STAGE:{
				view.showSubscribe();
				break;
			}
			case DISLIKE_STAGE:{
				view.showDislike();
				break;
			}
			case CREATE_STAGE:{
				view.showCreate();
				break;
			}

			case TUTORIAL_STAGE:{
				view.showTutorial();
				break;
			}

			case FINAL_STAGE:{
				view.showFinal();
				break;
			}

			case CLOSE_STAGE:{
				HomePageTutorialView.deActivateTutorial();
				App.loadHomePage();
				break;
			}

			default:{
				view.setup();
				break;
			}
			}
		}
	}
}
