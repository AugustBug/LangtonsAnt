package main;

// A Langton's Ant Simulation

import java.util.ArrayList;

import gui.AntsGrid;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ApplicationMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private AntsGrid grid;
	private ArrayList<String> cellCounts;
	private ArrayList<String> animationSpeeds;
	private int indCell;
	private int indAnim;
	
	private TextField txtCellCount;
	private TextField txtAnimationSpeed;
	private Button btnCellUp;
	private Button btnCellDown;
	private Button btnAnimUp;
	private Button btnAnimDown;

	@Override
	public void start(final Stage primaryStage) {
		
		primaryStage.setTitle("A H M E r T ' S    A N T");
		
		indCell = 4;
		cellCounts = new ArrayList<>();
		cellCounts.add("20");
		cellCounts.add("30");
		cellCounts.add("40");
		cellCounts.add("50");
		cellCounts.add("60");
		cellCounts.add("100");
		cellCounts.add("120");
		cellCounts.add("150");
		
		indAnim = 3;
		animationSpeeds = new ArrayList<>();
		animationSpeeds.add("20");
		animationSpeeds.add("30");
		animationSpeeds.add("40");
		animationSpeeds.add("50");
		animationSpeeds.add("100");
		animationSpeeds.add("200");
		animationSpeeds.add("300");
		animationSpeeds.add("500");
		animationSpeeds.add("750");
		animationSpeeds.add("1000");
		animationSpeeds.add("1500");
		animationSpeeds.add("2000");
		
		GridPane gpMain = new GridPane();
		gpMain.setPrefSize(10000, 10000);
		gpMain.setHgap(20);
		gpMain.setVgap(20);
		gpMain.setAlignment(Pos.CENTER);
		Label lblCellCount = new Label("Cell Count (20 - 150)");
		Label lblAnimationSpeed = new Label("Animation Speed (20 - 2000)");
		txtCellCount = new TextField();
		txtCellCount.setEditable(false);
		txtCellCount.setText(cellCounts.get(indCell));
		txtAnimationSpeed = new TextField();
		txtAnimationSpeed.setEditable(false);
		txtAnimationSpeed.setText(animationSpeeds.get(indAnim));
		
		btnCellUp = new Button("+");
		btnCellUp.setPrefSize(30, 30);
		btnCellDown = new Button("-");
		btnCellDown.setPrefSize(30, 30);
		btnAnimUp = new Button("+");
		btnAnimUp.setPrefSize(30, 30);
		btnAnimDown = new Button("-");
		btnAnimDown.setPrefSize(30, 30);
		
		btnCellUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (indCell < cellCounts.size() - 1) {
					indCell++;
					txtCellCount.setText(cellCounts.get(indCell));
				}
			}
		});

		btnCellDown.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (indCell > 0) {
					indCell--;
					txtCellCount.setText(cellCounts.get(indCell));
				}
			}
		});

		btnAnimUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (indAnim < animationSpeeds.size() - 1) {
					indAnim++;
					txtAnimationSpeed.setText(animationSpeeds.get(indAnim));
				}
			}
		});

		btnAnimDown.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (indAnim > 0) {
					indAnim--;
					txtAnimationSpeed.setText(animationSpeeds.get(indAnim));
				}
			}
		});
		
		Button btnStart = new Button("Start");
		btnStart.setPrefSize(120, 40);
		
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				grid = new AntsGrid(Integer.parseInt(txtCellCount.getText()), Integer.parseInt(txtAnimationSpeed.getText()));

				Scene scene = new Scene(grid, 600, 600);
				scene.setFill(Color.ALICEBLUE);
				primaryStage.setScene(scene);
				
				grid.startAnimation();
				
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent key) {

						if (key.getCode() == KeyCode.SPACE) {
							grid.playPauseAnimation();
						}

					}
				});
			}
		});
		
		gpMain.add(lblCellCount, 1, 1);
		gpMain.add(lblAnimationSpeed, 1, 2);
		gpMain.add(txtCellCount, 2, 1);
		gpMain.add(txtAnimationSpeed, 2, 2);
		gpMain.add(btnCellDown, 3, 1);
		gpMain.add(btnCellUp, 4, 1);
		gpMain.add(btnAnimDown, 3, 2);
		gpMain.add(btnAnimUp, 4, 2);
		gpMain.add(btnStart, 5, 5);
		
		Scene firstScene = new Scene(gpMain, 600, 600);
		firstScene.setFill(Color.ALICEBLUE);
		primaryStage.setScene(firstScene);
		primaryStage.show();
	}
}