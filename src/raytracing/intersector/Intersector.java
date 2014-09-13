package raytracing.intersector;

import raytracing.model.Ray;
import raytracing.model.Sphere;

public class Intersector {

	public static double raySphereIntersection(Ray ray, Sphere sphere) {
		double dx = ray.getStart().getX() - sphere.getCenter().getX();
		double dy = ray.getStart().getY() - sphere.getCenter().getY();
		double dz = ray.getStart().getZ() - sphere.getCenter().getZ();
		double xd = ray.getDirection().getX();
		double yd = ray.getDirection().getY();
		double zd = ray.getDirection().getZ();
		double r = sphere.getRadius();
		double b = 2 * xd * dx + 2 * yd * dy + 2 * zd * dz;
		double c = Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2) - Math.pow(r, 2);
		double d = Math.pow(b, 2) - 4 * c;
		if (d > 0) {
			double t0 = (-b - Math.sqrt(d)) / 2;
			double t1 = (-b + Math.sqrt(d)) / 2;
			if (t0 > t1) {
				return t1;
			} else {
				return t0;
			}
		} else {
			return 0;
		}
	}
}
