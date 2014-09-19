package raytracing.analyzer;

import java.util.List;

import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;
import raytracing.model.scene.Light;
import raytracing.model.scene.Object;
import raytracing.model.scene.Scene;

public class Phong {

	public static Color chromaticPhong(Point intersectionPoint, Object oMin, Scene scene) {
		Color envComp = environmentalComponent(oMin, scene);
		Color difComp = diffuseComponent(intersectionPoint, oMin, scene);
		Color speComp = specularComponent(intersectionPoint, oMin, scene);
		double r = envComp.getR() + difComp.getR() + speComp.getR();
		double g = envComp.getG() + difComp.getG() + speComp.getG();
		double b = envComp.getB() + difComp.getB() + speComp.getB();
		Color phong = new Color(r, g, b);
		phong.checkBounds();
		return phong;
	}
	
	public static Color environmentalComponent(Object oMin, Scene scene) {
		Color ia = scene.getEnvironmentalLightColor();
		Color objectColor = oMin.getColor();
		double ka = scene.getEnvironmentalCoef();
		double r = ia.getR() * ka * objectColor.getR();
		double g = ia.getG() * ka * objectColor.getG();
		double b = ia.getB() * ka * objectColor.getB();
		return new Color(r, g, b);
	}
	
	private static Color diffuseComponent(Point intersectionPoint, Object oMin, Scene scene) {
		List<Light> lights = scene.getLights();
		Vector normal = oMin.getNormal(intersectionPoint);
		Color objectColor = oMin.getColor();
		double r = 0;
		double g = 0;
		double b = 0;
		for (int i = 0; i < lights.size(); i++) {
			Light light = lights.get(i);
			Color lightColor = light.getColor();
			double dotP = Vector.dotProduct(normal, light.getNormalizedDirection(intersectionPoint));
			if (dotP > 0) {
				if (!checkShadowRayIntersection(light, intersectionPoint, scene, oMin)) {
					double attenuationFactor = calculateAttenuationFactor(light, intersectionPoint);
					r += lightColor.getR()*dotP * attenuationFactor;
					g += lightColor.getG()*dotP * attenuationFactor;
					b += lightColor.getB()*dotP * attenuationFactor;
				}
			}
		}
		r *= oMin.getKd() * objectColor.getR();
		g *= oMin.getKd() * objectColor.getG();
		b *= oMin.getKd() * objectColor.getB();
		return new Color(r, g, b);
	}
	
	private static Color specularComponent(Point intersectionPoint, Object oMin, Scene scene) {
		List<Light> lights = scene.getLights();
		Vector normal = oMin.getNormal(intersectionPoint);
		double vx = 0 - intersectionPoint.getX();
		double vy = 0 - intersectionPoint.getY();
		double vz = -100 - intersectionPoint.getZ();
		Vector obsVec = new Vector(vx, vy, vz);
		obsVec.normalize();
		
		double r = 0;
		double g = 0;
		double b = 0;
		int n = oMin.getN();
		for (int i = 0; i < lights.size(); i++) {
			Light light = lights.get(i);
			Color lightColor = light.getColor();
			Vector l = Vector.invert(light.getNormalizedDirection(intersectionPoint));
			Vector reflection = GeometricAnalyzer.perfectSpecularReflection(l, normal);
			double dotP = Vector.dotProduct(obsVec, reflection);
			if (dotP > 0) {
				if (!checkShadowRayIntersection(light, intersectionPoint, scene, oMin)) {
					double attenuationFactor = calculateAttenuationFactor(light, intersectionPoint);
					r += lightColor.getR()*Math.pow(dotP, n) * attenuationFactor;
					g += lightColor.getG()*Math.pow(dotP, n) * attenuationFactor;
					b += lightColor.getB()*Math.pow(dotP, n) * attenuationFactor;
				}
			}
		}
		r *= oMin.getKs();
		g *= oMin.getKs();
		b *= oMin.getKs();
		return new Color(r, g, b);
	}

	private static double calculateAttenuationFactor(Light light, Point p) {
		double distance = p.getDistanceTo(light.getPosition());
		double attenuationFactor = 1 / (0.005 + 0.001 * distance + 0.000001 * Math.pow(distance, 2));
		return Math.min(attenuationFactor, 1);
	}
	
	private static boolean checkShadowRayIntersection(Light light, Point intersectionPoint, Scene scene, Object oMin) {
		for (int j = 0; j < scene.getObjects().size(); j++) {
			Object object = scene.getObjects().get(j);
			if (oMin != object) {
				Vector direction = light.getDirection(intersectionPoint);
				Ray shadowRay = new Ray(intersectionPoint, direction);
				double t = object.checkIntersection(shadowRay);
				if (t >= 0 && t <= 1) {
					return true;
				}
			}
		}
		return false;
	}
}
