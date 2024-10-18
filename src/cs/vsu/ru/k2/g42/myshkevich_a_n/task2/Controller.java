package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {

	@FXML
	AnchorPane anchorPane;
	@FXML
	private Canvas canvas;

	@FXML
	private void initialize() {
		anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
		anchorPane.prefHeightProperty()
				.addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

		Oval.drawOval(canvas.getGraphicsContext2D(), 200, 200, 50, 70, Color.CHOCOLATE);
	}

}