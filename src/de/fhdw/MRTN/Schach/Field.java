package de.fhdw.MRTN.Schach;

public class Field {
	
	 private char x, y;
	 
	 public Field(char x, char y)
	 {
		 this.setX(x);
		 this.setY(y);
	 }

	 public void setX(char x)
	 {
		 this.x = x;
	 }
	 
	 public void setY(char y)
	 {
		 this.y = y;
	 }
	 
	 public char getX()
	 {
		 return x;
	 }
	 
	 public char getY()
	 {
		 return y;
	 }
	 
	 public boolean equals(Field f)
	 {
		 return (this.getX() == f.getX() && this.getY() == f.getY());
	 }
	 
	 
	 
	 public void printField()
	 {
		 System.out.println("(" + (int)x + ", " + (int)y + ")");
	 }
	 
	 
}
