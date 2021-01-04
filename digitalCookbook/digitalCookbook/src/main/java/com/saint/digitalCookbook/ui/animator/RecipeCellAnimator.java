package com.saint.digitalCookbook.ui.animator;

import java.util.List;

import com.saint.digitalCookbook.service.recipeBrowser.RecipeCellView;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * The class to handle animations of RecipeCellView
 * It is a singleton with thread unsafe
 * @author Zhouyao Yu
 */
public class RecipeCellAnimator {
	public static final float WINDOW_WIDTH = 1030.0F;
	public static final float WINDOW_HEIGHT = 560.F;
	public static final int ROW_CELL_NUM = 4;
	public static final float CELL_WIDTH_INTERVAL = 240.0F;
	public static final float CELL_HEIGHT_INTERVAL = 340.0F;
	public static final double CELL_SIZE = 200;
	public static final float DURATION = 1000;

	private static RecipeCellAnimator animator;
	private List<RecipeCellView> recipeCells;
	private ParallelTransition transitionSet;

	private RecipeCellAnimator() {}

	/**
	 * Used to get the instance of animator
	 * @param recipeCells
	 * @return
	 */
	public static RecipeCellAnimator createAnimator(List<RecipeCellView> recipeCells) {
//		if(animator == null) {
			animator = new RecipeCellAnimator();
			animator.recipeCells = recipeCells;
			animator.transitionSet = new ParallelTransition();
//		}
		return animator;
	}

	public static void playEntrance() {
		initEntranceAnnimation();
		animator.transitionSet.play();
	}  


	private static void initEntranceAnnimation() {
		for(int i = 0; i < animator.recipeCells.size(); i++) {
			RecipeCellView cell = animator.recipeCells.get(i);
			TranslateTransition tt = new TranslateTransition();
			tt.setNode(cell);
			tt.setDuration(Duration.millis(DURATION));
			tt.setFromX(cell.getTranslateX());
			tt.setFromY(cell.getTranslateY());

			int row = i / ROW_CELL_NUM;
			int col = i % ROW_CELL_NUM;
			tt.setToX(cell.getTranslateX() + col * CELL_WIDTH_INTERVAL);
			tt.setToY(cell.getTranslateY() + row * CELL_HEIGHT_INTERVAL);
			tt.setCycleCount(1);
			tt.setAutoReverse(false);
			animator.transitionSet.getChildren().add(tt);
		}

		for(int i = 0; i < animator.recipeCells.size(); i++) {
			RecipeCellView cell = animator.recipeCells.get(i);
			FadeTransition ft = new FadeTransition();
			ft.setNode(cell);
			ft.setDuration(Duration.millis(DURATION));
			ft.setFromValue(0.0F);
			ft.setToValue(1.0F);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			animator.transitionSet.getChildren().add(ft);
		}

		for(int i = 0; i < animator.recipeCells.size(); i++) {
			RecipeCellView cell = animator.recipeCells.get(i);
			ScaleTransition st = new ScaleTransition();
			st.setNode(cell);
			st.setDuration(Duration.millis(DURATION));
			st.setFromX(0.0F);
			st.setFromY(0.0F);
			st.setToX(1.0F);
			st.setToY(1.0F);
			st.setCycleCount(1);
			st.setAutoReverse(false);
			animator.transitionSet.getChildren().add(st);

		}
	}

}
