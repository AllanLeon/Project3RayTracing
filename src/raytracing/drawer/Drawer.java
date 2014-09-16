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
		g.drawLine(x, WindowConstants.HEIGHT - y, x, WindowConstants.HEIGHT - y);
	}
	
	public static void drawScene(Graphics g, Scene scene) {
		RayTracer tracer = new RayTracer(scene);
		for (int y = 0; y <= WindowConstants.HEIGHT; y++) {
			for (int x = 0; x <= WindowConstants.WIDTH; x++) {
				Ray ray = tracer.createPixelRay(x, y);
				Color color = tracer.rayTracing(ray, 3);
				putPixel(g, x, y, color);
			}
		}
		
		for (int a = 0; a <= scene.getLights().size(); a++) {
			//for (int b = 0; b <= scene.getObjects().get(b); x++) {			
				//Ray ray = tracer.createShadowRay(intersectionPoint, lightEmit);
			//	Color color = tracer.shadowRayTracing(ray, 3);
		//		putPixel(g, x, y, color);
		//	}
		}

	}
}
