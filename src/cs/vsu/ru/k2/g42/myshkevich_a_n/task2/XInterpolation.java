package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class XInterpolation extends Interpolation {

	@Override
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

}
