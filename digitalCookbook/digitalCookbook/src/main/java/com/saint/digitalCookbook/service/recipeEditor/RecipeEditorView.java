package com.saint.digitalCookbook.service.recipeEditor;

import java.util.ArrayList;
import java.util.List;

import com.saint.digitalCookbook.constant.RecipeTagEnum;
import com.saint.digitalCookbook.constant.UnitEnum;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * view class of the app, creates the GUI
 * @author LinHAN, Zhouyao Yu
 * @Refactor Zhouyao Yu
 */
public class RecipeEditorView extends Pane {
	/**
	 * fields of the view which point to the model and the controller
	 */
	private static RecipeEditorModel model;
	private static RecipeEditorController controller;
	
	/**
	 * Fields of constant value of RecipeEditorView
	 */
	public static final int EDITOR_WIDTH = 600;
	public static final int EDITOR_HEIGHT = 800;	
	public static final int INGREDIENT_TABLE_WIDTH = 580;
	public static final int INGREDIENT_TABLE_HEIGHT = 300;
	public static final String DEFAULT_SERVE_AMOUNT = "1";
	public static final int TABLE_CELL_OFFSET = 1;
	public static final int TABLE_COL_NUM = 6;
	public static final double ID_COL_WIDTH = 30;
	public static final double NAME_COL_WIDTH = 200;
	public static final double AMOUNT_COL_WIDTH = 80;
	public static final double UNIT_COL_WIDTH = 100;
	public static final double EDIT_COL_WIDTH = 60;
	public static final double DELETE_COL_WIDTH = 60;
	public static final String LOCAL_IMAGE_PREFIX = "file:";
	public static final double IMAGE_WIDTH = 200;
	public static final double IMAGE_HEIGHT = 200;
	public static final double IMAGE_BORDER = 80;
	public static final double TAG_BORDER = 20;
	public static final double NEWTAG_BORDER = 10;
	public static final double TAG_FONT = 12;

	/**
	 * Field of stage
	 */
	public Stage editStage;
	StackPane editPane;

	/**
	 * constructor, to build a view object with recipe info
	 */
	public RecipeEditorView(RecipeBasicInfo recipeBasicInfo) {
		model = new RecipeEditorModel(recipeBasicInfo);
		controller = new RecipeEditorController(model, this);
		editStage = new Stage();
	}

	// ----- Recipe Editor Main View ----- START ----- //

	/**
	 * fields of elements, contains the information of the recipe
	 */
	Label name = new Label("Name");
	Label amount = new Label("Serve amount");
	Label prepTime = new Label("Preparation time");
	Label cookTime = new Label("Cooking time");
	ImageView imageView = new ImageView();
	Label tag = new Label("tag:");
	Text newTagIcon = new Text("+");

	TextField nameText = new TextField();
	TextField amountText = new TextField();
	TextField prepText = new TextField();
	TextField cookText = new TextField();
	FlowPane tagPane = new FlowPane();
	List<Label> tagList = new ArrayList<Label>();

