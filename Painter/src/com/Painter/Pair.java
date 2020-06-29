package com.Painter;

public class Pair<Key,Value> 
{
	  private final Key key;
	  private Value value;

	  public Pair(Key key, Value value) throws Exception 
	  {
	    if(key == null)
	    	throw new Exception("Invalid key!!");

	    this.key = key;
	    this.value = value;
	  }

	  public Key getKey() 
	  {
		  return key; 
	  }
	  
	  public Value getValue() 
	  { 
		  return value; 
	  }
	  
	  public void setValue(Value value)
	  {
		  this.value = value;
	  }

	  @Override
	  public int hashCode() 
	  { 
		  return key.hashCode() ^ value.hashCode(); 
	  }

	  @Override
	  public boolean equals(Object o) 
	  {
	    if (!(o instanceof Pair)) 
	    	return false;
	    
	    Pair pair = (Pair)o;
	    return this.key.equals(pair.getKey()) && this.value.equals(pair.getValue());
	  }
}