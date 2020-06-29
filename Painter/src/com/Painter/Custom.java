package com.Painter;

import java.awt.Color;
import java.util.Vector;

public class Custom extends Shape 
{
	private Vector<Point> points;

	public Custom(double size, Point location, Color color, boolean transparentFlag, Vector<Point> points) 
	{
		super(size, location, color, transparentFlag);
		this.points = points;
	}

	public Vector<Point> getPoints() 
	{
		return points;
	}

	public void setPoints(Vector<Point> points) 
	{
		this.points = points;
	}
	
	public void addPoint(Point point)
	{
		points.add(point);
	}
}