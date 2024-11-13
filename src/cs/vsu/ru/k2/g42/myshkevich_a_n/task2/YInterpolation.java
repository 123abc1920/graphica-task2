package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class YInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety) {
		double[] result = new double[3];

		int y = curry;
		int y0 = centery + offsety;
		int y1 = centery - offsety;

		result[0] = Math.max(0, Math.min(1, c0.getRed() + (c1.getRed() - c0.getRed()) * (y - y0) / (y1 - y0)));
		result[1] = Math.max(0, Math.min(1, c0.getGreen() + (c1.getGreen() - c0.getGreen()) * (y - y0) / (y1 - y0)));
		result[2] = Math.max(0, Math.min(1, c0.getBlue() + (c1.getBlue() - c0.getBlue()) * (y - y0) / (y1 - y0)));

		return result;
	}

}
