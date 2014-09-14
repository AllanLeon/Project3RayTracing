package raytracing.model.scene;

import java.util.ArrayList;
import java.util.List;

import raytracing.model.Color;

public class Scene {

	private List<Light> lights;
	private List<Object> objects;
	private Color environmentalLightColor;
	private double environmentalCoef;
	
	public Scene(Color environmentalLightColor, double environmentalCoef) {
		this.environmentalLightColor = environmentalLightColor;
		this.environmentalCoef = environmentalCoef;
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

	public Color getEnvironmentalLightColor() {
		return environmentalLightColor;
	}

	public double getEnvironmentalCoef() {
		return environmentalCoef;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public void setEnvironmentalLightColor(Color environmentalLightColor) {
		this.environmentalLightColor = environmentalLightColor;
	}

	public void setEnvironmentalCoef(double environmentalCoef) {
		this.environmentalCoef = environmentalCoef;
	}
}
