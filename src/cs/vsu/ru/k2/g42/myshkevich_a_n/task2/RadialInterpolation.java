package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class RadialInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety, int a) {
		double[] result = new double[3];

		double k, b;
		k = (centery - curry) / (centerx - currx + 0.1);
		b = centery - k * centerx;

		int x1 = centerx - offsetx;

		for (; x1 > currx; x1--) {
			if (((x1 - centerx) * (x1 - centerx)) / (a * a)
					+ (k * x1 + b - centery) * (k * x1 + b - centery) / (offsety * offsety) - 1 < 0.01) {
				break;
			}
		}

		double y1 = k * x1 + b;

		result[0] = get(c0.getRed(), c1.getRed(), currx, curry, centerx, centery, offsetx, x1, y1);
		result[1] = get(c0.getGreen(), c1.getGreen(), currx, curry, centerx, centery, offsetx, x1, y1);
		result[2] = get(c0.getBlue(), c1.getBlue(), currx, curry, centerx, centery, offsetx, x1, y1);

		return result;
	}

	private double get(double c0Comp, double c1Comp, int currx, int curry, int centerx, int centery, int offsetx,
			double x1, double y1) {

		return Math.max(0, Math.min(1,
				c0Comp + (c1Comp - c0Comp)
						* Math.sqrt(((currx - centerx) * (currx - centerx) + (curry - centery) * (curry - centery))
								/ ((x1 - centerx) * (x1 - centerx) + (y1 - centery) * (y1 - centery)))));
	}
}