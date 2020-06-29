package com.Painter;

import java.awt.Color;

public class Circle extends Shape 
{
	private double radius;

	public Circle(double size, Point location, Color color, boolean transparentFlag) 
	{
		super(size, location, color, transparentFlag);
		this.radius = Math.sqrt(size/Math.PI);
	}

	public double getRadius() 
	{
		return radius;
	}

	public void setRadius(double radius) 
	{
		this.radius = radius;
	}
}