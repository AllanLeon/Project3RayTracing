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
		boolean existsMirroring = false;
		for (int i = 0; i < scene.getObjects().size(); i++) {
			if (scene.getObjects().get(i).isMirror()) {
				existsMirroring = true;
				break;
			}
			existsMirroring = false;
		}
		
		if (depth <= 0) {
			return new Color(0, 0, 0);
		} else if (existsMirroring) {
			intersectionPoint = ray.getPointAt(tMin);
			Vector mirrorDir = GeometricAnalyzer.perfectSpecularReflection(ray.getDirection(), oMin.getNormal(intersectionPoint));
			Ray mirrorRay = new Ray(intersectionPoint, mirrorDir);
			return rayTracing(mirrorRay, depth - 1);
		} else {
			checkIntersections(ray);
			if (oMin == null) {
				return new Color(0, 0, 0);
			} else {
				intersectionPoint = ray.getPointAt(tMin);
				if (checkShadowRayIntersections()) {
					Color color = Phong.environmentalComponent(oMin, scene);
					color.checkBounds();
					return color;
				} else {
					return Phong.chromaticPhong(intersectionPoint, oMin, scene);
				}
			}
		}
	}
	
	public boolean checkShadowRayIntersections() {
		for (int i = 0; i < scene.getLights().size(); i++) {
			Light light = scene.getLights().get(i);
			double dotP = Vector.dotProduct(light.getNormalizedDirection(intersectionPoint), oMin.getNormal(intersectionPoint));
			if (dotP > 0) {
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
			}
		}
		return false;
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
		direction.normalize();
		return new Ray(start, direction);
	}
}
