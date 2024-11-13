package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class RadialInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety) {
		double[] result = new double[3];

		result[0] = get(c0.getRed(), c1.getRed(), currx, curry, centerx, centery, offsetx, offsety);
		result[1] = get(c0.getGreen(), c1.getGreen(), currx, curry, centerx, centery, offsetx, offsety);
		result[2] = get(c0.getBlue(), c1.getBlue(), currx, curry, centerx, centery, offsetx, offsety);

		return result;
	}

	private double get(double c0Comp, double c1Comp, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety) {
		double a = (currx - centerx)
				/ Math.sqrt((currx - centerx) * (currx - centerx) + (curry - centery) * (curry - centery));
		double l = offsetx / a;
		double y1 = Math.sqrt(l * l - offsetx * offsetx);
		y1 = centery - y1;
		if (a == 0) {
			y1 = offsety;
		}
		return Math.max(0, Math.min(1,
				c0Comp + (c1Comp - c0Comp)
						* Math.sqrt(((currx - centerx) * (currx - centerx) + (curry - centery) * (curry - centery))
								/ (((centerx - centerx - offsetx) * (centerx - centerx - offsetx)
										+ (centery - y1) * (centery - y1))))));
	}

}
