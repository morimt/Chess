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
}
