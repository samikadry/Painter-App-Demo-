package com.Painter;

import java.awt.Color;

public class Rectangle extends Shape 
{
	private double height, width;

	public Rectangle(double size, Point location, Color color, boolean transparentFlag) 
	{
		super(size, location, color, transparentFlag);
		this.height = Math.sqrt(size);
		this.width = Math.sqrt(size);
	}

	public double getHeight() 
	{
		return height;
	}

	public double getWidth() 
	{
		return width;
	}

	public void setWidthAndHeight(double width, double height) 
	{
		if(width*height == this.getSize())
		{
			this.width = width;
			this.height = height;
		}
	}
}