package cs.vsu.ru.k2.g42.myshkevich_a_n.task2;

import javafx.scene.paint.Color;

public abstract class Interpolation {
	
	public abstract Color[] find4Colors(Color c0, Color c1, int x, int y, int x0, int y0, int x1, int y1, int a);

	public abstract double[] findRgb(Color c0, Color c1, int x, int y, int x0, int y0, int x1, int y1, int a);
}
