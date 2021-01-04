package com.saint.digitalCookbook.ui.component.helloSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * Model of Hello section on Home page
 * Used to load some images
 * @author Zhouyao Yu
 */
public class HelloSectionModel {
	private static final int LEFT = HelloSectionController.LEFT;
	private static final int MIDDLE = HelloSectionController.MIDDLE;
	private static final int RIGHT = HelloSectionController.RIGHT;
	private static final int FIRST_ITEM_INDEX = 0;
	
	private static final String DICTIONARY_PATH = "src/main/resources/image/system/hello"; 
	private List<Image> images = new ArrayList<Image>();
	private List<Integer> indexs = new ArrayList<Integer>();{
		indexs.add(LEFT);
		indexs.add(MIDDLE);
		indexs.add(RIGHT);
	}

	public HelloSectionModel() {
		try {
			File file = new File(DICTIONARY_PATH);
			if (!file.exists()) {
				throw new Exception();
			}

			File[] imgSet = file.listFiles();
			for (File imgFile : imgSet) {
				Image image = new Image(imgFile.toURI().toURL().toString(),
						HelloSectionView.IMAGE_WIDTH,
						HelloSectionView.IMAGE_HEIGHT,
						false,
						false);
				images.add(image);
			}
		} catch (Exception e) {
			System.out.println("Loading HelloPicture Failed");
			e.printStackTrace();
		}
	}

	public void leftMoveImage() {
		List<Image> newImages = new ArrayList<Image>();
		List<Integer> newIndexs = new ArrayList<Integer>();
		for(int i = 1; i < images.size(); i++) {
			newImages.add(images.get(i));
			newIndexs.add(indexs.get(i));
		}
		newImages.add(images.get(FIRST_ITEM_INDEX));
		newIndexs.add(indexs.get(FIRST_ITEM_INDEX));
		this.images = newImages;
		this.indexs = newIndexs;
	}

	public void rightMoveImage() {
		List<Image> newImages = new ArrayList<Image>();
		List<Integer> newIndexs = new ArrayList<Integer>();
		newImages.add(images.get(images.size() - 1));
		newIndexs.add(indexs.get(indexs.size() - 1));
		for(int i = 0; i < images.size() - 1; i++) {
			newImages.add(images.get(i));
			newIndexs.add(indexs.get(i));
		}
		this.images = newImages;
		this.indexs = newIndexs;
	}

	public Image getImage(int index) {
		return this.images.get(index);
	}
	
	public int getIndex(int index) {
		return indexs.get(index);
	}

}
