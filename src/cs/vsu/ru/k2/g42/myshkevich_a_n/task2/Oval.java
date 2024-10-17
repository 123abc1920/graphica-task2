package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	public static void drawOval(GraphicsContext graphicsContext, int x, int y, int width, int height, Color color) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		for (int row = y; row < y + height; ++row)
			for (int col = x; col < x + width; ++col)
				pixelWriter.setColor(col, row, Color.AQUA);

		int x0 = x + width / 2;
		int y0 = y + height / 2;

		double a = width / 2;
		double b = height / 2;

		int r = width / 2;
		x = -r;
		y = 0;
		int error = 2 - 2 * r;
		int it = 0;
		do {
			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 - y, color);
			}

			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 + y, color);
			}
			it++;

			r = error;
			if (error <= y) {
				if (it % (width / height) == 0) {
					y++;
				}
				error += y * 2 + 1;
			}
			if (error > x || error > y) {
				error += ++x * 2 + 1;
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