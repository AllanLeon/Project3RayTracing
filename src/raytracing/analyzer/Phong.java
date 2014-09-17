package raytracing.analyzer;

import java.util.List;

import raytracing.model.Color;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;
import raytracing.model.scene.Light;
import raytracing.model.scene.Object;
import raytracing.model.scene.Scene;

public class Phong {

	public static Color chromaticPhong(Point p, Object o, Scene scene) {
		Color envComp = environmentalComponent(o, scene);
		Color difComp = diffuseComponent(p, o, scene);
		Color speComp = specularComponent(p, o, scene);
		double r = envComp.getR() + difComp.getR() + speComp.getR();
		double g = envComp.getG() + difComp.getG() + speComp.getG();
		double b = envComp.getB() + difComp.getB() + speComp.getB();
		Color phong = new Color(r, g, b);
		phong.checkBounds();
		return phong;
	}
	
	private static Color environmentalComponent(Object o, Scene scene) {
		Color ia = scene.getEnvironmentalLightColor();
		Color objectColor = o.getColor();
		double ka = scene.getEnvironmentalCoef();
		double r = ia.getR() * ka * objectColor.getR();
		double g = ia.getG() * ka * objectColor.getG();
		double b = ia.getB() * ka * objectColor.getB();
		return new Color(r, g, b);
	}
	
	private static Color diffuseComponent(Point p, Object o, Scene scene) {
		List<Light> lights = scene.getLights();
		Vector normal = o.getNormal(p);
		Color objectColor = o.getColor();
		double r = 0;
		double g = 0;
		double b = 0;
		for (int i = 0; i < lights.size(); i++) {
			Light light = lights.get(i);
			Color lightColor = light.getColor();
			double dotP = Vector.dotProduct(normal, light.getNormalizedDirection(p));
			if (dotP > 0) {
				double attenuationFactor = calculateAttenuationFactor(light, p);
				r += lightColor.getR()*dotP * attenuationFactor;
				g += lightColor.getG()*dotP * attenuationFactor;
				b += lightColor.getB()*dotP * attenuationFactor;
			}
		}
		r *= o.getKd() * objectColor.getR();
		g *= o.getKd() * objectColor.getG();
		b *= o.getKd() * objectColor.getB();
		return new Color(r, g, b);
	}
	
	private static Color specularComponent(Point p, Object o, Scene scene) {
		List<Light> lights = scene.getLights();
		Vector normal = o.getNormal(p);
		double vx = 0 - p.getX();
		double vy = 0 - p.getY();
		double vz = -100 - p.getZ();
		Vector obsVec = new Vector(vx, vy, vz);
		obsVec.normalize();
		
		double r = 0;
		double g = 0;
		double b = 0;
		int n = o.getN();
		for (int i = 0; i < lights.size(); i++) {
			Light light = lights.get(i);
			Color lightColor = light.getColor();
			Vector l = Vector.invert(light.getNormalizedDirection(p));
			Vector reflection = GeometricAnalyzer.perfectSpecularReflection(l, normal);
			double dotP = Vector.dotProduct(obsVec, reflection);
			if (dotP > 0) {
				double attenuationFactor = calculateAttenuationFactor(light, p);
				r += lightColor.getR()*Math.pow(dotP, n) * attenuationFactor;
				g += lightColor.getG()*Math.pow(dotP, n) * attenuationFactor;
				b += lightColor.getB()*Math.pow(dotP, n) * attenuationFactor;
			}
		}
		r *= o.getKs();
		g *= o.getKs();
		b *= o.getKs();
		return new Color(r, g, b);
	}

	private static double calculateAttenuationFactor(Light light, Point p) {
		double distance = p.getDistanceTo(light.getPosition());
		double attenuationFactor = 1 / (1 + 1 * distance + 1 * Math.pow(distance, 2));
		//return Math.min(attenuationFactor, 1);
		return 1;
	}
}
