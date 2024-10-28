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

	public double[] findRgb(Color c0, Color c1, int y, int b) {
		double[] result = new double[3];

		result[0] = c0.getBlue() + (colorOvalStart.getBlue() - c0.getBlue()) * y / b;
		result[1] = c0.getRed() + (colorOvalStart.getRed() - c0.getRed()) * y / b;
		result[2] = c0.getRed() + (colorOvalStart.getRed() - c0.getRed()) * y / b;

		return result;
	}

	public void drawOval(GraphicsContext graphicsContext) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
		colorOvalStart = Color.rgb(121, 175, 232);
		colorOvalFinish = Color.rgb(239, 135, 255);

		int x0 = this.x + this.width / 2;
		int y0 = this.y + this.height / 2;

		int a = this.width / 2;
		int b = this.height / 2;

		int x = -a;
		int y = 0;
		int error = a * a + b * b - 2 * a * a * b;

		Color colorOvalMidUp = colorOvalStart;
		Color colorOvalMidDown = colorOvalFinish;

		double mblue = colorOvalFinish.getBlue() + (colorOvalStart.getBlue() - colorOvalFinish.getBlue()) * 0.5;
		double mred = colorOvalFinish.getRed() + (colorOvalStart.getRed() - colorOvalFinish.getRed()) * 0.5;
		double mgreen = colorOvalFinish.getRed() + (colorOvalStart.getRed() - colorOvalFinish.getRed()) * 0.5;

		Color colorOvalMid = Color.color(mred, mgreen, mblue);

		double blue = colorOvalMid.getBlue() + (colorOvalStart.getBlue() - colorOvalMid.getBlue()) * y / b;
		double red = colorOvalMid.getRed() + (colorOvalStart.getRed() - colorOvalMid.getRed()) * y / b;
		double green = colorOvalMid.getRed() + (colorOvalStart.getRed() - colorOvalMid.getRed()) * y / b;

		colorOvalMidUp = Color.color(red, green, blue);

		blue = colorOvalMid.getBlue() + (colorOvalFinish.getBlue() - colorOvalMid.getBlue()) * y / b;
		red = colorOvalMid.getRed() + (colorOvalFinish.getRed() - colorOvalMid.getRed()) * y / b;
		green = colorOvalMid.getRed() + (colorOvalFinish.getRed() - colorOvalMid.getRed()) * y / b;

		colorOvalMidDown = Color.color(red, green, blue);

		do {
			for (int i = x0 - x; i >= x0 + x; i--) {
				pixelWriter.setColor(i, y0 - y, colorOvalMidUp);
			}

			for (int i = x0 - x; i >= x0 + x; i--) {
				pixelWriter.setColor(i, y0 + y, colorOvalMidDown);
			}

			if (error <= y) {
				error += ++y * 2 * a * a + 1;
			}
			if (error > x || error > y) {
				error += ++x * 2 * b * b + 1;
			}

			blue = colorOvalMid.getBlue() + (colorOvalStart.getBlue() - colorOvalMid.getBlue()) * y / b;
			red = colorOvalMid.getRed() + (colorOvalStart.getRed() - colorOvalMid.getRed()) * y / b;
			green = colorOvalMid.getRed() + (colorOvalStart.getRed() - colorOvalMid.getRed()) * y / b;

			colorOvalMidUp = Color.color(red, green, blue);

			blue = colorOvalMid.getBlue() + (colorOvalFinish.getBlue() - colorOvalMid.getBlue()) * y / b;
			red = colorOvalMid.getRed() + (colorOvalFinish.getRed() - colorOvalMid.getRed()) * y / b;
			green = colorOvalMid.getRed() + (colorOvalFinish.getRed() - colorOvalMid.getRed()) * y / b;

			colorOvalMidDown = Color.color(red, green, blue);
		} while (x < 0);
	}
}