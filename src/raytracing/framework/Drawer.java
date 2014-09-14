package raytracing.framework;

import java.awt.Graphics;

public class Drawer {
	
	public static void putPixel(Graphics g, int x, int y) {
		g.drawLine(x, y, x, y);
	}
}
