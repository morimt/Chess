package de.fhdw.MRTN.Schach;

import java.util.LinkedList;
import java.util.List;

public class Board {

	private List<Figure> figures = new LinkedList<Figure>();
	private List<Figure> dead = new LinkedList<Figure>();
	
	public Board()
	{
		this.initFigures();
	}
	
	private void initFigures()
	{
		/*Pawn p1 = new Pawn(new Field('0','1'), "Pawn", "White");
		figures.add(p1);
		Pawn p2 = new Pawn(new Field('1','1'), "Pawn", "White");
		figures.add(p2);
		Pawn p3 = new Pawn(new Field('2','1'), "Pawn", "White");
		figures.add(p3);
		Pawn p4 = new Pawn(new Field('3','1'), "Pawn", "White");
		figures.add(p4);
		Pawn p5 = new Pawn(new Field('4','1'), "Pawn", "White");
		figures.add(p5);
		Pawn p6 = new Pawn(new Field('5','1'), "Pawn", "White");
		figures.add(p6);
		Pawn p7 = new Pawn(new Field('6','1'), "Pawn", "White");
		figures.add(p7);
		Pawn p8 = new Pawn(new Field('7','1'), "Pawn", "White");
		figures.add(p8);
		Pawn p9 = new Pawn(new Field('0','6'), "Pawn", "Black");
		figures.add(p9);
		Pawn p10 = new Pawn(new Field('1','6'), "Pawn", "Black");
		figures.add(p10);
		Pawn p11 = new Pawn(new Field('2','6'), "Pawn", "Black");
		figures.add(p11);
		Pawn p12 = new Pawn(new Field('3','6'), "Pawn", "Black");
		figures.add(p12);
		Pawn p13 = new Pawn(new Field('4','6'), "Pawn", "Black");
		figures.add(p13);
		Pawn p14 = new Pawn(new Field('5','6'), "Pawn", "Black");
		figures.add(p14);
		Pawn p15 = new Pawn(new Field('6','6'), "Pawn", "Black");
		figures.add(p15);
		Pawn p16 = new Pawn(new Field('7','6'), "Pawn", "Black");
		figures.add(p16);*/
		
		for(int i = 1; i < 7; i+=5)
		{
			for(int j = 0; j < 8; j++)
			{
				figures.add(new Pawn(new Field((char)j, (char)i), "Pawn", (i == 1)?"White":"Black"));
			}
		}		
		//TODO init for all other figs
	}
	
	/*Checks field if field is free, taken by friendly or foe. Parameters: f is field to check; c is Figure which calls the function (needed to check side)
	 * 0: Field is free.
	 * 1: Field is taken by friendly.
	 * 2: Field is taken by foe.
	 *-1: Field is out of bounds.
	 */
	public int checkField(Field f, Figure c)
	{
		for(Figure fig: figures)
		{
			if(fig.getField().equals(f))
			{
				if(fig.getSide().equals(c.getSide()))
					return 1;
				else
					return 2;
			}
		}
		if(f.getX() >= 0 && f.getX() <= 7 && f.getY() >= 0 && f.getY() <= 7)
			return 0;
		return -1;
	}
	
	//Computes fields for figures to move to. Takes in the figure to compute for. Returns a list of figures.
	public List<Field> computeFields(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		switch(f.getFig())
		{
		case "pawn":
			int x;
			if(f.getSide().equals("white"))
			{
				x = 1;
			} 
			else
			{
				x = -1;
			}
			
			Field f1 = new Field((char)(f.getField().getX()+x), (char)(f.getField().getY()-1));
			Field f2 = new Field((char)(f.getField().getX()+x), (char)(f.getField().getY()));
			Field f3 = new Field((char)(f.getField().getX()+x), (char)(f.getField().getY()+1));
			int tmp = this.checkField(f1, f);
			int tmp2 = this.checkField(f2, f);
			int tmp3 = this.checkField(f3, f);
			
			if(tmp == 0 || tmp == 2)
			{
				tmpList.add(f1);
			}
			if(tmp2 == 0 || tmp2 == 2)
			{
				tmpList.add(f2);
			}
			if(tmp3 == 0 || tmp3 == 2)
			{
				tmpList.add(f3);
			}
			if( !((Pawn) f).hasMoved() )
			{
				Field f4 = new Field((char)(f.getField().getX()+2*x), (char)(f.getField().getY()));
				int tmp4 = this.checkField(f3, f);
				if(tmp4 == 0 || tmp4 == 2)
					tmpList.add(f4);
			}
			
			return tmpList;
		case "rook":
			return tmpList;
		case "knight":
			return tmpList;
		case "bishop":
			return tmpList;
		case "queen":
			return tmpList;
		case "king":
			return tmpList;
			default:
				return tmpList;
			
		}
	}
	
	
	
	
	
}
