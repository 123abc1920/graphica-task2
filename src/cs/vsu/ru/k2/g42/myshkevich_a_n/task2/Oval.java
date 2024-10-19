package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	public static void drawOval(GraphicsContext graphicsContext, int x, int y, int width, int height, Color color) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		for (int row = y; row < y + height; ++row) {
			for (int col = x; col < x + width; ++col) {
				pixelWriter.setColor(col, row, Color.rgb(230, 244, 255));
			}
		}

		int x0 = x + width / 2;
		int y0 = y + height / 2;

		int a = width / 2;
		int b = height / 2;

		int r = width / 2;
		x = -r;
		y = 0;
		int error = a * a + b * b - 2 * a * a * b;

		do {
			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 - y, color);
			}

			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 + y, color);
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
/*
 * int r = width / 2; int x0 = x + width / 2; int y0 = y + height / 2; int error
 * = 2 - 2 * height / 2;
 * 
 * x = -r; y = 0;
 * 
 * do { pixelWriter.setColor(x0 + y, y0 + x, color); pixelWriter.setColor(x0 -
 * y, y0 - x, color); pixelWriter.setColor(x0 - x, y0 + y, color);
 * pixelWriter.setColor(x0 + x, y0 - y, color);
 * 
 * r = error; if (error <= y) { error += ++y * 2 + 1; } if (error > x || error >
 * y) { error += ++x * 2 + 1; } } while (x < 0);
 */