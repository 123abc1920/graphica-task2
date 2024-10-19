package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {

	@FXML
	AnchorPane anchorPane;
	@FXML
	private Canvas canvas;
	@FXML
	private Button clearBtn;
	@FXML
	private Button rectBtn;

	private int x = -1, y = -1;
	private boolean drawRect = false;
	private static Color colorRect = Color.rgb(230, 244, 255);
	private List<Oval> ovals = new ArrayList();

	@FXML
	private void initialize() {
		anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
		anchorPane.prefHeightProperty()
				.addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

		rectBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				drawRect = !drawRect;
				repaint();
			}
		});

		clearBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ovals.clear();
				repaint();
			}
		});

		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				x = (int) arg0.getX();
				y = (int) arg0.getY();
			}
		});

		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				repaint();
				int startx = findStartCoord(x, (int) arg0.getX());
				int starty = findStartCoord(y, (int) arg0.getY());

				Oval.drawRect(canvas.getGraphicsContext2D(), startx, starty, (int) Math.abs(arg0.getX() - x),
						(int) Math.abs(arg0.getY() - y), Color.rgb(230, 244, 255));
			}
		});

		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int startx = findStartCoord(x, (int) arg0.getX());
				int starty = findStartCoord(y, (int) arg0.getY());

				Oval oval = new Oval(startx, starty, Math.abs(x - (int) arg0.getX()), Math.abs(y - (int) arg0.getY()));
				ovals.add(oval);
				repaint();
			}
		});
	}

	private int findStartCoord(int x, int x0) {
		int start = x0;
		if (x < x0) {
			start = x;
		}
		return start;
	}

	private void repaint() {
		Oval.drawRect(canvas.getGraphicsContext2D(), 0, 0, (int) canvas.getWidth() + 1, (int) canvas.getHeight() + 1,
				Color.WHITE);

		for (Oval oval : ovals) {
			if (drawRect) {
				Oval.drawRect(canvas.getGraphicsContext2D(), oval.getX(), oval.getY(), oval.getWidth(),
						oval.getHeight(), colorRect);
			}
			oval.drawOval(canvas.getGraphicsContext2D());
		}
	}

}