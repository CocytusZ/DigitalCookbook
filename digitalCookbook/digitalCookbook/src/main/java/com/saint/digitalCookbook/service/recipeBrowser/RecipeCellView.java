package com.saint.digitalCookbook.service.recipeBrowser;

import java.util.ArrayList;
import java.util.List;

import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.service.homePage.HomePageView;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * view class of the app, creates the GUI
 * @author Han Jin
 * @Refactor Han Jin
 * 
 */
public class RecipeCellView extends Group {
	
	private RecipeSuscribeModel model = new RecipeSuscribeModel();
	private RecipeCellController controller = new RecipeCellController(this, model);
	
	public static final int IMAGE_SIZE = 200;
	public static final int X_LAYOUT = 65;
	public static final int Y_LAYOUT = 25;
	public static final int X_LAYOUT_BUTTON = 25;
	public static final int Y_LAYOUT_BUTTON = 80;
	public static final int BUTON_SPACING = 10;
	public static final int TAG_GAP = 5;
	public static final int CELL_MIN_HEIGHT = 260;

	/**
	 * Fields of elements, contained in the home page
	 */
	public Image cellImage;
	public ImageView cellImageView;
	public boolean isSuscribe; 
	public boolean isDislike;
	public HBox choice = new HBox();
	public Pane cellImageEnter = new Pane();
	public BoxBlur blur;
	public String recipeID;
	public Button subscribe = new Button();
	public Button dislike = new Button();
	public Text name;
	public List<Button> tag = new ArrayList<Button>();
	public HomePageView homepage;
	public FlowPane tagSet = new FlowPane(); 
	public VBox cellUnit;
	public RecipeBasicInfo recipeBasicInfo;
	public RecipeBrowserView recipeBrowserView;
	private static final String prefix = "file:";
	
	Image subscribeImgOrig=new Image("file:src/main/resources/image/system/cellViewButton/notSubscribed.png",50,30,false,false);
	Image dislikeImgOrig=new Image("file:src/main/resources/image/system/cellViewButton/notDisliked.png",50,30,false,false);
	Image subscribeImgClicked = new Image("file:src/main/resources/image/system/cellViewButton/isSubscribed.png",50,30,false,false);
	Image dislikeImgClicked = new Image("file:src/main/resources/image/system/cellViewButton/isDisliked.png",50,30,false,false);
	ImageView subscribeView = new ImageView(subscribeImgOrig);
	ImageView dislikeView = new ImageView(dislikeImgOrig);
	
	public RecipeCellView() {}
	public RecipeCellView(String recipeImageRoute, String recipeName, List<String> recipeTags, boolean isSuscribe, boolean isDislike, String recipeID, RecipeBasicInfo recipeBasicInfo, HomePageView homepage) {
		
		this.setLayoutX(X_LAYOUT);
		this.setLayoutY(Y_LAYOUT);
		
		this.recipeID = recipeID;
		this.isSuscribe = isSuscribe;
		this.isDislike = isDislike;
		this.recipeBasicInfo = recipeBasicInfo;
		this.homepage = homepage;
		
		cellImage = new Image(prefix+recipeImageRoute, IMAGE_SIZE, IMAGE_SIZE, false, false);
		cellImageView = new ImageView(cellImage);	
		cellImageEnter.setOnMouseEntered(controller);
		cellImageEnter.setOnMouseExited(controller);
			
		if(isSuscribe)
			subscribeView = new ImageView(subscribeImgClicked);
		else
			subscribeView = new ImageView(subscribeImgOrig);
		if(isDislike)
			dislikeView= new ImageView(dislikeImgClicked);
		else
			dislikeView = new ImageView(dislikeImgOrig); 
		subscribe.setGraphic(subscribeView);
		subscribe.setOnMouseClicked(controller);
		dislike.setGraphic(dislikeView);
		dislike.setOnMouseClicked(controller);	
		choice = new HBox(subscribe,dislike);
		choice.setLayoutX(X_LAYOUT_BUTTON);
		choice.setLayoutY(Y_LAYOUT_BUTTON);
		choice.setSpacing(BUTON_SPACING);
		cellImageEnter.getChildren().add(choice);
		cellImageEnter.getChildren().add(cellImageView);
		
		name = new Text(recipeName);
		
		for(String s: recipeTags) 
			tag.add(new Button(s));
		for(Button b: tag) {
			tagSet.getChildren().add(b);
			b.setOnMouseClicked(controller);
		}	
		
		tagSet.setHgap(TAG_GAP);
		cellUnit = new VBox(cellImageEnter, name, tagSet);
		cellUnit.setMinHeight(CELL_MIN_HEIGHT);
		this.getChildren().add(cellUnit);
		cellUnit.setOnMouseClicked(controller);
	}
		/**
		 * This is called to show the subscribe and dislike button
		 */
		public void buttonShowUpdate() {
			blur = new BoxBlur(10, 10, 1);
			cellImageView.setEffect(blur);
			cellImageEnter.getChildren().get(0).toFront();
		}
		
		/**
		 * This is called to hide the subscribe and dislike button
		 */
		public void buttonDisappearUpdate() {
			blur = new BoxBlur(10, 10, 0);
			cellImageView.setEffect(blur);
			cellImageEnter.getChildren().get(1).toBack();
		}
		
		/**
		 * This is called to update the subscribe and dislike button
		 */
		public void subscribeButtonUpdate(boolean b) {
			if (b)
				subscribeView = new ImageView(subscribeImgOrig);
			else
				subscribeView = new ImageView(subscribeImgClicked);
			subscribe.setGraphic(subscribeView);
	}
		
		/**
		 * This is called to update the dislike and dislike button
		 */
		public void dislikeButtonUpdate(boolean b) {
			if (b)
				dislikeView = new ImageView(dislikeImgOrig);
			else
				dislikeView = new ImageView(dislikeImgClicked);
			dislike.setGraphic(dislikeView);
		}	
}
