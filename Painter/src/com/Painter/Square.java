package com.Painter;

import java.awt.Color;

public class Square extends Rectangle 
{
	public Square(double size, Point location, Color color, boolean transparentFlag) 
	{
		super(size, location, color, transparentFlag);
		this.setWidthAndHeight(size/2, size/2);
	}
}