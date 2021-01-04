package com.saint.digitalCookbook.ui.component.helloSection;

import java.util.ArrayList;
import java.util.List;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Controller of Hello Section on Homepage
 * @author Zhouyao Yu
 */
public class HelloSectionController implements EventHandler<MouseEvent>{
	public static final int LEFT = HelloSectionView.LEFT;
	public static final int MIDDLE = HelloSectionView.MIDDLE;
	public static final int RIGHT = HelloSectionView.RIGHT;

	// Translate Transition
	private static final float TRANSLATE_DISTANCE = 250.0F;
	// Scale Transition
	private static final float SCALE_MAX = 1.0F;
	private static final float SCALE_MIN = HelloSectionView.MID_IMG_SCALE;	
	// Fade Transition
	private static final float OPACITY_MAX = 1.0F;
	private static final float OPACITY_MIN = 0.8F;
	// All Transition
	private static final int ANIMATOR_DURATION = 5000;
	private static final int DURATION = 300;
	private static final int VIEW_NUM = HelloSectionView.IMAGE_VIEW_NUM;
	private List<FadeTransition> fadeTransitions = new ArrayList<FadeTransition>();
	private List<TranslateTransition> translateTransitions = new ArrayList<TranslateTransition>();
	private List<ScaleTransition> scaleTransitions = new ArrayList<ScaleTransition>();
	private List<ParallelTransition> transitionSet = new ArrayList<ParallelTransition>();

	private HelloSectionView view;
	private HelloSectionModel model;

	public HelloSectionController(HelloSectionView view, HelloSectionModel model) {
		this.view = view;
		this.model = model;

		for (int i = 0; i < VIEW_NUM; i++) {
			fadeTransitions.add(new FadeTransition());
			translateTransitions.add(new TranslateTransition());
			scaleTransitions.add(new ScaleTransition());
		}
	}


	public void handle(MouseEvent event) {
		if (event.getSource() == view.imageViews.get(model.getIndex(LEFT)))  { rightSwipeImages(); }
		if (event.getSource() == view.imageViews.get(model.getIndex(RIGHT))) { leftSwipeImages(); }
	}


	private void leftSwipeImages() {
		leftToRight(model.getIndex(LEFT));
		moveToLeft(model.getIndex(MIDDLE));
		centerFromRight(model.getIndex(RIGHT));
		play();

		model.leftMoveImage();
		view.imageViews.get(model.getIndex(MIDDLE)).toFront();
	}

	private void rightSwipeImages() {
		rightToLeft(model.getIndex(RIGHT));
		moveToRight(model.getIndex(MIDDLE));
		centerFromLeft(model.getIndex(LEFT));
		play();

		model.rightMoveImage();
		view.imageViews.get(model.getIndex(MIDDLE)).toFront();
	}


	private void rightToLeft(int viewPos){
		setFade(viewPos, OPACITY_MIN, OPACITY_MIN);
		setScale(viewPos, SCALE_MIN, SCALE_MIN);
		setTranslate(viewPos, - 2 * TRANSLATE_DISTANCE);
	}

	private void leftToRight(int viewPos){
		setFade(viewPos, OPACITY_MIN, OPACITY_MIN);
		setScale(viewPos, SCALE_MIN, SCALE_MIN);
		setTranslate(viewPos, 2 * TRANSLATE_DISTANCE);
	}

	private void centerFromLeft(int viewPos) {
		setFade(viewPos, OPACITY_MIN, OPACITY_MAX);
		setScale(viewPos, SCALE_MIN, SCALE_MAX);
		setTranslate(viewPos, TRANSLATE_DISTANCE);
	}

	private void centerFromRight(int viewPos) {
		setFade(viewPos, OPACITY_MIN, OPACITY_MAX);
		setScale(viewPos, SCALE_MIN, SCALE_MAX);
		setTranslate(viewPos, - TRANSLATE_DISTANCE);
	}

	private void moveToLeft(int viewPos) {
		setFade(viewPos, OPACITY_MAX, OPACITY_MIN);
		setScale(viewPos, SCALE_MAX, SCALE_MIN);
		setTranslate(viewPos, - TRANSLATE_DISTANCE);
	}

	private void moveToRight(int viewPos) {
		setFade(viewPos, OPACITY_MAX, OPACITY_MIN);
		setScale(viewPos, SCALE_MAX, SCALE_MIN);
		setTranslate(viewPos, TRANSLATE_DISTANCE);
	}

	/**
	 * Set Fade Transistion for a view
	 * @param view
	 * @param from
	 * @param to
	 */
	private void setFade(int viewPos,float from, float to) {
		FadeTransition ft = fadeTransitions.get(viewPos);
		ft.setDuration(Duration.millis(DURATION));
		ft.setNode(view.imageViews.get(viewPos));
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.setAutoReverse(false);
	}

	/**
	 * Set Scale Transition for a view
	 * @param view
	 * @param from
	 * @param to
	 */
	private void setScale(int viewPos, float from, float to) {
		ScaleTransition st = scaleTransitions.get(viewPos);
		st.setDuration(Duration.millis(DURATION));
		st.setNode(view.imageViews.get(viewPos));
		st.setFromX(from);
		st.setFromY(from);
		st.setToX(to);
		st.setToY(to);
		st.setAutoReverse(false);
	}

	/**
	 * Set Translate Transition for a view
	 * @param view
	 * @param from
	 * @param to
	 */
	private void setTranslate(int viewPos, float distance) {
		TranslateTransition tt = translateTransitions.get(viewPos);
		double startX = view.imageViews.get(viewPos).getTranslateX();
		tt.setDuration(Duration.millis(DURATION));
		tt.setNode(view.imageViews.get(viewPos));
		tt.setFromX(startX);
		tt.setToX(startX + distance);
		tt.setAutoReverse(false);
	}

	/**
	 * Play all transition
	 */
	private void play() {
		for (int i = 0; i < VIEW_NUM; i++) {
			transitionSet.add(new ParallelTransition(
					fadeTransitions.get(i),
					translateTransitions.get(i),
					scaleTransitions.get(i)));
		}

		for (ParallelTransition transition : transitionSet) {
			transition.play();
		}
	}
}
