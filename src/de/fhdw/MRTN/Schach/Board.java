package de.fhdw.MRTN.Schach;

import java.util.LinkedList;
import java.util.List;

public abstract class Board {

	private List<Figure> figures = new LinkedList<Figure>();
	private List<Figure> dead = new LinkedList<Figure>();
	
	public Board()
	{
		this.initFigures();
	}
	
	private void initFigures()
	{
		//TODO init
	}
	
	
}
