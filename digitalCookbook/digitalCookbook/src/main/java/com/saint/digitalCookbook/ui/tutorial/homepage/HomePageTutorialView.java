package com.saint.digitalCookbook.ui.tutorial.homepage;

import com.saint.digitalCookbook.service.homePage.HomePageView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *  View of a simple tutorial of home page and some basic operation
 * @author Zhouyao Yu
 */
public class HomePageTutorialView extends StackPane{
	static int stage = 0;
	public static boolean isTutorial;
	
	private HomePageTutorialController controller = new HomePageTutorialController(this);
	
	private static final Rectangle BG_MASK = new Rectangle(HomePageView.WINDOW_WIDTH, HomePageView.WINDOW_HEIGHT);{
	}
	
	private static final Rectangle SEARCHBAR_DEMASK = new Rectangle(730, 50);{
		SEARCHBAR_DEMASK.setLayoutX(480);
		SEARCHBAR_DEMASK.setLayoutY(10);
		SEARCHBAR_DEMASK.setArcWidth(30);
		SEARCHBAR_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle CELL_DEMASK = new Rectangle(225, 250); {
		CELL_DEMASK.setLayoutX(405);
		CELL_DEMASK.setLayoutY(445);
		CELL_DEMASK.setArcWidth(30);
		CELL_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle OPERATE_DEMASK = new Rectangle(205, 300);{
		OPERATE_DEMASK.setLayoutX(70);
		OPERATE_DEMASK.setLayoutY(460);
		OPERATE_DEMASK.setArcWidth(30);
		OPERATE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle HOMEPAGE_DEMASK = new Rectangle(160, 40);{
		HOMEPAGE_DEMASK.setLayoutX(90);
		HOMEPAGE_DEMASK.setLayoutY(476);
		HOMEPAGE_DEMASK.setArcWidth(30);
		HOMEPAGE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle BROWSE_DEMASK = new Rectangle(160, 40);{
		BROWSE_DEMASK.setLayoutX(90);
		BROWSE_DEMASK.setLayoutY(520);
		BROWSE_DEMASK.setArcWidth(30);
		BROWSE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle SUBSCRIBE_DEMASK = new Rectangle(160, 40);{
		SUBSCRIBE_DEMASK.setLayoutX(90);
		SUBSCRIBE_DEMASK.setLayoutY(570);
		SUBSCRIBE_DEMASK.setArcWidth(30);
		SUBSCRIBE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle DISLIKE_DEMASK = new Rectangle(160, 40);{
		DISLIKE_DEMASK.setLayoutX(90);
		DISLIKE_DEMASK.setLayoutY(615);
		DISLIKE_DEMASK.setArcWidth(30);
		DISLIKE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle CREATE_DEMASK = new Rectangle(160, 40);{
		CREATE_DEMASK.setLayoutX(90);
		CREATE_DEMASK.setLayoutY(660);
		CREATE_DEMASK.setArcWidth(30);
		CREATE_DEMASK.setArcHeight(30);
	}
	
	private static final Rectangle TUTORIAL_DEMASK = new Rectangle(160, 40);{
		TUTORIAL_DEMASK.setLayoutX(90);
		TUTORIAL_DEMASK.setLayoutY(710);
		TUTORIAL_DEMASK.setArcWidth(30);
		TUTORIAL_DEMASK.setArcHeight(30);
	}


	Shape mask = BG_MASK;
	VBox guideGroup = new VBox();
	Text guideText = new Text();
	Button nextBtn = new Button("Next");

	public HomePageTutorialView() {
		setup();
	}

	public void setup() {
		VBox.setMargin(guideText, new Insets(0, 0, 50, 0));
		mask.setOpacity(0.8);
		guideText.setFill(Color.WHITE);
		guideText.setFont(new Font(54));
		guideText.setText("Welcome to Saints' \n digital cookbook !");
		BackgroundFill btnBg = new BackgroundFill( Color.FORESTGREEN,new CornerRadii(20), Insets.EMPTY);
		Background background = new Background(btnBg);
		nextBtn.setBackground(background);
		nextBtn.setPrefSize(135, 60);
		nextBtn.setFont(new Font(36));
		nextBtn.setTextFill(Color.WHITE);
		nextBtn.setText("Next");
		nextBtn.setOnMouseClicked(controller);
		
		show();
	}

	private void show() {
		guideGroup.getChildren().clear();
		guideGroup.getChildren().addAll(guideText,nextBtn);
		guideGroup.setAlignment(Pos.CENTER);
		
		this.getChildren().clear();
		this.getChildren().addAll(mask, guideGroup);
		stage++;
	}

	public void showSearchBar() {
		mask = Shape.subtract(BG_MASK, SEARCHBAR_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setFont(new Font(36));
		guideText.setText("This is the Search Bar.\n \n "
				+ "You can search recipes by typing in a part of recipe name, \n "
				+ "and then click the SEARCH button");
		
		show();
	}
	
	public void showRecipeCell() {
		mask = Shape.subtract(BG_MASK, CELL_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setFont(new Font(24));
		guideText.setText("This is the Recipe cell. \n\n"
				+ "You can read the detailed information of recipe by clicking the picture of recipe. \n\n "
				+ "If you move your mouse on the picture, you can DISLIKE or SUBSCRIBE a recipe by button emerged.\n"
				+ "The disliked recipe cannot be found by Search Bar anymore ,but you can still find it in DISLIKE page.\n\n"
				+ "What's more, you can search recipes with the same tag by clicking TAG below the picture.");
		
		show();
	}
	
	public void showOperate() {
		mask = Shape.subtract(BG_MASK, OPERATE_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setFont(new Font(48));
		guideText.setText("These are Functional buttons. \n \n"
				+ "You can switch different page by click these buttons");
		
		show();
	}
	
	public void showHomepage() {
		mask = Shape.subtract(BG_MASK, HOMEPAGE_DEMASK);
		mask.setOpacity(0.8);

		guideText.setFont(new Font(36));
		guideText.setText("This is Homepage button. \n\n"
				+ "If you want to return this current Page and browser some Recommended recipes. \n"
				+ "then click it");
		
		show();
	}
	
	public void showBrowse() {
		mask = Shape.subtract(BG_MASK, BROWSE_DEMASK);
		mask.setOpacity(0.8);

		guideText.setText("Browser View will show recipes.\n \n"
				+ "Even if you have not searched anything, \n"
				+ "it will show all recipes in database.");
		
		show();
	}
	
	public void showSubscribe() {
		mask = Shape.subtract(BG_MASK, SUBSCRIBE_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setText("In this page, \n"
				+ "All subscribed recipe will showed up.");
		
		show();
	}
	
	public void showDislike() {
		mask = Shape.subtract(BG_MASK, DISLIKE_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setText("Every Recipe marked as DISLIKE can be found here.\n"
				+ "Also, you can removed them from Dislike List. \n \n"
				+ "Tips:  A balanced diet is important.");
		
		show();
	}
	
	public void showCreate() {
		mask = Shape.subtract(BG_MASK, CREATE_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setText("You can create your own recipe here.\n\n"
				+ "Perhaps, we will offer web service some day.\n"
				+ "Then your recipes can be spread over the world!");
	
		show();
	}
	
	public void showTutorial() {
		mask = Shape.subtract(BG_MASK, TUTORIAL_DEMASK);
		mask.setOpacity(0.8);
		
		guideText.setText("I know it's unnecessary for you.\n"
				+ "But if you want to read the tutorial again.\n"
				+ "Please click here.");
	
		show();
	}
	
	public void showFinal() {
		mask = BG_MASK;
		
		VBox.setMargin(guideText, new Insets(0, 0, 50, 0));
		guideText.setFont(new Font(54));
		guideText.setText("Have a good time!\n\n"
				+ "Btw, much thanks to our teacher\n"
				+ "Lenka\n");
		
		nextBtn.setPrefWidth(180);
		nextBtn.setText("START!");
		
		show();
	}
	
	public static void deActivateTutorial() {
		stage = 0;
		isTutorial = false;
	}
	
	public static void activateTutorial() {
		isTutorial = true;
	}
}
