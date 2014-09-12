package raytracing.model;

public class Vector  extends Coordinates{

	public Vector(int x, int y, int z) {
		super(x, y, z);
		w = 0;
	}	
}
