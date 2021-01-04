package com.saint.digitalCookbook.service.homePage;


import java.util.ArrayList;
import java.util.List;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.service.recipeBrowser.RecipeCellView;
import com.saint.digitalCookbook.ui.component.helloSection.HelloSectionView;
import com.saint.digitalCookbook.ui.animator.RecipeCellAnimator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * view class of the app, creates the GUI
 * @author Han Jin
 * @Refactor Han Jin
 * 
 */
public class HomePageView extends BorderPane{


	RecipeSearchModel model = new RecipeSearchModel();
	HomePageController controller = new HomePageController(this, model);

	
	// ----- Recipe home page Main View ----- START ----- //
	public static final int SEARCHBAR_MINWIDTH = 600;
	public static final int SEARCHBAR_MINHeight = 58;
	public static final Insets TEAM_NAME_PADDING = new Insets(5,250,0,0);
	public static final Color HEADLINE_BG_COLOR = new Color(0.988, 0.225, 0.225, 1);
	public static final Insets HEADLINE_PADDING = new Insets(15);
	public static final int HEADLINE_WIDTH = 1540;
	public static final int SIDEBAR_MINWIDTH = 350;
	public static final Insets SIDEBAR_PADDING = new Insets(15);
	public static final Insets PROFILE_IMAGE_INFO_MARGIN = new Insets(25,0,10,0);
	public static final int CHICKEN_SOUP_AREA_HEIGHT = 250;
	public static final double WINDOW_WIDTH = 1540;
	public static final double WINDOW_HEIGHT = 768;

	/**
	 * Fields of elements, contained in the home page
	 */
	public Label teamName = new Label("Saint's digital cookbook");
	public TextField searchBar = new TextField();
	public Button searchConfirm = new Button("Search!");
	public Button exitButton = new Button("x");
	public Image profileImage = new Image("file:src/main/resources/image/system/delicacy.jpg",270, 270, false, false);
	public ImageView profileImageView = new ImageView(profileImage);
	public Label name = new Label("When God is cooking, you can't see the smoke.");
	public Label createMyRecipe= new Label("Create!");
	public Label browse= new Label("Browse");
	public Label subscribe= new Label("Suscribe");
	public Label dislike= new Label("Dislike");
	public Label homepage = new Label("Homepage");
	public Label tutorial = new Label("Tutorial");

	Pane browseDisplay = new Pane();
	Label subscribeCell = new Label("");
	Pane subscribeDisplay = new Pane(subscribeCell);
	Label dislikeCell = new Label("");
	Pane dislikeDisplay = new Pane(dislikeCell);
	FlowPane chickenSoupArea = new FlowPane(new HelloSectionView());
	Group cellDisplay = new Group(browseDisplay);
	BorderPane sideBar = new BorderPane();
	BorderPane mainDisplayInitial; 
	Pane mainDisplay = new Pane();
	ScrollPane mainDisplayScroll = new ScrollPane();

	/**
	 * Use to set up the pane of home page
	 */
	public HomePageView() {
		start();
	}

