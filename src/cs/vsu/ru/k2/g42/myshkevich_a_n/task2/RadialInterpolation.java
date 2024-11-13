package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class RadialInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx, int width,
			int height) {
		double[] result = new double[3];

		double k, b, y1 = 0, x1 = 0;

		k = (centerx != currx) ? (double) (centery - curry) / (centerx - currx) : 0;
		b = centery - k * centerx;
		x1 = centerx - offsetx;

		double height2 = height * height;
		double width2 = width * width;

		while (x1 > currx) {
			if (((x1 - centerx) * (x1 - centerx)) / height2
					+ (k * x1 + b - centery) * (k * x1 + b - centery) / width2 < 1.000000001) {
				break;
			}
			x1 -= 0.05;
		}
		y1 = k * x1 + b;

		if (centerx == currx) {
			x1 = centerx;
			y1 = centery - width;
		}

		double konst = Math.sqrt(((currx - centerx) * (currx - centerx) + (curry - centery) * (curry - centery))
				/ ((x1 - centerx) * (x1 - centerx) + (y1 - centery) * (y1 - centery)));

		result[0] = get(c0.getRed(), c1.getRed(), konst);
		result[1] = get(c0.getGreen(), c1.getGreen(), konst);
		result[2] = get(c0.getBlue(), c1.getBlue(), konst);

		return result;
	}

	@Override
	public Color[] find4Colors(Color c0, Color c1, int currx, int curry, int x0, int y0, int offsetx, int width,
			int height) {
		Color color1 = Color.color(findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[0],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[1],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[2]);
		return new Color[] { color1, color1, color1, color1 };
	}
}