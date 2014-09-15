package raytracing.drawer;

import java.awt.Graphics;

import raytracing.analyzer.RayTracer;
import raytracing.data.WindowConstants;
import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.scene.Scene;

public class Drawer {
	
	public static void putPixel(Graphics g, int x, int y, Color color) {
		g.setColor(new java.awt.Color(
				(float) color.getR(), (float) color.getG(), (float) color.getB()));
		g.drawLine(x, WindowConstants.PANEL_HEIGHT - y, x, WindowConstants.PANEL_HEIGHT - y);
	}
	
	public static void drawScene(Graphics g, Scene scene) {
		RayTracer tracer = new RayTracer(scene);
		for (int y = 0; y <= WindowConstants.PANEL_HEIGHT; y++) {
			for (int x = 0; x <= WindowConstants.PANEL_WIDTH; x++) {
				Ray ray = tracer.createPixelRay(x, y);
				Color color = tracer.rayTracing(ray, 3);
				putPixel(g, x, y, color);
			}
		}
	}
}
