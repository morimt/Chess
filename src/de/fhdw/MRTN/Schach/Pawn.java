package de.fhdw.MRTN.Schach;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Figure{

	private boolean hasMoved;
	
	public Pawn(Field nf, String n, Board b)
	{
		super(nf, n, b);
		this.hasMoved = false;
	}
	
	public boolean hasMoved()
	{
		return hasMoved;
	}
	
	public void setHasMoved()
	{
		hasMoved = true;
	}
	
	
	
	
	
	
}
