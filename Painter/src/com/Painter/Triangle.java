package com.Painter;

import java.awt.Color;

public class Triangle extends Shape 
{
	private double base, height;

	public Triangle(double size, Point location, Color color, boolean transparentFlag) 
	{
		super(size, location, color, transparentFlag);
		this.base = Math.sqrt(size*2);
		this.height = Math.sqrt(size*2);
	}

	public double getBase() 
	{
		return base;
	}

	public double getHeight() 
	{
		return height;
	}

	public void setHeightAndBase(double height, double base) 
	{
		if(height*base/2 == this.getSize())
		{
			this.height = height;
			this.base = base;
		}
	}
}
