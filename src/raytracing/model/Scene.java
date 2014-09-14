package raytracing.model;

import java.util.ArrayList;
import java.util.List;

public class Scene {

	private List<Light> lights;
	private List<Object> objects;
	private Color ambientLightColor;
	private double ambientCoef;
	
	public Scene(Color ambientLightColor, double ambientCoef) {
		this.ambientLightColor = ambientLightColor;
		this.ambientCoef = ambientCoef;
		this.lights = new ArrayList<Light>();
		this.objects = new ArrayList<Object>();
	}
	
	public void addLight(Light light) {
		lights.add(light);
	}
	
	public void addObject(Object object) {
		objects.add(object);
	}

	public List<Light> getLights() {
		return lights;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public Color getAmbientLightColor() {
		return ambientLightColor;
	}

	public double getAmbientCoef() {
		return ambientCoef;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public void setAmbientLightColor(Color ambientLightColor) {
		this.ambientLightColor = ambientLightColor;
	}

	public void setAmbientCoef(double ambientCoef) {
		this.ambientCoef = ambientCoef;
	}
}
