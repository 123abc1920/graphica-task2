package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	public static void drawOval(GraphicsContext graphicsContext, int x, int y, int width, int height, Color color) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		int x0 = x + width / 2;
		int y0 = y + height / 2;

		pixelWriter.setColor(x0, y0, color);
		int it = 0;

		int r = width / 2;
		x = -r;
		y = 0;
		int error = 2 - 2 * r;
		do {
			pixelWriter.setColor(x0 + x, y0 - y, color);
			pixelWriter.setColor(x0 + x, y0 + y, color);

			pixelWriter.setColor(x0 - x, y0 - y, color);
			pixelWriter.setColor(x0 - x, y0 + y, color);

			r = error;
			it++;
			if (error <= y) {
				if (height < width) {
					if (it % (height / width + 2) == 0) {
						y++;
					}
				} else {
					y++;
				}
				error += y * 2 + 1;
			}
			if (error > x || error > y) {
				if (height > width) {
					if (it % (height / width + 2) == 0) {
						x++;
					}
				} else {
					x++;
				}
				error += x * 2 + 1;
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