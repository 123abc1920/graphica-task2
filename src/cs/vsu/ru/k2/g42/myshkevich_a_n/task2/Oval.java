package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	private int x;
	private int y;
	private int height;
	private int width;

	private static Color colorOval = Color.rgb(121, 175, 232);
	private static Color colorRect = Color.rgb(230, 244, 255);

	public Oval(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public static void drawRect(GraphicsContext graphicsContext, int x, int y, int width, int height, Color color) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		for (int row = y; row < y + height; ++row) {
			for (int col = x; col < x + width; ++col) {
				pixelWriter.setColor(col, row, color);
			}
		}
	}

	public void drawOval(GraphicsContext graphicsContext) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		drawRect(graphicsContext, this.x, this.y, this.width, this.height, colorRect);

		int x0 = this.x + this.width / 2;
		int y0 = this.y + this.height / 2;

		int a = this.width / 2;
		int b = this.height / 2;

		int r = this.width / 2;
		int x = -r;
		int y = 0;
		int error = a * a + b * b - 2 * a * a * b;

		do {
			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 - y, colorOval);
			}

			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 + y, colorOval);
			}

			r = error;
			if (error <= y) {
				error += ++y * 2 * a * a + 1;
			}
			if (error > x || error > y) {
				error += ++x * 2 * b * b + 1;
			}
		} while (x < 0);
	}
}