	private void start(){

		//field of the control bar on the top
		teamName.setPadding(TEAM_NAME_PADDING);
		teamName.setTextFill(Color.WHITE);
		teamName.setStyle("-fx-font-size: 20;");
		searchBar.setText("");
		searchBar.setEditable(true);
		searchBar.setMinWidth(SEARCHBAR_MINWIDTH);
		searchBar.setStyle("-fx-font-size: 20;");
		searchConfirm.setStyle("-fx-font-size: 20;");
		HBox searchField = new HBox(searchBar, searchConfirm);		
		exitButton.setStyle("-fx-font-size: 20;");
		
		BorderPane headLine = new BorderPane();
		headLine.setMinWidth(HEADLINE_WIDTH);
		headLine.setMaxWidth(HEADLINE_WIDTH);
		headLine.setLeft(teamName);
		headLine.setCenter(searchField);
		headLine.setRight(exitButton); 
		headLine.setPadding(HEADLINE_PADDING);
		headLine.setMinHeight(SEARCHBAR_MINHeight);
		Paint headLineBackground = HEADLINE_BG_COLOR;
		headLine.setBackground(new Background(new BackgroundFill(headLineBackground, CornerRadii.EMPTY, Insets.EMPTY)));
		
		searchConfirm.setOnMouseClicked(controller);
		exitButton.setOnMouseClicked(controller);

		////field of the side bar on the left
		VBox profileInfo = new VBox(profileImageView, name);
		VBox selectDisplay = new VBox();
		selectDisplay.getChildren().addAll(homepage, browse, subscribe, dislike, createMyRecipe, tutorial);
		selectDisplay.setPadding(SIDEBAR_PADDING);
		
		for (Node n: selectDisplay.getChildren()) {
			n.setStyle("-fx-font-family: 'recommended';-fx-font-size: 20; -fx-padding: 10");
			n.setOnMouseEntered(controller);
			n.setOnMouseExited(controller);
			n.setOnMouseClicked(controller);
		}
		
		sideBar.setStyle("-fx-border-width: 0 1 0 0;-fx-border-style: solid;-fx-border-color: black");
		sideBar.setMinWidth(SIDEBAR_MINWIDTH);
		sideBar.setTop(profileInfo);
		sideBar.setCenter(selectDisplay);
		VBox.setMargin(profileImageView, PROFILE_IMAGE_INFO_MARGIN);
		profileInfo.setAlignment(Pos.BOTTOM_CENTER);
		selectDisplay.setAlignment(Pos.BOTTOM_CENTER);

		////field of the main part in the center	
		chickenSoupArea.setMinHeight(CHICKEN_SOUP_AREA_HEIGHT);
		chickenSoupArea.setPrefWidth(1068);
		chickenSoupArea.setAlignment(Pos.CENTER);
		chickenSoupArea.setStyle("-fx-border-width: 0 0 1 0;-fx-border-style: solid;-fx-border-color: black");
		chickenSoupArea.setMaxWidth(1068);
		initialDisplay(model.searchRecommendRecipe(4),  model.searchDislikeRecipe(), model.searchRecommendRecipe(2).size());
		mainDisplayInitial= new BorderPane(cellDisplay, chickenSoupArea, null, null, null);

		//put them together
		this.setTop(headLine);
		this.setLeft(sideBar);
		this.setCenter(mainDisplayInitial);
		this.setPrefWidth(WINDOW_WIDTH);
		this.setPrefHeight(WINDOW_HEIGHT);
	}

