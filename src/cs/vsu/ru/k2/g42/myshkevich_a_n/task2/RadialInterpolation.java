package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class RadialInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety, int a) {
		double[] result = new double[3];

		double k, b, y1 = 0, x1 = 0;

		k = (centery - curry) / (centerx - currx + 0.000000001);
		b = centery - k * centerx;

		x1 = centerx - offsetx;

		for (; x1 > currx; x1 -= 0.05) {
			if (((x1 - centerx) * (x1 - centerx)) / (a * a)
					+ (k * x1 + b - centery) * (k * x1 + b - centery) / (offsety * offsety) - 1 < 0.000000001) {
				break;
			}
		}
		y1 = k * x1 + b;

		if (centerx == currx) {
			x1 = centerx;
			y1 = centery - offsety;
		}

		double konst = Math.sqrt(((currx - centerx) * (currx - centerx) + (curry - centery) * (curry - centery))
				/ ((x1 - centerx) * (x1 - centerx) + (y1 - centery) * (y1 - centery)));

		result[0] = get(c0.getRed(), c1.getRed(), konst);
		result[1] = get(c0.getGreen(), c1.getGreen(), konst);
		result[2] = get(c0.getBlue(), c1.getBlue(), konst);

		return result;
	}

	private double get(double c0Comp, double c1Comp, double k) {
		return Math.max(0, Math.min(1, c0Comp + (c1Comp - c0Comp) * k));
	}
}