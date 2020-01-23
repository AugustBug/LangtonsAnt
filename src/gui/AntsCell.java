package gui;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AntsCell extends HBox {

	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;
	public static final int LEFT = 4;

	private static HashMap<Integer, Integer> clockwiseMap;
	private static HashMap<Integer, Integer> counterClockwiseMap;

	private ImageView ivAnt;
	private Image imgAntLeft;
	private Image imgAntRight;
	private Image imgAntTop;
	private Image imgAntBottom;

	private boolean isDark;
	private int direction;

	private int myIndexX;
	private int myIndexY;
	private int gridSize;

	public AntsCell(int x, int y, int imageSize, int gSize) {
		setPrefSize(10000, 10000);
		setStyle("-fx-background-color: white; -fx-border-width: 1;"
				+ "-fx-border-color: black; -fx-border-radius: 3;"
				+ "-fx-background-radius: 3;");

		clockwiseMap = new HashMap<>();
		clockwiseMap.put(TOP, RIGHT);
		clockwiseMap.put(RIGHT, BOTTOM);
		clockwiseMap.put(BOTTOM, LEFT);
		clockwiseMap.put(LEFT, TOP);

		counterClockwiseMap = new HashMap<>();
		counterClockwiseMap.put(TOP, LEFT);
		counterClockwiseMap.put(LEFT, BOTTOM);
		counterClockwiseMap.put(BOTTOM, RIGHT);
		counterClockwiseMap.put(RIGHT, TOP);

		isDark = false;
		direction = TOP;
		myIndexX = x;
		myIndexY = y;
		gridSize = gSize;

		ivAnt = new ImageView();
		ivAnt.setPreserveRatio(true);
		ivAnt.setFitHeight(imageSize);

		imgAntLeft = new Image("antleft.png");
		imgAntRight = new Image("antright.png");
		imgAntTop = new Image("anttop.png");
		imgAntBottom = new Image("antbottom.png");

		getChildren().add(ivAnt);
	}

	// start with ant's direction
	public void myTurnStart(int dir) {
		turnAntToDirection(dir);
	}

	public void myTurnAction() {

		// turn ant
		if (isDark) {
			turnAntToDirection(counterClockwiseMap.get(direction));
		} else {
			turnAntToDirection(clockwiseMap.get(direction));
		}

		// invert color
		if (isDark) {
			isDark = false;
			setStyle("-fx-background-color: white;");
		} else {
			isDark = true;
			setStyle("-fx-background-color: silver;");
		}
	}

	// returns index of the next cell when animation finishes 
	public int myTurnFinished() {

		// removes ant
		ivAnt.setImage(null);

		switch (direction) {
		case LEFT:
			return myIndexY + gridSize * (myIndexX - 1);

		case TOP:
			return (myIndexY - 1) + gridSize * myIndexX;

		case RIGHT:
			return myIndexY + gridSize * (myIndexX + 1);

		case BOTTOM:
			return (myIndexY + 1) + gridSize * myIndexX;

		default:
			return -1;
		}
	}

	// turns ant to direction
	private void turnAntToDirection(int dir) {
		switch (dir) {
		case TOP:
			direction = TOP;
			ivAnt.setImage(imgAntTop);
			break;

		case LEFT:
			direction = LEFT;
			ivAnt.setImage(imgAntLeft);
			break;

		case BOTTOM:
			direction = BOTTOM;
			ivAnt.setImage(imgAntBottom);
			break;

		case RIGHT:
			direction = RIGHT;
			ivAnt.setImage(imgAntRight);
			break;

		default:
			break;
		}
	}

	public int getMyDirection() {
		return direction;
	}

}
