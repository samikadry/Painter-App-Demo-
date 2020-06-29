package com.Painter;

import java.awt.Color;

public abstract class Shape 
{
	private double size;
	private Point location;
	private Color color;
	private boolean transparentFlag;
	
	public Shape(double size, Point location, Color color, boolean transparentFlag) 
	{
		this.size = size;
		this.location = location;
		this.color = color;
		this.transparentFlag = transparentFlag;
	}
	
	public double getSize() 
	{
		return size;
	}

	public void setSize(double size) 
	{
		this.size = size;
	}

	public Point getLocation() 
	{
		return location;
	}

	public void setLocation(Point location) 
	{
		this.location = location;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	public boolean isTransparentFlag() 
	{
		return transparentFlag;
	}

	public void setTransparentFlag(boolean transparentFlag) 
	{
		this.transparentFlag = transparentFlag;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		long temp;
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (transparentFlag ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		if (transparentFlag != other.transparentFlag)
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return this.getClass().getName().split("com.Painter.")[1];
	}	
}