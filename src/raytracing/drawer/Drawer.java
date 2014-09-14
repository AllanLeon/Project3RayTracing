package raytracing.drawer;

import java.awt.Graphics;

import raytracing.analyzer.RayAnalyzer;
import raytracing.analyzer.RayTracer;
import raytracing.data.DataConstants;
import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.scene.Scene;

public class Drawer {
	
	public static void putPixel(Graphics g, int x, int y, Color color) {
		g.setColor(new java.awt.Color(
				(float) color.getR(), (float) color.getG(), (float) color.getB()));
		g.drawLine(x, DataConstants.WINDOW_HEIGHT - y, x, DataConstants.WINDOW_HEIGHT - y);
	}
	
	public static void drawScene(Graphics g, Scene scene) {
		RayTracer rayTracer = new RayTracer(scene);
		for (int y = 0; y <= DataConstants.WINDOW_HEIGHT; y++) {
			for (int x = 0; x <= DataConstants.WINDOW_WIDTH; x++) {
				Ray ray = RayAnalyzer.createPixelRay(x, y);
				Color color = rayTracer.rayTracing(ray, 3);
				putPixel(g, x, y, color);
			}
		}
	}
}
