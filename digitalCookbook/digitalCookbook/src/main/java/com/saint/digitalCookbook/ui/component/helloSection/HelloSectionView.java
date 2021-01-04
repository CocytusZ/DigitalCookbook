package com.saint.digitalCookbook.ui.component.helloSection;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * View of hello section on home page
 * Used to decorate the home page
 * @author Zhouyao Yu
 */
public class HelloSectionView extends Pane{
	public static final double SCENE_WIDTH = 1030;
	public static final double SCENE_HEIGHT = 300;
	public static final double IMAGE_WIDTH = 500;
	public static final double IMAGE_HEIGHT = 250;
	public static final int IMAGE_VIEW_NUM = 3;
	public static final int LEFT = 0;
	public static final int MIDDLE = 1;
	public static final int RIGHT = 2;
	public static final float MID_IMG_SCALE = 0.8F;
	public static final float LEFT_IMG_POSX = 15.0F;
	public static final float MIDDLE_IMG_POSX = 265.0F;
	public static final float RIGHT_IMG_POSX = 565.0F;
	public static final float POSY = 25.0F;
	
	HelloSectionModel model = new HelloSectionModel();
	HelloSectionController controller = new HelloSectionController(this, model);

	// All pictures
	 List<ImageView> imageViews = new ArrayList<ImageView>();{
		imageViews.add(new ImageView()); // Left
		imageViews.add(new ImageView()); // Middle
		imageViews.add(new ImageView()); // Right
		for (ImageView v : imageViews) {
			this.getChildren().add(v);
			v.setOnMouseClicked(controller);
		}
	}
	
	 /**
	  * 1. Constructor
	  */
	public HelloSectionView() {
		this.setMinSize(SCENE_WIDTH, SCENE_HEIGHT);
		this.setLayoutX(0);
		this.setLayoutY(0);
		loadImageViews();
	}


	/**
	 * Used to load Image to image views
	 */
	public void loadImageViews() {
		imageViews.clear();
		this.getChildren().clear();
		
		ImageView imageView;
		imageView = new ImageView(model.getImage(LEFT));
		imageView.setScaleX(0.8);
		imageView.setScaleY(0.8);
		imageView.setX(LEFT_IMG_POSX);
		imageView.setY(POSY);
		imageViews.add(imageView);
		
		imageView = new ImageView(model.getImage(MIDDLE));
		imageView.setX(MIDDLE_IMG_POSX);
		imageView.setY(POSY);
		imageViews.add(imageView);
		
		imageView = new ImageView(model.getImage(RIGHT));
		imageView.setScaleX(0.8);
		imageView.setScaleY(0.8);
		imageView.setX(RIGHT_IMG_POSX);
		imageView.setY(POSY);
		imageViews.add(imageView);
		
		for (ImageView v : imageViews) {
			this.getChildren().add(v);
			v.setOnMouseClicked(controller);
		}
		imageViews.get(model.getIndex(MIDDLE)).toFront();
	}
	



}