	Label ingreText = new Label("List of ingredients");
	List<Text> idCol = new ArrayList<Text>();
	List<Text> nameCol = new ArrayList<Text>();
	List<Text> amountCol = new ArrayList<Text>();
	List<Text> unitCol = new ArrayList<Text>();
	List<Button> editCol = new ArrayList<Button>();
	List<Button> deleteCol = new ArrayList<Button>();
	GridPane ingredientTable = new GridPane();{
		ingredientTable.setPrefSize(INGREDIENT_TABLE_WIDTH, INGREDIENT_TABLE_HEIGHT);
	}
	ScrollPane ingreScrollPane = new ScrollPane();{
		ingreScrollPane.setPrefSize(INGREDIENT_TABLE_WIDTH, INGREDIENT_TABLE_HEIGHT);
		ingreScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	Button addIngre = new Button("Add Ingredient");
	Button deleteIngre = new Button("Delete All");

	TextArea stepTArea = new TextArea();
	Button saveRecipe  = new Button("Save Recipe");

	/**
	 * The method used to set the scene of Editor 
	 * and then pop the RecipeEditorView
	 */
	public void pop() {
		editPane = new StackPane();
		Scene scene = new Scene(editPane,EDITOR_WIDTH,EDITOR_HEIGHT);
		this.setup();
		editStage.setScene(scene);
		editStage.show();
	}
	
	/**
	 * Used to set up Recipe edit window
	 */
	private void setup() {
		initialize();
		BorderPane root = new BorderPane();
		editPane.getChildren().add(root);

		//upper part, contains basic information
		// Recipe info		
		GridPane infoGridPane = new GridPane();

		infoGridPane.addColumn(1, name, amount, prepTime, cookTime);
		infoGridPane.addColumn(2, nameText, amountText, prepText, cookText);

		infoGridPane.setPadding(new Insets(20));
		infoGridPane.setHgap(5);
		infoGridPane.setVgap(10);

		nameText.setEditable(true);
		amountText.setEditable(true);
		prepText.setEditable(true);
		cookText.setEditable(true);

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
		HBox ingreOperate = new HBox(addIngre, deleteIngre);
		ingreOperate.setSpacing(10);

		// Ingredient Table
		ingreScrollPane.setContent(ingredientTable);

		GridPane ingrePane = new GridPane();
		ingrePane.addColumn(0, ingreText, ingreScrollPane,ingreOperate);
		ingrePane.setPadding(new Insets(1, 20, 1, 20));
		ingrePane.setVgap(10);

		//lower part, contains preparation steps and a confirm button
		Label step = new Label("Cooking Steps:");
		VBox stepBox = new VBox(step, stepTArea);

		HBox confirmBox = new HBox(saveRecipe);
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
		saveRecipe.setOnMouseClicked(controller);
		addIngre.setOnMouseClicked(controller);
		deleteIngre.setOnMouseClicked(controller);
		addConfirm.setOnMouseClicked(controller);
		editConfirm.setOnMouseClicked(controller);
		deleteConfirm.setOnMouseClicked(controller);
		deleteCancel.setOnMouseClicked(controller);

		loadImage();
		loadIngredientTable();
		initialTags();
	}
	
	/**
	 * Used to load Image to view
	 */
	public void loadImage() {
		imageView.setImage(null);
		Image image = new Image(LOCAL_IMAGE_PREFIX + model.getPicture(), IMAGE_WIDTH, IMAGE_HEIGHT, false, false);
		imageView.setImage(image);
		imageView.setOnMouseClicked(controller);
	}

	/**
	 * Load ingredients from model to ingredient table
	 */
	public void loadIngredientTable() {
		// Add Header and constraints
		ingredientTable.getChildren().clear();
		ColumnConstraints constraints = new ColumnConstraints();
		constraints.setHalignment(HPos.CENTER);
		Button idHead = new Button("ID");
		Button nameHead = new Button("Name");
		Button amountHead = new Button("Amount");
		Button unitHead = new Button("Unit");
		Button editHead = new Button("Edit");
		Button deleteHead = new Button("Delete");
		idHead.setPrefWidth(ID_COL_WIDTH);
		nameHead.setPrefWidth(NAME_COL_WIDTH);
		amountHead.setPrefWidth(AMOUNT_COL_WIDTH);
		unitHead.setPrefWidth(UNIT_COL_WIDTH);
		editHead.setPrefWidth(EDIT_COL_WIDTH);
		deleteHead.setPrefWidth(DELETE_COL_WIDTH);
		ingredientTable.addRow(0, idHead, nameHead, amountHead, unitHead, editHead, deleteHead);
		for (int i = 0; i < TABLE_COL_NUM; i++) {
			ingredientTable.getColumnConstraints().add(i, constraints);
		}

		// Add ingredients
		List<Ingredient> ingredients = model.getIngredients();	
		idCol.clear();
		nameCol.clear();
		amountCol.clear();
		unitCol.clear();
		editCol.clear();
		deleteCol.clear();
		for (Ingredient in : ingredients) {
			idCol.add(new Text(in.getIngredientId()));
			nameCol.add(new Text(in.getName()));
			amountCol.add(new Text(Float.toString(in.getAmount())));
			unitCol.add(new Text(in.getUnit()));
			editCol.add(new Button("🖊"));
			deleteCol.add(new Button("×"));
		}
		for (int i = 0; i < ingredients.size(); i++) {
			ingredientTable.add(idCol.get(i), 0, i + TABLE_CELL_OFFSET);
			ingredientTable.add(nameCol.get(i), 1, i + TABLE_CELL_OFFSET);
			ingredientTable.add(amountCol.get(i), 2, i + TABLE_CELL_OFFSET);
			ingredientTable.add(unitCol.get(i), 3, i + TABLE_CELL_OFFSET);
			ingredientTable.add(editCol.get(i), 4, i + TABLE_CELL_OFFSET);
			ingredientTable.add(deleteCol.get(i), 5, i + TABLE_CELL_OFFSET);
		}
		for (Button btn : deleteCol) {
			btn.setOnMouseClicked(controller);
		}
		for (Button btn : editCol) {
			btn.setOnMouseClicked(controller);
		}
	}

	/**
	 * Load tags from model to tagList
	 */
	private void initialTags() {
		List<String> tagNames = model.getTags();
		for(String str : tagNames) {
			tagList.add(new Label(str));
		}
		newTagIcon.setOnMouseEntered(controller);
		newTagIcon.setOnMouseExited(controller);
		newTagIcon.setOnMouseClicked(controller);
		loadTags();
	}
	
	/**
	 * Used to load tags to view
	 */
	public void loadTags() {
		tagPane.getChildren().clear();
		for(Label label : tagList) {
			label.setFont(new Font(TAG_FONT));
			label.setPadding(new Insets(0, 5, 0, 0));
			label.setOnMouseClicked(controller);
			label.setOnMouseEntered(controller);
			label.setOnMouseExited(controller);
			tagPane.getChildren().add(label);
		}
		tagPane.getChildren().add(newTagIcon);
	}

	/**
	 * Used to get Tag List as a String
	 * @return
	 */
	public String getTagsFromLabel() {
		String tagStr = "";
		for(Label label : tagList) {
			tagStr += label.getText() + ",";
		}
		tagStr = tagStr.substring(0, tagStr.length() - 1);
		return tagStr;
	}
	// ----- Recipe Editor Main View ----- END ----- //


	// ----- Recipe Editor Sub Window View ----- START ----- //
	/**
	 * Fields of Stages
	 */
	Stage addPop;
	Stage editPop;
	Stage deletePop;
	Stage newTagPop;
	/**
	 * field of elements which used in popped window
	 */
	Label ingreName = new Label("Name");
	Label ingreAmount = new Label("Amount");
	Label ingreUnit = new Label("Unit");
	Label ingreTitle = new Label("Add Ingrements");

	TextField ingreNameText = new TextField();
	TextField ingreAmountText = new TextField();
	ComboBox<String> ingreUnitBox;{
		ObservableList<String> options = FXCollections.observableArrayList(UnitEnum.toList());
		ingreUnitBox = new ComboBox<String>(options);
	}

	Button addConfirm = new Button("Confirm");
	Button editConfirm = new Button("Confirm");
	Button newTagConfirm = new Button("Confirm");
	ComboBox<String> newTagBox;
	/**
	 * a popped window after clicking 'add ingredient' button
	 * enables user to add ingredients in the recipe
	 */
	public void addIngrePop() {
		GridPane addIngrePane = new GridPane();
		Scene addPopScene = new Scene(addIngrePane, 400, 250);
		addPop = new Stage();
		addPop.setTitle("Add Ingrements");
		addIngrePane.setPadding(new Insets(20));
		addIngrePane.setVgap(10);
		addIngrePane.setHgap(10);
		addIngrePane.addColumn(0, ingreName, ingreAmount, ingreUnit);
		addIngrePane.addColumn(1, ingreNameText, ingreAmountText, ingreUnitBox);
		addIngrePane.add(addConfirm, 1,	4);

		addPop.setScene(addPopScene);
		addPop.show();
	}

	/**
	 * a popped window after clicking 'edit ingredient' button
	 * enables user to add ingredients in the recipe
	 */
	public void editIngrePop(int index) {
		GridPane editIngrePane = new GridPane();
		Scene editPopScene = new Scene(editIngrePane, 400, 250);
		editPop = new Stage();
		editPop.setTitle("Edit Ingrements");
		editIngrePane.setPadding(new Insets(20));
		editIngrePane.setVgap(10);
		editIngrePane.setHgap(10);
		editIngrePane.addColumn(0, ingreName, ingreAmount, ingreUnit);

		ingreNameText.setText(model.getIngredients().get(index).getName());
		ingreAmountText.setText(String.valueOf(model.getIngredients().get(index).getAmount()));
		ingreUnitBox.setPromptText(model.getIngredients().get(index).getUnit());
		editIngrePane.addColumn(1, ingreNameText, ingreAmountText, ingreUnitBox);
		editIngrePane.add(editConfirm, 1, 4);


		editPop.setScene(editPopScene);
		editPop.show();
	}

	/**
	 * fields of buttons which used in delete-popped window
	 */
	Button deleteConfirm = new Button("Confirm");
	Button deleteCancel = new  Button("Cancel");

	/**
	 * a popped window after clicking 'delete ingredient' button
	 * enables user to delete selected ingredient
	 */
	public void deleteIngrePop() {
		Label hint = new Label();
		hint.setText("Do you want to delete?");

		FlowPane deletePane = new FlowPane();
		Scene deleteScene = new Scene(deletePane, 300, 170);
		deletePop = new Stage();

		HBox deleteBox = new HBox(deleteConfirm, deleteCancel);
		deleteBox.setSpacing(15);
		VBox deleteOperate = new VBox(hint, deleteBox);
		deleteOperate.setSpacing(20);
		deletePane.getChildren().add(deleteOperate);
		deletePane.setAlignment(Pos.CENTER);

		deletePop.setScene(deleteScene);
		deletePop.show();
	}

	public void newTagOptionPop() {
		newTagPop = new Stage();
		VBox content = new VBox(NEWTAG_BORDER);
		content.setAlignment(Pos.CENTER);
		content.setPadding(new Insets(NEWTAG_BORDER, NEWTAG_BORDER, NEWTAG_BORDER, NEWTAG_BORDER));
		Text title = new Text("Choose a Tag");
		title.setFont(new Font(14));
		ObservableList<String> options = FXCollections.observableArrayList(RecipeTagEnum.toList());
		newTagBox = new ComboBox<String>(options);
		
		newTagConfirm.setOnMouseClicked(controller);
		
		content.getChildren().addAll(title, newTagBox, newTagConfirm);
		Scene newTagScene = new Scene(content);
		
		newTagPop.setScene(newTagScene);
		newTagPop.show();
	}
	
	// ----- Recipe Editor Sub Window View ----- END ----- //
}
