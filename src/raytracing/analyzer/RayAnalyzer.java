package raytracing.analyzer;

import raytracing.model.Vector;

public class RayAnalyzer {

	public static Vector perfectSpecularReflection(Vector light, Vector normal) {
		double dotP = Vector.dotProduct(light, normal);
		double x = light.getX() - 2 * dotP * normal.getX();
		double y = light.getY() - 2 * dotP * normal.getY();
		double z = light.getZ() - 2 * dotP * normal.getZ();
		Vector r = new Vector(x, y, z);
		r.normalize();
		return r;
	}
}
