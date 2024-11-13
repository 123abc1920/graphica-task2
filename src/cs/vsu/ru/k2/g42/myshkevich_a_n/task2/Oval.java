package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	private int x;
	private int y;
	private int height;
	private int width;

	private Color colorOvalStart = Color.rgb(121, 175, 232);
	private Color colorOvalFinish = Color.rgb(242, 152, 250);

	private Interpolation interpolation;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Oval(int x, int y, int width, int height, Color start, Color finish, Interpolation interpolation) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		colorOvalStart = start;
		colorOvalFinish = finish;
		this.interpolation = interpolation;
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

		int x0 = this.x + this.width / 2;
		int y0 = this.y + this.height / 2;

		int a = this.width / 2;
		int b = this.height / 2;

		int x = -a;
		int y = 0;
		int error = a * a + b * b - 2 * a * a * b;

		do {
			for (int i = x0 - x; i >= x0; i--) {
				pixelWriter.setColor(i, y0 - y,
						Color.color(
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 - y, x0, y0, x, b, a)[0],
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 - y, x0, y0, x, b, a)[1],
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 - y, x0, y0, x, b, a)[2]));
				pixelWriter.setColor(2 * x0 - i, y0 - y, Color.color(
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 - y, x0, y0, x, b, a)[0],
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 - y, x0, y0, x, b, a)[1],
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 - y, x0, y0, x, b,
								a)[2]));
			}

			for (int i = x0 - x; i >= x0; i--) {
				pixelWriter.setColor(i, y0 + y,
						Color.color(
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 + y, x0, y0, x, b, a)[0],
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 + y, x0, y0, x, b, a)[1],
								interpolation.findRgb(colorOvalStart, colorOvalFinish, i, y0 + y, x0, y0, x, b, a)[2]));
				pixelWriter.setColor(2 * x0 - i, y0 + y, Color.color(
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 + y, x0, y0, x, b, a)[0],
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 + y, x0, y0, x, b, a)[1],
						interpolation.findRgb(colorOvalStart, colorOvalFinish, 2 * x0 - i, y0 + y, x0, y0, x, b,
								a)[2]));
			}

			if (error <= y) {
				error += ++y * 2 * a * a + 1;
			}
			if (error > x || error > y) {
				error += ++x * 2 * b * b + 1;
			}
		} while (x < 0);
	}
}