package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
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
	@FXML
	private ColorPicker color1;
	@FXML
	private ColorPicker color2;
	@FXML
	private MenuButton gradientMenu;
	@FXML
	private CheckMenuItem xGradient;
	@FXML
	private CheckMenuItem yGradient;
	@FXML
	private CheckMenuItem radialGradient;

	private int x = -1, y = -1;
	private boolean drawRect = false;
	private List<Oval> ovals = new ArrayList();

	private Color colorOvalStart = Color.rgb(121, 175, 232);
	private Color colorOvalFinish = Color.rgb(242, 152, 250);
	private static Color colorRect = Color.rgb(230, 244, 255);
	private Interpolation interpolation = new YInterpolation();

	@FXML
	private void initialize() {
		anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
		anchorPane.prefHeightProperty()
				.addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

		color1.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colorOvalStart = color1.getValue();
			}
		});

		color1.setValue(colorOvalStart);
		color2.setValue(colorOvalFinish);
		yGradient.setSelected(true);
		gradientMenu.setText("y-gradient");

		color2.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colorOvalFinish = color2.getValue();
			}
		});

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
				colorOvalStart = Color.rgb(121, 175, 232);
				colorOvalFinish = Color.rgb(242, 152, 250);
				color1.setValue(colorOvalStart);
				color2.setValue(colorOvalFinish);
				interpolation = new YInterpolation();
				yGradient.setSelected(true);
				xGradient.setSelected(false);
				radialGradient.setSelected(false);
				gradientMenu.setText("y-gradient");
				repaint();
			}
		});

		yGradient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (((CheckMenuItem) arg0.getSource()).isSelected()) {
					xGradient.setSelected(false);
					radialGradient.setSelected(false);
					gradientMenu.setText("y-gradient");
					interpolation = new YInterpolation();
				}
			}
		});

		xGradient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (((CheckMenuItem) arg0.getSource()).isSelected()) {
					yGradient.setSelected(false);
					radialGradient.setSelected(false);
					gradientMenu.setText("x-gradient");
					interpolation = new XInterpolation();
				}
			}
		});

		radialGradient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (((CheckMenuItem) arg0.getSource()).isSelected()) {
					xGradient.setSelected(false);
					yGradient.setSelected(false);
					gradientMenu.setText("radial-gradient");
					interpolation = new RadialInterpolation();
				}
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

				Oval oval = new Oval(startx, starty, Math.abs(x - (int) arg0.getX()), Math.abs(y - (int) arg0.getY()),
						colorOvalStart, colorOvalFinish, interpolation);
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