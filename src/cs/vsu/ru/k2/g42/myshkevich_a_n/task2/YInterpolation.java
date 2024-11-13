package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public class YInterpolation extends Interpolation {

	@Override
	public double[] findRgb(Color c0, Color c1, int currx, int curry, int centerx, int centery, int offsetx,
			int offsety, int a) {
		double[] result = new double[3];

		int y = curry;
		int y0 = centery + offsety;
		int y1 = centery - offsety;

		result[0] = Math.max(0, Math.min(1, c0.getRed() + (c1.getRed() - c0.getRed()) * (y - y0) / (y1 - y0)));
		result[1] = Math.max(0, Math.min(1, c0.getGreen() + (c1.getGreen() - c0.getGreen()) * (y - y0) / (y1 - y0)));
		result[2] = Math.max(0, Math.min(1, c0.getBlue() + (c1.getBlue() - c0.getBlue()) * (y - y0) / (y1 - y0)));

		return result;
	}

	@Override
	public Color[] find4Colors(Color c0, Color c1, int currx, int curry, int x0, int y0, int offsetx, int width,
			int a) {
		Color color1 = Color.color(findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, a)[0],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, a)[1],
				findRgb(c0, c1, currx, curry, x0, y0, offsetx, width, a)[2]);
		Color color2 = Color.color(c1.getRed() - color1.getRed() + c0.getRed(),
				c1.getGreen() - color1.getGreen() + c0.getGreen(), c1.getBlue() - color1.getBlue() + c0.getBlue());
		return new Color[] { color1, color1, color2, color2 };
	}

}
