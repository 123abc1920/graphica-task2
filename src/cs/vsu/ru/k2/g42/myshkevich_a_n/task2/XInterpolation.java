package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class XInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx, int width,
			int height) {
		double[] result = new double[3];

		int x = currx;
		int x0 = centerx - width;
		int x1 = centerx + width;

		double k = (x - x0) / (double) (x1 - x0);

		result[0] = getColorComp(c0.getRed(), c1.getRed(), k);
		result[1] = getColorComp(c0.getGreen(), c1.getGreen(), k);
		result[2] = getColorComp(c0.getBlue(), c1.getBlue(), k);

		return result;
	}

	@Override
	public Color[] find4Colors(Color c0, Color c1, int currx, int curry, int x0, int y0, int offsetx, int width,
			int height) {
		Color color1 = Color.color(findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[0],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[1],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, height)[2]);
		Color color2 = Color.color(getNegativeColorComp(c0.getRed(), c1.getRed(), color1.getRed()),
				getNegativeColorComp(c0.getGreen(), c1.getGreen(), color1.getGreen()),
				getNegativeColorComp(c0.getBlue(), c1.getBlue(), color1.getBlue()));
		return new Color[] { color1, color2, color1, color2 };
	}

}
