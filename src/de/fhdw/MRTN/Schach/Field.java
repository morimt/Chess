package de.fhdw.MRTN.Schach;

public class Field {
	
	 private int x, y;
	 
	 public Field(int x, int y)
	 {
		 this.setX(x);
		 this.setY(y);
	 }
	 
	 public Field(Field f)
	 {
		 this.setX(f.getX());
		 this.setY(f.getY());
	 }

	 public void setX(int x)
	 {
		 this.x = x;
	 }
	 
	 public void setY(int y)
	 {
		 this.y = y;
	 }
	 
	 public int getX()
	 {
		 return x;
	 }
	 
	 public int getY()
	 {
		 return y;
	 }
	 
	 public boolean equals(Field f)
	 {
		 return (this.getX() == f.getX() && this.getY() == f.getY());
	 }
	 
	 
	 
	 public void printField()
	 {
		 System.out.println("(" + (char)(65+x) + ", " + y + ")");
	 }
	 
	 
}
