package com.Painter;

import java.awt.Color;

public class Line extends Shape 
{
	private double length;

	public Line(double size, Point location, Color color, boolean transparentFlag) 
	{
		super(size, location, color, transparentFlag);
		this.length = size;
	}

	public double getLength() 
	{
		return length;
	}

	public void setLength(double length) 
	{
		this.length = length;
	}
}