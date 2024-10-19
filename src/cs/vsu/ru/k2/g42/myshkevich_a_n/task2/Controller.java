package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {

	@FXML
	AnchorPane anchorPane;
	@FXML
	private Canvas canvas;

	private int x, y;

	private List<Oval> ovals = new ArrayList();

	@FXML
	private void initialize() {
		anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
		anchorPane.prefHeightProperty()
				.addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

		// Oval.drawOval(canvas.getGraphicsContext2D(), 200, 200, 50, 70);

		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				x = (int) arg0.getX();
				y = (int) arg0.getY();
			}
		});

		canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				repaint();
			}
		});

		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int x0 = (int) arg0.getX();
				int y0 = (int) arg0.getY();
				int startx = x0, starty = y0;
				if (x < x0) {
					startx = x;
				}
				if (y < y0) {
					starty = y;
				}

				int height = Math.abs(y - y0);
				int width = Math.abs(x - x0);

				Oval oval = new Oval(startx, starty, width, height);
				ovals.add(oval);
				oval.drawOval(canvas.getGraphicsContext2D());
			}
		});
	}

	private void repaint() {
		Oval.drawRect(canvas.getGraphicsContext2D(), 0, 0, (int) canvas.getWidth() + 1, (int) canvas.getHeight() + 1,
				Color.WHITE);

		for (Oval oval : ovals) {
			oval.drawOval(canvas.getGraphicsContext2D());
		}
	}

}