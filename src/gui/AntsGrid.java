package gui;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/*------------------------------*/
/* 0-0 1-0 2-0 3-0 4-0 5-0 .....*/
/* 0-1 1-1 2-1 3-1 4-1 5-1 .....*/
/* 0-2 1-2 2-2 3-2 4-2 5-2 .....*/
/* 0-3 1-3 2-3 3-3 4-3 5-3 .....*/
/* 0-4 1-4 2-4 3-4 4-4 5-4 .....*/
/* 0-5 1-5 2-5 3-5 4-5 5-5 .....*/
/* ....................... .....*/
/*------------------------------*/

public class AntsGrid extends GridPane {

	private static int GRID_SIZE;
	private static int ANIMATION_SPEED;

	private ArrayList<AntsCell> cells;

	private int activeIndex1;
	private int activeIndex2;

	private int turnCounter;

	private Timeline timeline;

	public AntsGrid(int gridSize, int animationSpeed) {
		
		GRID_SIZE = gridSize;
		ANIMATION_SPEED = animationSpeed;
		
		setPrefSize(10000, 10000);
		setAlignment(Pos.CENTER);

		cells = new ArrayList<>();

		// create cells
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				AntsCell cell = new AntsCell(i, j, (600 / GRID_SIZE) - 2, GRID_SIZE);
				add(cell, i, j);
				cells.add(cell);
			}
		}

		activeIndex1 = 0;
		activeIndex2 = GRID_SIZE*GRID_SIZE/2 + GRID_SIZE/2;

//		cells.get(activeIndex1).myTurnStart(AntsCell.LEFT);
		cells.get(activeIndex2).myTurnStart(AntsCell.TOP);

		turnCounter = 0;
	}

	public void startAnimation() {

		KeyFrame keyFrame = new KeyFrame(Duration.millis(ANIMATION_SPEED),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if ((++turnCounter) % 2 == 0) {
							if(turnCounter % 1000 == 0) {
								System.out.println("******* - " + turnCounter/2 + " - *******");
							}
							
//							int direction1 = cells.get(activeIndex1).getMyDirection();

							int direction2 = cells.get(activeIndex2).getMyDirection();

//							activeIndex1 = cells.get(activeIndex1).myTurnFinished();

							activeIndex2 = cells.get(activeIndex2).myTurnFinished();

							// if exceeds limits
							if ((activeIndex1 < 0)
									|| (activeIndex1 > GRID_SIZE * GRID_SIZE)) {
								System.out
										.println(turnCounter
												+ ". exceeds limits! ANT NUMBER 1");
								timeline.stop();
								return;
							}

							if ((activeIndex2 < 0)
									|| (activeIndex2 > GRID_SIZE * GRID_SIZE)) {
								System.out
										.println(turnCounter
												+ ". exceeds limits! ANT NUMBER 2");
								timeline.stop();
								return;
							}

							if (activeIndex1 == activeIndex2) {
								System.out.println("ants crashed!");
								playPauseAnimation();
							}

//							cells.get(activeIndex1).myTurnStart(direction1);
							cells.get(activeIndex2).myTurnStart(direction2);
						} else {
//							cells.get(activeIndex1).myTurnAction();
							cells.get(activeIndex2).myTurnAction();
						}
					}
				});

		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
	}

	public void playPauseAnimation() {

		System.out.println("***");
		if (timeline.getStatus() == Status.RUNNING) {
			timeline.pause();
			System.out.println("STEP " + (turnCounter / 2));
		} else if (timeline.getStatus() == Status.PAUSED) {
			timeline.play();
			System.out.println("STEP " + (turnCounter / 2));
		}
	}

}
