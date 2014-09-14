package raytracing.analyzer;

import raytracing.data.DataConstants;
import raytracing.model.Ray;
import raytracing.model.basics.Point;
import raytracing.model.basics.Vector;

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
	
	public static Ray createPixelRay(int x, int y) {
		Point start = new Point(0, 0, -100);
		Vector direction = new Vector(x - start.getX() - (DataConstants.WINDOW_WIDTH / 2),
				y - start.getY() - (DataConstants.WINDOW_HEIGHT / 2), 0 - start.getZ());
		return new Ray(start, direction);
	}
}
