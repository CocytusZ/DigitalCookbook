package com.saint.digitalCookbook;

import java.io.File;

import com.saint.digitalCookbook.constant.DefaultRecipe;
import com.saint.digitalCookbook.service.homePage.HomePageView;
import com.saint.digitalCookbook.ui.tutorial.homepage.HomePageTutorialView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Entrance of App 
 *
 */	
public class App extends Application
{
	private static StackPane root = new StackPane();
	@Override
	public void start(Stage primaryStage) throws Exception {
		DefaultRecipe.CreateRecipe();
		
		try {
			File f = new File("src/main/resources/audio/Honey.mp3");

			Media media = new Media(f.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		
		loadHomePage();
		Scene scene = new Scene(root);
		
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void loadHomePage() {
		root.getChildren().clear();
		
		root.getChildren().add(new HomePageView());
		if (HomePageTutorialView.isTutorial) {
			root.getChildren().add(new HomePageTutorialView());
		}
	}
	
	public static void main( String[] args )
	{
		Application.launch();
	}
}
