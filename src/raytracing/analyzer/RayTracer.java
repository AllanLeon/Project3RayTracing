package raytracing.analyzer;

import raytracing.data.WindowConstants;
import raytracing.model.Color;
import raytracing.model.Ray;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;
import raytracing.model.scene.Light;
import raytracing.model.scene.Object;
import raytracing.model.scene.Scene;

public class RayTracer {

	private Scene scene;
	private double tMin;
	private Object oMin;
	private Point intersectionPoint;
	
	public RayTracer(Scene scene) {
		this.scene = scene;
	}

	public Color rayTracing(Ray ray, int depth) {
		if (depth <= 0) {
			return new Color(0, 0, 0);
		} else {
			checkIntersections(ray);
			if (oMin == null) {
				return new Color(0, 0, 0);
			} else {
				intersectionPoint = ray.getPointAt(tMin);
				return Phong.chromaticPhong(intersectionPoint, oMin, scene);
			}
		}
	}
	
	public Color shadowRayTracing(Ray ray, int depth) {
		if (depth <= 0) {
			return new Color(0, 0, 0);
		} else {
			checkIntersections(ray);
			if (oMin == null) {
				return new Color(0, 0, 0);
			} else {
				intersectionPoint = ray.getPointAt(tMin);
				return Phong.chromaticPhong(intersectionPoint, oMin, scene);
			}
		}
	}
	
	private void checkIntersections(Ray ray) {
		tMin = 9999999;
		oMin = null;
		for (int i = 0; i < scene.getObjects().size(); i++) {
			Object object = scene.getObjects().get(i);
			double t = object.checkIntersection(ray);
			if (t < tMin) {
				tMin = t;
				oMin = object;
			}
		}
	}
	
	public Ray createPixelRay(int x, int y) {
		Point start = new Point(0, 0, -100);
		Vector direction = new Vector(x - start.getX() - (WindowConstants.WIDTH / 2),
				y - start.getY() - (WindowConstants.HEIGHT / 2), 0 - start.getZ());
		return new Ray(start, direction);
	}
	
	public Ray createShadowRay(Point intersectionPoint, Light lightEmit) {
		Point start = intersectionPoint;
		Vector direction = new Vector(lightEmit.getPosition().getX() - start.getX(),
					lightEmit.getPosition().getY() - start.getY(), 0 - start.getZ());
		return new Ray(start, direction);
	}
}
