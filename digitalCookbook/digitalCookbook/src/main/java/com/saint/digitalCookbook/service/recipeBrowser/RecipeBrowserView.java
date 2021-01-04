package com.saint.digitalCookbook.service.recipeBrowser;

import java.util.ArrayList;
import java.util.List;

import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * view class of the app, creates the GUI
 * @author LinHAN， Zhouyao Yu
 * @Refactor Zhouyao Yu
 * 
 */
public class RecipeBrowserView {
	private RecipeBrowserController controller;
	private RecipeBrowserModel model;

	public static final int BROWSER_WIDTH = 600;
	public static final int BROWSER_HEIGHT = 800;	
	public static final double AMOUNT_TEXT_WIDTH = 40;
	public static final double AMOUNT_PANE_WIDTH = 200;
	public static final double AMOUNT_CALC_BORDER = 40;
	public static final int INGREDIENT_TABLE_WIDTH = 420;
	public static final int INGREDIENT_TABLE_HEIGHT = 200;
	public static final String DEFAULT_SERVE_AMOUNT = "1";
	public static final int TABLE_CELL_OFFSET = 1;
	public static final int TABLE_COL_NUM = 4;
	public static final double ID_COL_WIDTH = 30;
	public static final double NAME_COL_WIDTH = 200;
	public static final double AMOUNT_COL_WIDTH = 80;
	public static final double UNIT_COL_WIDTH = 100;
	public static final double EDIT_COL_WIDTH = 60;
	public static final double DELETE_COL_WIDTH = 60;
	public static final String LOCAL_IMAGE_PREFIX = "file:";
	public static final double IMAGE_WIDTH = 200;
	public static final double IMAGE_HEIGHT = 200;
	public static final double IMAGE_BORDER = 50;
	public static final double TAG_BORDER = 20;
	public static final double NEWTAG_BORDER = 10;
	public static final double STEP_TEXT_WIDTH = 580;
	public static final double STEP_TEXT_HEIGHT = 280;
	public static final double STEP_TEXT_FONT = 16;
	public static final double CONFIRM_BUTTON_BORDER = 30;

	public RecipeBrowserView(RecipeBasicInfo recipeBasicInfo) {
		this.model = new RecipeBrowserModel(recipeBasicInfo);
		this.controller = new RecipeBrowserController(model, this);

		this.pop();
	}

	// ----- Recipe Editor Main View ----- START ----- //
	/**
	 * Field of stage
	 */
	Stage browserStage = new Stage();
	private StackPane browserPane;

	/**
	 * Fields of elements, contains the information of the recipe
	 */
	Label name = new Label("Name");
	Label amount = new Label("Serve amount");
	Label prepTime = new Label("Preparation time");
	Label cookTime = new Label("Cooking time");
	ImageView imageView = new ImageView();
	Label tag = new Label("tag:");

	TextField nameText = new TextField();
	TextField amountText = new TextField();
	Button amountInc = new Button("+");
	Button amountDec = new Button("-");
	Button amountCalc = new Button("CALC");
	TextField prepText = new TextField();
	TextField cookText = new TextField();
	FlowPane tagPane = new FlowPane();
	List<Label> tagList = new ArrayList<Label>();