	/**
	 * 
	 * @param recipeBasicInfo: the recommended recipes
	 * @param recipeBasicInfoDislike: the recipes that were disliked, which will not be shown
	 * @param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void initialDisplay(List<RecipeBasicInfo> recipeBasicInfo, List<RecipeBasicInfo> recipeBasicInfoDislike, int recipeCounter) {
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		List<String> buffer = new ArrayList<String>();
		for (RecipeBasicInfo r: recipeBasicInfoDislike)	
			buffer.add(r.getName());
		for (RecipeBasicInfo r: recipeBasicInfo) {
			if(!buffer.contains(r.getName())) {
				List<String> tags = new ArrayList<String>();
				String tagStr = new String(r.getTag());
				while (tagStr.contains(",")) {
					int commaPos = tagStr.indexOf(",");
					tags.add(tagStr.substring(0, commaPos));
					tagStr = tagStr.substring(commaPos+1, tagStr.length());
				}
				tags.add(tagStr);		
				recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
			}
		}
		RecipeCellAnimator.createAnimator(recipeCells);
		for (RecipeCellView view : recipeCells) 
			browseDisplay.getChildren().add(view);
		browseDisplay.setPrefWidth(1030);
		browseDisplay.getChildren().add(new Label("Today's special dish:"));
		RecipeCellAnimator.playEntrance();
	}

	/**
	 * 
	 * @param recipeBasicInfo: the result recipes
	 * @param recipeBasicInfoDislike: the recipes that were disliked, which will not be shown
	 * @param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void updateSearch(List<RecipeBasicInfo> recipeBasicInfo, List<RecipeBasicInfo> recipeBasicInfoDislike, int recipeCounter) {
		mainDisplay.getChildren().clear();
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		List<String> buffer = new ArrayList<String>();
		for (RecipeBasicInfo r: recipeBasicInfoDislike)	
			buffer.add(r.getName());
		for (RecipeBasicInfo r: recipeBasicInfo) {
			if(!buffer.contains(r.getName())) {
				List<String> tags = new ArrayList<String>();
				String tagStr = new String(r.getTag());
				while (tagStr.contains(",")) {
					int commaPos = tagStr.indexOf(",");
					tags.add(tagStr.substring(0, commaPos));
					tagStr = tagStr.substring(commaPos+1, tagStr.length());
				}
				tags.add(tagStr);		
				recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
			}
		}
		mainDisplay.getChildren().add(new Label("Search result:"));
		RecipeCellAnimator.createAnimator(recipeCells);
		for (RecipeCellView view : recipeCells) 
			mainDisplay.getChildren().add(view);
		browseDisplay.getChildren().add(new Label("Today's special dish:"));
		RecipeCellAnimator.playEntrance();
		mainDisplayScroll.setContent(mainDisplay);
		mainDisplay.setPrefSize(1068, (recipeCells.size()/4+1)*340+65>710?(recipeCells.size()/4+1)*340+65:708);
		mainDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setCenter(mainDisplayScroll);	
	}

	/**
	 * 
	 * @param recipeBasicInfo: all of the recipes
	 * @param recipeBasicInfoDislike: the recipes that were disliked, which will not be shown
	 * @param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void updateBrowse(List<RecipeBasicInfo> recipeBasicInfo, List<RecipeBasicInfo> recipeBasicInfoDislike, int recipeCounter) {
		mainDisplay.getChildren().clear();
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		List<String> buffer = new ArrayList<String>();
		for (RecipeBasicInfo r: recipeBasicInfoDislike)	
			buffer.add(r.getName());
		
		for (RecipeBasicInfo r: recipeBasicInfo) {
			if(!buffer.contains(r.getName())) {
				List<String> tags = new ArrayList<String>();
				String tagStr = new String(r.getTag());
				while (tagStr.contains(",")) {
					int commaPos = tagStr.indexOf(",");
					tags.add(tagStr.substring(0, commaPos));
					tagStr = tagStr.substring(commaPos+1, tagStr.length());
				}
				tags.add(tagStr);		
				recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
			}
		}
		mainDisplay.getChildren().add(new Label("Search result:"));
		RecipeCellAnimator.createAnimator(recipeCells);
		for (RecipeCellView view : recipeCells) 
			mainDisplay.getChildren().add(view);
		browseDisplay.getChildren().add(new Label("Today's special dish:"));
		RecipeCellAnimator.playEntrance();
		mainDisplayScroll.setContent(mainDisplay);
		mainDisplay.setPrefSize(1068, (recipeCells.size()/4+1)*340+65>710?(recipeCells.size()/4+1)*340+65:708);
		mainDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setCenter(mainDisplayScroll);
	}
	
	/**
	 * 
	 * @param recipeBasicInfo: the susbscrubed recipes
	 * @param recipeBasicInfoDislike: the recipes that were disliked, which will not be shown
	 * @param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void updateSubscribe(List<RecipeBasicInfo> recipeBasicInfo, List<RecipeBasicInfo> recipeBasicInfoDislike, int recipeCounter) {
		mainDisplay.getChildren().clear();
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		List<String> buffer = new ArrayList<String>();
		for (RecipeBasicInfo r: recipeBasicInfoDislike)	
			buffer.add(r.getName());
		for (RecipeBasicInfo r: recipeBasicInfo) {
			if(!buffer.contains(r.getName())) {
				List<String> tags = new ArrayList<String>();
				String tagStr = new String(r.getTag());
				while (tagStr.contains(",")) {
					int commaPos = tagStr.indexOf(",");
					tags.add(tagStr.substring(0, commaPos));
					tagStr = tagStr.substring(commaPos+1, tagStr.length());
				}
				tags.add(tagStr);		
				recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
			}
		}
		mainDisplay.getChildren().add(new Label("Favourites:"));
		RecipeCellAnimator.createAnimator(recipeCells);
		for (RecipeCellView view : recipeCells) 
			mainDisplay.getChildren().add(view);
		RecipeCellAnimator.playEntrance();
		mainDisplayScroll.setContent(mainDisplay);
		mainDisplay.setPrefSize(1068, (recipeCells.size()/4+1)*340+65>710?(recipeCells.size()/4+1)*340+65:708);
		mainDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setCenter(mainDisplayScroll);
	}

	/**
	 * 
	 * @param recipeBasicInfo: the disliked recipes will only be shown here
	@param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void updateDislike(List<RecipeBasicInfo> recipeBasicInfo, int recipeCounter) {
		mainDisplay.getChildren().clear();
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		for (RecipeBasicInfo r: recipeBasicInfo) {
			List<String> tags = new ArrayList<String>();
			String tagStr = new String(r.getTag());
			while (tagStr.contains(",")) {
				int commaPos = tagStr.indexOf(",");
				tags.add(tagStr.substring(0, commaPos));
				tagStr = tagStr.substring(commaPos+1, tagStr.length());
			}
			tags.add(tagStr);		
			recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
		}
		RecipeCellAnimator.createAnimator(recipeCells);
		for (RecipeCellView view : recipeCells) 
			mainDisplay.getChildren().add(view);
		mainDisplay.getChildren().add(new Label("Search results:"));
		RecipeCellAnimator.playEntrance();
		mainDisplayScroll.setContent(mainDisplay);
		mainDisplay.setPrefSize(1068, (recipeCounter/4+1)*340+65>710?(recipeCounter/4+1)*340+65:708);
		mainDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setCenter(mainDisplayScroll);	
	}

	/**
	 * This is called to return to the original home page
	 */
	public void returnHomepage() {
		browseDisplay.getChildren().clear();	
		initialDisplay(model.searchRecommendRecipe(4),  model.searchDislikeRecipe(), model.searchRecommendRecipe(2).size());
		this.setCenter(mainDisplayInitial);
	}

