package raytracing.model.scene;

import java.util.Random;

import raytracing.model.Color;
import raytracing.model.basics.Point;

public class SceneFactory {

	public static Scene createBasicScene() {
		Color lightColor = new Color(1, 1, 1);
		/*Scene scene = new Scene(lightColor, 0.9);
		Light light1 = new Light(lightColor, new Point(0, 0, -80));
		Object sphere1 = new Sphere(new Point(0, 0, 0), 70, new Color(0, 1, 1), 0.4, 1, 7);*/
		Scene scene = new Scene(lightColor, 0.7);
		Light light1 = new Light(lightColor, new Point(-100, 100, 400));
		Light light2 = new Light(lightColor, new Point(100, 100, 400));
		Object sphere1 = new Sphere(new Point(0, 0, 600), 180, new Color(0, 0, 1), 0.4, 1, 7, true);
		Object sphere2 = new Sphere(new Point(-100, 100, 300), 40, new Color(1, 0, 0), 0.4, 1, 7, true);
		Object sphere3 = new Sphere(new Point(-80, 80, 100), 50, new Color(0, 1, 0), 0.4, 1, 7);
		scene.addLight(light1);
		scene.addLight(light2);
		scene.addObject(sphere1);
		scene.addObject(sphere2);
		//scene.addObject(sphere3);
		return scene;
	}
	
	public static Scene createSpiralScene() {
		Color lightColor = new Color(1, 1, 1);
		Scene scene = new Scene(lightColor, 0.8);
		Light light1 = new Light(lightColor, new Point(-800, -910, 200));
		Light light2 = new Light(lightColor, new Point(0, -800, 500));
		Light light3 = new Light(lightColor, new Point(400, -800, 700));
		Random rand = new Random();
		for (int i = 0; i <= 5; i++) {
			for (int k = 0; k <= 5; k++) {
				double sx = -1500 + 600*i;
				double sy = -1000;
				double sz = 400 + 200*k;
				double r = rand.nextDouble();
				double g = rand.nextDouble();
				double b = rand.nextDouble();
				Object sphere = new Sphere(new Point(sx, sy, sz), 80, new Color(r, g, b), 0.7, 1, 7, false);
				scene.addObject(sphere);
				
			}
			Light light = new Light(lightColor, new Point(-1500+600*i -100, -700, 400));
			scene.addLight(light);
		}
		for (int i = 0; i < 50; i++) {
			double sx = 600*Math.cos(i);
			double sy = 600*Math.sin(i);
			double sz = 400 + i*20;
			double lx = 400*Math.cos(i);
			double ly = 400*Math.sin(i);
			double lz = 400 + i*20;
			double r = rand.nextDouble();
			double g = rand.nextDouble();
			double b = rand.nextDouble();
			Object sphere = new Sphere(new Point(sx, sy, sz), 80, new Color(r, g, b), 0.7, 1, 7, false);
			scene.addObject(sphere);
			Light light = new Light(lightColor, new Point(lx, ly, lz));
			scene.addLight(light);
		}
		
		/*for (int i = 0; i < 50; i++) {
			double x = rand.nextInt(500) - 1000;
			double y = rand.nextInt(500) - 1000;
			double z = rand.nextInt(800) - 400;
			Light light = new Light(lightColor, new Point(x, y, z));
			scene.addLight(light);
		}*/
		Object sphere1 = new Sphere(new Point(0, 0, 1000), 300, new Color(0.2, 0.2, 0.2), 0.4, 1, 7, true);
		/*Object sphere2 = new Sphere(new Point(-40, 40, 300), 40, new Color(1, 0, 0), 0.4, 1, 7, true);
		Object sphere3 = new Sphere(new Point(-80, 80, 100), 50, new Color(0, 1, 0), 0.4, 1, 7);
		scene.addLight(light1);
		scene.addLight(light2);*/
		scene.addObject(sphere1);
		//scene.addObject(sphere2);
		//scene.addLight(light1);
		//scene.addLight(light2);
		//scene.addLight(light3);
		//scene.addObject(sphere3);
		return scene;
	}
}
