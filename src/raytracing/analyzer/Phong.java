package raytracing.analyzer;

import java.util.List;

import raytracing.model.Color;
import raytracing.model.Light;
import raytracing.model.Point;
import raytracing.model.Scene;
import raytracing.model.Vector;
import raytracing.model.Object;

public class Phong {

	public static Color chromaticPhong(Point p, Object o, Scene scene) {
		
	}
	
	private static Color environmentalComponent(Scene scene) {
		Color ia = scene.getEnvironmentalLightColor();
		double ka = scene.getEnvironmentalCoef();
		double r = ia.getR() * ka;
		double g = ia.getG() * ka;
		double b = ia.getB() * ka;
		return new Color(r, g, b);
	}
	
	private static Color diffuseComponent(Point p, Object o, Scene scene) {
		List<Light> lights = scene.getLights();
		Vector n = o.getNormal(p);
		double r = 0;
		double g = 0;
		double b = 0;
		for (int i = 0; i < lights.size(); i++) {
			Color lightColor = lights.get(i).getColor();
			double dotP = Vector.dotProduct(n, lights.get(i).getDirection(p));
			r += lightColor.getR()*dotP;
			g += lightColor.getG()*dotP;
			b += lightColor.getB()*dotP;
		}
		r *= o.getKd();
		g *= o.getKd();
		b *= o.getKd();
		Color color = new Color(r, g, b);
		color.checkBounds();
		return color;
	}
}