	/**
	 * 
	 * @param recipeBasicInfo: the result recipes
	 * @param recipeBasicInfoDislike: the recipes that were disliked, which will not be shown
	 * @param recipeCounter: count the number of recipes, and calculates the height of the pane 
	 */
	public void updateTag(List<RecipeBasicInfo> recipeBasicInfo, List<RecipeBasicInfo> recipeBasicInfoDislike, int size) {
		mainDisplay.getChildren().clear();
		List<RecipeCellView> recipeCells = new ArrayList<RecipeCellView>();
		List<String> buffer = new ArrayList<String>();
		for (RecipeBasicInfo r: recipeBasicInfoDislike)	
			buffer.add(r.getName());
		for (RecipeBasicInfo r: recipeBasicInfo) {
			if(!buffer.contains(r.getName())) {
				List<String> tags = new ArrayList<String>();
				String tagStr = new String(r.getTag());
				while (tagStr.contains(",")) {
					int commaPos = tagStr.indexOf(",");
					tags.add(tagStr.substring(0, commaPos));
					tagStr = tagStr.substring(commaPos+1, tagStr.length());
				}
				tags.add(tagStr);		
				recipeCells.add(new RecipeCellView(r.getpicture(), r.getName(), tags, r.isSubscribe(), r.isDislike(), r.getRecipeId(), r, this));
			}
		}
		RecipeCellAnimator.createAnimator(recipeCells);
		mainDisplay.getChildren().add(new Label("Search result:"));
		for (RecipeCellView view : recipeCells) 
			mainDisplay.getChildren().add(view);
		RecipeCellAnimator.playEntrance();
		mainDisplayScroll.setContent(mainDisplay);
		mainDisplay.setPrefSize(1068, (recipeCells.size()/4+1)*340+65>710?(recipeCells.size()/4+1)*340+65:708);
		mainDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setCenter(mainDisplayScroll);
	}

}
