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

	public Oval(int x, int y, int width, int height, Color start, Color finish) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		colorOvalStart = start;
		colorOvalFinish = finish;
	}

	public static void drawRect(GraphicsContext graphicsContext, int x, int y, int width, int height, Color color) {
		final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

		for (int row = y; row < y + height; ++row) {
			for (int col = x; col < x + width; ++col) {
				pixelWriter.setColor(col, row, color);
			}
		}
	}

	public double[] findRgb(Color c0, Color c1, int x, int y, int x0, int y0, int x1, int y1) {
		double[] result = new double[3];

		result[0] = Math.max(0,
				Math.min(1,
						c0.getRed() + (c1.getRed() - c0.getRed()) * Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0))
								/ Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0))));
		result[1] = Math.max(0,
				Math.min(1,
						c0.getGreen()
								+ (c1.getGreen() - c0.getGreen()) * Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0))
										/ Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0))));
		result[2] = Math.max(0,
				Math.min(1,
						c0.getBlue()
								+ (c1.getBlue() - c0.getBlue()) * Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0))
										/ Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0))));

		return result;
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

		Color colorOvalMid = Color.color(findRgb(colorOvalFinish, colorOvalStart, 0, y0, 0, y0 - b, 0, y0 + b)[0],
				findRgb(colorOvalFinish, colorOvalStart, 0, y0, 0, y0 - b, 0, y0 + b)[1],
				findRgb(colorOvalFinish, colorOvalStart, 0, y0, 0, y0 - b, 0, y0 + b)[2]);
		Color colorOvalMidUp = colorOvalMid;
		Color colorOvalMidDown = colorOvalMid;

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

			colorOvalMidUp = Color.color(findRgb(colorOvalMid, colorOvalStart, 0, y0 - y, 0, y0, 0, y0 - b)[0],
					findRgb(colorOvalMid, colorOvalStart, 0, y0 - y, 0, y0, 0, y0 - b)[1],
					findRgb(colorOvalMid, colorOvalStart, 0, y0 - y, 0, y0, 0, y0 - b)[2]);

			colorOvalMidDown = Color.color(findRgb(colorOvalMid, colorOvalFinish, 0, y0 + y, 0, y0, 0, y0 + b)[0],
					findRgb(colorOvalMid, colorOvalFinish, 0, y0 + y, 0, y0, 0, y0 + b)[1],
					findRgb(colorOvalMid, colorOvalFinish, 0, y0 + y, 0, y0, 0, y0 + b)[2]);
		} while (x < 0);
	}
}