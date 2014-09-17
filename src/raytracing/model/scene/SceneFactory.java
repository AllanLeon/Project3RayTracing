package raytracing.model.scene;

import raytracing.model.Color;
import raytracing.model.basics.Point;

public class SceneFactory {

	public static Scene createBasicScene() {
		Color lightColor = new Color(1, 1, 1);
		Scene scene = new Scene(lightColor, 0.9);
		Light light1 = new Light(lightColor, new Point(0, -80, 0));
		Object sphere1 = new Sphere(new Point(0, 0, 10), 70, new Color(0, 1, 1), 0.4, 1, 7);
		scene.addLight(light1);
		scene.addObject(sphere1);
		return scene;
	}
}
