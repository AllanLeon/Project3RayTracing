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
			Color lightColor = lights.get(i).getColor();
			double dotP = Vector.dotProduct(normal, lights.get(i).getDirection(p));
			r += lightColor.getR()*dotP;
			g += lightColor.getG()*dotP;
			b += lightColor.getB()*dotP;
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
		double vz = 0 - p.getZ();
		Vector obsVec = new Vector(vx, vy, vz);
		obsVec.normalize();
		
		double r = 0;
		double g = 0;
		double b = 0;
		int n = o.getN();
		for (int i = 0; i < lights.size(); i++) {
			Color lightColor = lights.get(i).getColor();
			Vector l = Vector.invert(lights.get(i).getDirection(p));
			Vector reflection = RayAnalyzer.perfectSpecularReflection(l, normal);
			double dotP = Vector.dotProduct(obsVec, reflection);
			r += lightColor.getR()*Math.pow(dotP, n);
			g += lightColor.getG()*Math.pow(dotP, n);
			b += lightColor.getB()*Math.pow(dotP, n);
		}
		r *= o.getKs();
		g *= o.getKs();
		b *= o.getKs();
		return new Color(r, g, b);
	}
}
