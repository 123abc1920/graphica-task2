package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Oval {
	private int x;
	private int y;
	private int height;
	private int width;

	private static Color colorOvalStart = Color.rgb(121, 175, 232);
	private static Color colorOvalFinish = Color.rgb(242, 152, 250);

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
		colorOvalStart = Color.rgb(121, 175, 232);
		colorOvalFinish = Color.rgb(183, 152, 250);

		int x0 = this.x + this.width / 2;
		int y0 = this.y + this.height / 2;

		int a = this.width / 2;
		int b = this.height / 2;

		int x = -a;
		int y = 0;
		int error = a * a + b * b - 2 * a * a * b;

		double dblue = (colorOvalFinish.getBlue() - colorOvalStart.getBlue()) / height;
		double dgreen = (colorOvalFinish.getGreen() - colorOvalStart.getGreen()) / height;
		double dred = (colorOvalFinish.getRed() - colorOvalStart.getRed()) / height;

		double midblue = (colorOvalFinish.getBlue() + colorOvalStart.getBlue()) / 2;
		double midgreen = (colorOvalFinish.getGreen() + colorOvalStart.getGreen()) / 2;
		double midred = (colorOvalFinish.getRed() + colorOvalStart.getRed()) / 2;

		colorOvalFinish = colorOvalStart = Color.color(midred, midgreen, midblue);

		do {
			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 - y, colorOvalStart);
			}

			for (int i = x0 + x; i <= x0 - x; i++) {
				pixelWriter.setColor(i, y0 + y, colorOvalFinish);
			}

			if (error <= y) {
				error += ++y * 2 * a * a + 1;
			}
			if (error > x || error > y) {
				error += ++x * 2 * b * b + 1;
			}

			colorOvalStart = Color.color(Math.max(0, Math.min(colorOvalStart.getRed() - dred, 1)),
					Math.max(0, Math.min(colorOvalStart.getGreen() - dgreen, 1)),
					Math.max(0, Math.min(colorOvalStart.getBlue() - dblue, 1)));
			colorOvalFinish = Color.color(Math.max(0, Math.min(colorOvalFinish.getRed() + dred, 1)),
					Math.max(0, Math.min(colorOvalFinish.getGreen() + dgreen, 1)),
					Math.max(0, Math.min(colorOvalFinish.getBlue() + dblue, 1)));
		} while (x < 0);
	}
}