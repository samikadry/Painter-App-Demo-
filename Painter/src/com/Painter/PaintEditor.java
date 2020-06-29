package com.Painter;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

public enum PaintEditor 
{
	Instance;
	private static Vector<Pair<Shape, String>> actions = new Vector<Pair<Shape, String>>(), redo = new Vector<Pair<Shape, String>>();
	private static Vector<Shape> shapes = new Vector<Shape>(); 
	private static int redoFlag = 0, undoFlag = 0; 
	
	private PaintEditor()
	{
		System.out.println("==========Welcome to Painter program==========\n");
	}
	
	public static Vector<Shape> getShapes() 
	{
		return shapes;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean addShape(Shape shape) 
	{
		try 
		{
			shapes.add(shape);
			int red, green, blue;;
			red = shape.getColor().getRed();
			green = shape.getColor().getGreen();
			blue = shape.getColor().getBlue();
			String details = "Added the shape:\nShape: " + shape + "\nSize: " + shape.getSize() + "\nLocation: " + shape.getLocation() 
							 + "\nColor: " + red + "," + green + "," + blue + "\nTransparent: " + shape.isTransparentFlag() + "\n";
			actions.add(new Pair(shape, details));
			System.out.println(details + "===================");
			undoFlag++;
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean deleteShape(Shape shape) throws Exception
	{
		if(!shapes.contains(shape))
			return false;
		
		int red, green, blue;;
		red = shape.getColor().getRed();
		green = shape.getColor().getGreen();
		blue = shape.getColor().getBlue();
		String details = "Removed the shape:\nShape: " + shape + "\nSize: " + shape.getSize() + "\nLocation: " + shape.getLocation() 
						 + "\nColor: " + red + "," + green + "," + blue + "\nTransparent: " + shape.isTransparentFlag() + "\n";
		shapes.remove(shape);
		actions.add(new Pair(shape, details));
		System.out.println(details + "===================");
		undoFlag++;
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean updateSize(Shape shape, double size) throws Exception
	{
		if(!shapes.contains(shape))
			return false;
		
		for(Shape s:shapes)
		{
			if(s.equals(shape))
			{
				int red, green, blue;;
				red = s.getColor().getRed();
				green = s.getColor().getGreen();
				blue = s.getColor().getBlue();
				String details = "Size Updated:\nShape: " + shape + "\nSize before: " + shape.getSize() + "\tSize after: " + size + "\nLocation: " 
				+ shape.getLocation() + "\nColor: r=" + red + " g=" + green + " b=" + blue + "\nTransparent: " + shape.isTransparentFlag() + "\n";
				actions.add(new Pair(shape, details));
				s.setSize(size);
				System.out.println(details + "===================");
				undoFlag++;
				return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean updateColor(Shape shape, Color color) throws Exception
	{
		if(!shapes.contains(shape))
			return false;
		
		for(Shape s:shapes)
		{
			if(s.equals(shape))
			{
				int red, green, blue, newRed, newGreen, newBlue;
				red = s.getColor().getRed();
				green = s.getColor().getGreen();
				blue = s.getColor().getBlue();
				newRed = color.getRed();
				newGreen = color.getGreen();
				newBlue = color.getBlue();
				String details = "Color Updated:\nShape: " + shape + "\nSize: " + shape.getSize() + "\nLocation: " + shape.getLocation() 
								 + "\nColor before: r=" + red + " g=" + green + " b=" + blue + "\tColor after: r=" + newRed + " b=" + newGreen + " g=" + 
						         newBlue + "\nTransparent: " + shape.isTransparentFlag() + "\n";
				actions.add(new Pair(shape, details));
				s.setColor(color);
				System.out.println(details + "===================");
				undoFlag++;
				return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean updateLocation(Shape shape, Point location) throws Exception
	{
		if(!shapes.contains(shape))
			return false;
		
		for(Shape s:shapes)
		{
			if(s.equals(shape))
			{
				int red, green, blue;;
				red = s.getColor().getRed();
				green = s.getColor().getGreen();
				blue = s.getColor().getBlue();
				String details = "Location Updated:\nShape: " + shape + "\nSize: " + shape.getSize() + "\nLocation before: " + shape.getLocation() 
				+ "\tLocation after:" + location + "\nColor: r=" + red + " g=" + green + " b=" + blue + "\nTransparent: " + shape.isTransparentFlag() + "\n";
				actions.add(new Pair(shape, details));
				s.setLocation(location);
				System.out.println(details + "===================");
				undoFlag++;
				return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean reDo() throws Exception
	{
		if(redoFlag == 0)
			return false;		
		
		String toMake = redo.get(redoFlag-1).getValue().split(" ")[0];
		switch(toMake)
		{
			case "Added":
				PaintEditor.addShape(redo.get(redoFlag-1).getKey());
				actions.add(new Pair(redo.get(redoFlag-1).getKey(), redo.remove(redoFlag-1).getValue()));
				break;
				
			case "Removed":
				PaintEditor.deleteShape(redo.get(redoFlag-1).getKey());
				actions.add(new Pair(redo.get(redoFlag-1).getKey(), redo.remove(redoFlag-1).getValue()));
				break;
				
			case "Color":
				int r = Integer.parseInt(redo.get(redoFlag-1).getValue().substring(redo.get(redoFlag-1).getValue().indexOf("r=")+2, redo.get(redoFlag-1).getValue().indexOf(" g="))), 
					g = Integer.parseInt(redo.get(redoFlag-1).getValue().substring(redo.get(redoFlag-1).getValue().indexOf("g=")+2, redo.get(redoFlag-1).getValue().indexOf(" b="))), 
					b = Integer.parseInt(redo.get(redoFlag-1).getValue().substring(redo.get(redoFlag-1).getValue().indexOf("b=")+2, redo.get(redoFlag-1).getValue().indexOf("\tColor after:")));
				Color color = new Color(r, g, b);
				shapes.get(shapes.indexOf(redo.get(redoFlag-1).getKey())).setColor(color);
				actions.add(new Pair(redo.get(redo.size()-1).getKey(), redo.remove(redo.size()-1).getValue()));
				break;
				
			case "Size":
				updateSize(shapes.get(shapes.indexOf(redo.get(redoFlag-1).getKey())), (Double.parseDouble(redo.get(redoFlag-1).getValue().substring(redo.get(redoFlag-1)
				.getValue().indexOf("before:")+8, redo.get(redoFlag-1).getValue().indexOf("\tSize after:")))));
				actions.add(new Pair(redo.get(redoFlag-1).getKey(), redo.remove(redoFlag-1).getValue()));
				break;
				
			case "Location":
				String str = new String(redo.get(redoFlag-1).getValue().substring(redo.get(redoFlag-1).getValue().indexOf("before:")+9, redo.get(redoFlag-1).getValue().indexOf("\tLocation")));
				Point p = new Point(Integer.parseInt(str.substring(0, str.indexOf(","))), Integer.parseInt(str.substring(str.indexOf(",")+1, str.indexOf(")"))));
				updateLocation(shapes.get(shapes.indexOf(redo.get(redoFlag-1).getKey())), p);
				actions.add(new Pair(redo.get(redoFlag-1).getKey(), redo.remove(redoFlag-1).getValue()));
				break;
		}
		
		redoFlag--;
		undoFlag++;
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean unDo() throws Exception
	{
		if(undoFlag == 0)
			return false;
		
		String toMake = actions.get(undoFlag-1).getValue().split(" ")[0];
		switch(toMake)
		{
			case "Added":
				redo.add(new Pair(shapes.get(undoFlag-1), actions.remove(undoFlag-1).getValue()));
				PaintEditor.deleteShape(shapes.get(undoFlag-1));
				break;
			
			case "Removed":
				redo.add(new Pair(actions.get(undoFlag-1).getKey(), actions.remove(undoFlag-1).getValue()));
				PaintEditor.addShape(shapes.get(undoFlag-1));
				break;
				
			case "Color":
				int r = Integer.parseInt(actions.get(undoFlag-1).getValue().substring(actions.get(undoFlag-1).getValue().indexOf("r=")+2, actions.get(undoFlag-1).getValue().indexOf(" g="))), 
					g = Integer.parseInt(actions.get(undoFlag-1).getValue().substring(actions.get(undoFlag-1).getValue().indexOf("g=")+2, actions.get(undoFlag-1).getValue().indexOf(" b="))), 
					b = Integer.parseInt(actions.get(undoFlag-1).getValue().substring(actions.get(undoFlag-1).getValue().indexOf("b=")+2, actions.get(undoFlag-1).getValue().indexOf("\tColor after:")));
				Color color = new Color(r, g, b);
				updateColor(shapes.get(shapes.indexOf(actions.get(undoFlag-1).getKey())), color);
				redo.add(new Pair(actions.get(actions.size()-1).getKey(), actions.remove(actions.size()-1).getValue()));
				break;
				
			case "Size":
				updateSize(shapes.get(shapes.indexOf(actions.get(undoFlag-1).getKey())), (Double.parseDouble(actions.get(undoFlag-1).getValue().substring(actions.get(undoFlag-1)
				.getValue().indexOf("before:")+8, actions.get(undoFlag-1).getValue().indexOf("\tSize after:")))));
				redo.add(new Pair(actions.get(undoFlag-1).getKey(), actions.remove(undoFlag-1).getValue()));
				break;
				
			case "Location":
				String str = new String(actions.get(undoFlag-1).getValue().substring(actions.get(undoFlag-1).getValue().indexOf("before:")+9, actions.get(undoFlag-1).getValue().indexOf("\tLocation")));
				Point p = new Point(Integer.parseInt(str.substring(0, str.indexOf(","))), Integer.parseInt(str.substring(str.indexOf(",")+1, str.indexOf(")"))));
				updateLocation(shapes.get(shapes.indexOf(actions.get(undoFlag-1).getKey())), p);
				redo.add(new Pair(actions.get(undoFlag-1).getKey(), actions.remove(undoFlag-1).getValue()));
				break;
			
			default:
				System.out.println("\nThere is an error occured!!");
				return false;
		}
		
		redoFlag++;
		undoFlag--;
		return true;
	}
	
	public static boolean save()
	{
		try 
		{
			FileWriter file = new FileWriter("Saved paint.txt");			
			for(Shape s: shapes)
				file.write("Shape details:\nName: " + s + "\nColor: " + s.getColor().toString().split("java.awt.Color")[1] 
							+ "\nSize: " + s.getSize() + "\nLocation: " + s.getLocation() + "\n\n");
			file.close();
		    return true;
		} 
		catch (Exception e) 
		{
		      System.out.println("\nAn error occurred during creating the file!!");
		      return false;
		}
	}
	
	public static boolean load(File file)
	{
		String data = "", name = "";
		Color color = null;
		double size = 0.0;
		Point location = null;
		
		try 
		{
			Scanner myReader = new Scanner(file);
			shapes.removeAllElements();
			
		    while (myReader.hasNext()) 
		    {
		    	data = myReader.next();
		    	switch(data)
		    	{
		    		case "Name:":
		    			name = myReader.next();
		    			break;
		    		
		    		case "Color:":
		    			data = myReader.next();
		    			color = new Color(Integer.parseInt(data.substring(data.indexOf("r=")+2,data.indexOf(",g="))),
		    							  Integer.parseInt(data.substring(data.indexOf("g=")+2,data.indexOf(",b="))),
		    							  Integer.parseInt(data.substring(data.indexOf("b=")+2,data.indexOf("]"))));
		    			break;
		    		
		    		case "Size:":
		    			size = myReader.nextDouble();
		    			break;
		    		
		    		case "Location:":
		    			data = myReader.next();
		    			location = new Point(Integer.parseInt(data.substring(data.indexOf("(")+1,data.indexOf(","))),
		    								 Integer.parseInt(data.substring(data.indexOf(",")+1,data.indexOf(")"))));
		    			switch(name)
		    			{
		    				case "Circle":
		    					Shape circle = new Circle(size, location, color, false);
		    					PaintEditor.addShape(circle);
		    					break;
		    					
		    				case "Rectangle":
		    					Shape rectangle = new Rectangle(size, location, color, false);
		    					PaintEditor.addShape(rectangle);
		    					break;
		    					
		    				case "Square":
		    					Shape square = new Square(size, location, color, false);
		    					PaintEditor.addShape(square);
		    					break;
		    					
		    				case "Triangle":
		    					Shape triangle = new Triangle(size, location, color, false);
		    					PaintEditor.addShape(triangle);
		    					break;
		    					
		    				case "Line":
		    					Shape line = new Line(size, location, color, false);
		    					PaintEditor.addShape(line);
		    					break;
		    					
		    				case "Custom":
		    					Shape custom = new Custom(size, location, color, false, new Vector<Point>());
		    					PaintEditor.addShape(custom);
		    					break;
		    			}
		    			break;
		    			
		    		default:
		    			continue;
		    	}
		    }
		    myReader.close();
		    return true;
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("\nError.. File not found!!");
			return false;
		}
	}
}