package com.Painter;
import java.awt.Color;
import java.io.File;

public class Painter 
{
	public static void main(String[] args) throws Exception 
	{
		
		Shape square = new Square(10, new Point(5,5), new Color(10,10,10), true); 
		Shape circle = new Circle(34, new Point(17,19), new Color(0,0,25),true); 
		Shape rectangle = new Rectangle(16, new Point(35,50), new Color(17,25,0), true);
		  
		PaintEditor.addShape(square); 
		PaintEditor.addShape(circle);
		PaintEditor.addShape(rectangle);
		  
		System.out.println("\n==========Removing the last added shape after undo action==========");
		PaintEditor.unDo();
		System.out.println("\n==========The existing shapes after undo==========\n" +
		PaintEditor.getShapes() + "\n====================\n");
		  
		System.out.println("\n==========Adding the last removed shape after redo action==========");
		PaintEditor.reDo();
		System.out.println("\n==========The existing shapes after redo==========\n" +
		PaintEditor.getShapes() + "\n====================\n");
		  
		PaintEditor.updateSize(circle, 5);
		  
		System.out.println("\n==========Check undo and redo size update==========\n");
		PaintEditor.unDo(); 
		PaintEditor.reDo();
		 
		System.out.println("\n==========Check undo color update==========\nThe old color is: " + square.getColor()
						   + "\n====================\n");
		PaintEditor.updateColor(square, new Color(3,3,3)); 
		PaintEditor.unDo(); 
		System.out.println("The new color is: " + square.getColor() + "\n====================\n");
		  
		  
		System.out.println("\n==========Check undo size update==========\nThe old size is: " + circle.getSize()
		      			   + "\n====================\n");
		PaintEditor.updateSize(circle, 22.8);
		PaintEditor.unDo(); 
		System.out.println("The new size is: " + circle.getSize() + "\n====================\n");
		 
		  
		System.out.println("\n==========Check undo location update==========\nThe old location is: " + rectangle.getLocation()
			 			   + "\n====================\n");
		PaintEditor.updateLocation(rectangle, new Point(40,40)); 
		PaintEditor.unDo();
		System.out.println("The new location is: " + rectangle.getLocation() + "\n\n==========End of program==========\n");
		 
		
		File file = new File("../Painter/Paint1.txt");
		PaintEditor.load(file);
		
		System.out.println("\n==========The existing shapes after load a file==========\n" + PaintEditor.getShapes() + "\n====================\n");
		
		PaintEditor.addShape(new Line(13, new Point(5,5), new Color(88, 88, 88), true));
		
		System.out.println("\n==========The existing shapes after adding line==========\n" + PaintEditor.getShapes() + "\n====================\n");
		
		PaintEditor.save();
	}
}