	Label ingreText = new Label("List of ingredients");
	List<Text> idCol = new ArrayList<Text>();
	List<Text> nameCol = new ArrayList<Text>();
	List<Text> amountCol = new ArrayList<Text>();
	List<Text> unitCol = new ArrayList<Text>();
	GridPane ingredientTable = new GridPane();{
		ingredientTable.setPrefSize(INGREDIENT_TABLE_WIDTH, INGREDIENT_TABLE_HEIGHT);
	}
	ScrollPane ingreScrollPane = new ScrollPane();{
		ingreScrollPane.setPrefSize(INGREDIENT_TABLE_WIDTH, INGREDIENT_TABLE_HEIGHT);
		ingreScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	TextArea stepTArea = new TextArea();{
		stepTArea.setPrefWidth(STEP_TEXT_WIDTH);
		stepTArea.setPrefHeight(STEP_TEXT_HEIGHT);
		stepTArea.setFont(new Font(STEP_TEXT_FONT));
		stepTArea.setWrapText(true);
	}
	Button editBtn  = new Button("Edit Recipe");
	Button deleteBtn = new Button("Delete Recipe");

	public void pop() {
		browserStage.setTitle("Browser Stage");
		browserPane = new StackPane();
		Scene scene = new Scene(browserPane, BROWSER_WIDTH, BROWSER_HEIGHT);
		this.setup();
		browserStage.setScene(scene);
		browserStage.show();
	}

	/**
	 * Use to set up the pane of browser
	 */
	private void setup() {
		initialize();
		BorderPane root = new BorderPane();
		browserPane.getChildren().add(root);

		//upper part, contains basic information
		// Recipe info		
		GridPane infoGridPane = new GridPane();

		// Recipe info - amount
		FlowPane amountPane = new FlowPane();
		amountPane.setPrefWidth(AMOUNT_PANE_WIDTH);
		amountText.setPrefWidth(AMOUNT_TEXT_WIDTH);
		FlowPane.setMargin(amountCalc, new Insets(0, 0, 0, AMOUNT_CALC_BORDER));
		amountPane.getChildren().addAll( amountDec, amountText, amountInc, amountCalc);

		// Recipe info -wrap
		infoGridPane.addColumn(1, name, amount, prepTime, cookTime);
		infoGridPane.addColumn(2, nameText, amountPane, prepText, cookText);

		infoGridPane.setPadding(new Insets(20));
		infoGridPane.setHgap(5);
		infoGridPane.setVgap(10);

		nameText.setEditable(false);
		amountText.setEditable(false);
		prepText.setEditable(false);
		cookText.setEditable(false);
		stepTArea.setEditable(false);

		VBox upperLeft = new VBox();
		upperLeft.getChildren().add(infoGridPane);
		upperLeft.getChildren().add(tag);
		upperLeft.getChildren().add(tagPane);

		tagPane.setPrefWidth(200);
		VBox.setMargin(tag, new Insets(0, 0, TAG_BORDER / 2, TAG_BORDER));
		VBox.setMargin(tagPane, new Insets(0, 0, 0, 2 * TAG_BORDER));

		StackPane imagePane = new StackPane();
		imagePane.setPrefSize(IMAGE_WIDTH + IMAGE_BORDER, IMAGE_HEIGHT + IMAGE_BORDER);
		imagePane.getChildren().add(imageView);
		StackPane.setAlignment(imageView, Pos.CENTER);
		// Wrap upper part
		BorderPane top = new BorderPane();
		top.setLeft(upperLeft);
		top.setRight(imagePane);

		//middle part, contains ingredients

		// Ingredient Table
		ingreScrollPane.setContent(ingredientTable);

		GridPane ingrePane = new GridPane();
		ingrePane.addColumn(0, ingreText, ingreScrollPane);
		ingrePane.setPadding(new Insets(1, 20, 1, 20));
		ingrePane.setVgap(10);

		//lower part, contains preparation steps and a confirm button
		Label step = new Label("Cooking Steps:");
		VBox stepBox = new VBox(step, stepTArea);

		HBox confirmBox = new HBox();
		confirmBox.getChildren().addAll(editBtn, deleteBtn);
		HBox.setMargin(editBtn, new Insets(0, CONFIRM_BUTTON_BORDER, 0, 0));
		HBox.setMargin(deleteBtn, new Insets(0, 0, 0, CONFIRM_BUTTON_BORDER));
		confirmBox.setAlignment(Pos.CENTER);

		GridPane bottom = new GridPane();
		bottom.addColumn(0, stepBox, confirmBox);
		bottom.setPadding(new Insets(20));
		bottom.setVgap(10);

		root.setTop(top);
		root.setCenter(ingrePane);
		root.setBottom(bottom);
	}

	/**
	 * Load the action listener and contents
	 */
	private void initialize() {
		nameText.setText(model.getName());
		prepText.setText(model.getPrepTime());
		cookText.setText(model.getCookTime());
		stepTArea.setText(model.getCookStep());
		amountText.setText(DEFAULT_SERVE_AMOUNT);
		amountInc.setOnMouseClicked(controller);
		amountDec.setOnMouseClicked(controller);
		amountCalc.setOnMouseClicked(controller);
		editBtn.setOnMouseClicked(controller);
		deleteBtn.setOnMouseClicked(controller);

		loadImage();
		initialTags();
		loadIngredientTable(model.getIngredients());
	}

	/**
	 * Used to load Image to view
	 */
	public void loadImage() {
		imageView.setImage(null);
		Image image = new Image(LOCAL_IMAGE_PREFIX + model.getPicture(), IMAGE_WIDTH, IMAGE_HEIGHT, false, false);
		imageView.setImage(image);
	}

	/**
	 * Load ingredients from model to ingredient table
	 * Setup Head titles 
	 * Add record to table according to ingredientList
	 * @param ingredientList
	 */
	public void loadIngredientTable(List<Ingredient> ingredientList) {
		// Add Header and constraints
		ingredientTable.getChildren().clear();
		ColumnConstraints constraints = new ColumnConstraints();
		constraints.setHalignment(HPos.CENTER);
		Button idHead = new Button("ID");
		Button nameHead = new Button("Name");
		Button amountHead = new Button("Amount");
		Button unitHead = new Button("Unit");
		idHead.setPrefWidth(ID_COL_WIDTH);
		nameHead.setPrefWidth(NAME_COL_WIDTH);
		amountHead.setPrefWidth(AMOUNT_COL_WIDTH);
		unitHead.setPrefWidth(UNIT_COL_WIDTH);
		ingredientTable.addRow(0, idHead, nameHead, amountHead, unitHead);
		for (int i = 0; i < TABLE_COL_NUM; i++) {
			ingredientTable.getColumnConstraints().add(i, constraints);
		}

		// Add ingredients
		List<Ingredient> ingredients = ingredientList;
		idCol.clear();
		nameCol.clear();
		amountCol.clear();
		unitCol.clear();
		for (Ingredient in : ingredients) {
			idCol.add(new Text(in.getIngredientId()));
			nameCol.add(new Text(in.getName()));
			amountCol.add(new Text(Float.toString(in.getAmount())));
			unitCol.add(new Text(in.getUnit()));
		}
		for (int i = 0; i < ingredients.size(); i++) {
			ingredientTable.add(idCol.get(i), 0, i + TABLE_CELL_OFFSET);
			ingredientTable.add(nameCol.get(i), 1, i + TABLE_CELL_OFFSET);
			ingredientTable.add(amountCol.get(i), 2, i + TABLE_CELL_OFFSET);
			ingredientTable.add(unitCol.get(i), 3, i + TABLE_CELL_OFFSET);
		}
	}

	/**
	 * Load tags from model to tagList
	 */
	private void initialTags() {
		List<String> tagNames = model.getTags();
		tagList.clear();
		for(String str : tagNames) {
			tagList.add(new Label(str));
		}
		loadTags();
	}

	/**
	 * Used to load tags to view
	 */
	public void loadTags() {
		tagPane.getChildren().clear();
		for(Label label : tagList) {
			label.setPadding(new Insets(0, 5, 0, 0));
			label.setOnMouseClicked(controller);
			label.setOnMouseEntered(controller);
			label.setOnMouseExited(controller);
			tagPane.getChildren().add(label);
		}
	}

	// ----- Recipe Editor Main View ----- END ----- //

	// ----- Delete Pop Window ----- START ----- //
	/**
	 * Field of delete Pop window
	 */
	Stage deletePopStage = new Stage();
	Button deleteConfirm = new Button("Confirm");
	Button deleteCancel = new Button("Cancel");
	/**
	 * a popped window after clicking 'delete ingredient' button
	 * enables user to delete selected ingredient
	 */
	public void deletePop() {
		Label hint = new Label();
		hint.setText("Do you want to delete?");

		FlowPane deletePane = new FlowPane();
		Scene deleteScene = new Scene(deletePane, 300, 170);
		deleteConfirm.setOnMouseClicked(controller);
		deleteCancel.setOnMouseClicked(controller);

		HBox deleteBox = new HBox(deleteConfirm, deleteCancel);
		deleteBox.setSpacing(15);
		VBox deleteOperate = new VBox(hint, deleteBox);
		deleteOperate.setSpacing(20);
		deletePane.getChildren().add(deleteOperate);
		deletePane.setAlignment(Pos.CENTER);

		deletePopStage.setScene(deleteScene);
		deletePopStage.show();
	}
	// ----- Delete Pop Window ----- END ----- //
}
