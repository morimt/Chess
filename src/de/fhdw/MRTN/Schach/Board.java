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
		for(int i = 1; i < 7; i+=5)
		{
			for(int j = 0; j < 8; j++)
			{
				figures.add(new Pawn(new Field((char)(j+65), (char)(i+48)), "Pawn", (i == 1)?"White":"Black"));
				this.computeFields(figures.get(figures.size()-1));
			}
		}		
		//TODO init for all other figs
		
		/* 	for(Figure fig: figures)
		 	{
		 		fig.getField().printField();
		 	}
		 */
	}
	
	/*Checks field if field is free, taken by friendly or foe. Parameters: f is field to check; c is Figure which calls the function (needed to check side)
	 * 0: Field is free.
	 * 1: Field is taken by friendly.
	 * 2: Field is taken by foe.
	 * 3: Field is taken by enemy king.
	 *-1: Field is out of bounds.
	 */
	public int checkField(Figure c, Field f)
	{
		for(Figure fig: figures)
		{
			if(fig.getField().equals(f))
			{
				if(fig.getSide().equals(c.getSide()))
					return 1;
				else if(!fig.getSide().equals(c.getSide()) && fig.getName().equals("king"))
					return 3;
				else
					return 2;
			}
		}
		if(f.getX() >= 0 && f.getX() <= 7 && f.getY() >= 0 && f.getY() <= 7)
			return 0;
		return -1;
	}
	
	//Computes fields for figures to move to. Takes in the figure to compute for. Returns a list of figures.
	public void computeFields(Figure f)
	{
		switch(f.getFig())
		{
		case "pawn":
			computePawn(f);
			break;
		case "rook":
			computeRook(f);
			break;
		case "knight":
			computeKnight(f);
			break;
		case "bishop":
			computeBishop(f);
			break;
		case "queen":
			computeQueen(f);
			break;
		case "king":
			computeKing(f);
			break;
		}
	}
	
	/*
	 * Returns:
	 * 0: Figure has been moved to target field.
	 * 1: Figure has not been moved because there is a friendly on the target field.
	 * 2: Figure has not been moved because there is an enemy king on the target field. 
	 *-1: Figure has not been moved because the target field is out of bounds.
	 * MIN_INT: Failed to add proper return statement.
	 */
	public int move(Figure fig, Field tf)
	{
		
		switch(fig.getName())
		{
		case "pawn":
			if(((Pawn) fig).getPossibleFields().contains(tf))
			{
				fig.setField(tf);
				this.computeFields(fig);
				return 0;
			}
			else
			{
				switch(this.checkField(fig, tf))
				{
				case 1:
					return 1;
				case 3:
					return 2;
				case -1:
					return -1;
				}
			}
			break;
		}
		
		return Integer.MIN_VALUE;
	}
	
	/* Returns:
	 * True: On field would be check
	 * False: On field would not be check
	 */
	private boolean checkOnField(Field f)
	{
		for(Figure fig: figures)
		{
			if(fig.getPossibleFields().contains(f))
			{
				return true;
			}
		}
		return false;
	}
	
	private void computePawn(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
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
		int tmp1 = this.checkField(f, f1);
		int tmp2 = this.checkField(f, f2);
		int tmp3 = this.checkField(f, f3);
		
		if(tmp1 == 0 || tmp1 == 2)
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
			int tmp4 = this.checkField(f, f4);
			if(tmp4 == 0 || tmp4 == 2)
				tmpList.add(f4);
		}
		if(tmp1 == 3)
		{
			tmpList.add(f1);
		}
		if(tmp3 == 3)
		{
			tmpList.add(f3);
		}
		
		f.setPossibleFields(tmpList);
	}
	
	private void computeRook(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		for(int i = 0; i < 4; i++)
		{
			for(int j = 1; j < 8; j++)
			{	
				//Field to check
				Field ftc = f.getField();
				//ax and ay are j * (-1, 0 or 1) depending on whether checking up, right, down or left
				int ax = 0, ay = 0;
				switch(i)
				{
				//Up
				case 0:
					ax = j * 0;
					ay = j * 1;
					break;
				//Right
				case 1:
					ax = j * 1;
					ay = j * 0;
					break;
				//Down
				case 2:
					ax = j * 0;
					ay = j * -1;
					break;
				//Left
				case 3:
					ax = j * -1;
					ay = j * 0;
					break;
				}
				ftc.setX(ftc.getX()+ax);
				ftc.setY(ftc.getY()+ay);
				int chkres = this.checkField(f, ftc); 
				if(chkres == -1 || chkres == 1)
				{
					break;
				}
				else if(chkres == 0)
				{
					tmpList.add(new Field(ftc));
				}
				else if(chkres == 2 || chkres == 3)
				{
					tmpList.add(new Field(ftc));
					break;
				}
			}
		}
		f.setPossibleFields(tmpList);
	}
	
	private void computeKnight(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		Field ftc = new Field(f.getField());
		for(int i = 0; i < 8; i++)
		{
			//ax and ay are -2, -1, 1 or 2 depending on whether checking up right, right up, right down, down right, down left, left down, left up or up left
			int ax = 0, ay = 0;
			switch(i)
			{
			case 0:
				ax = 1;
				ay = 2;
				break;
			case 1:
				ax = 2;
				ay = 1;
				break;
			case 2:
				ax = 2;
				ay = -1;
				break;
			case 3:
				ax = 1;
				ay = -2;
				break;
			case 4:
				ax = -1;
				ay = -2;
				break;
			case 5:
				ax = -2;
				ay = -1;
				break;
			case 6:
				ax = -2;
				ay = 1;
				break;
			case 7:
				ax = -1;
				ay = 2;
				break;
			}
			ftc.setX(f.getField().getX()+ax);
			ftc.setY(f.getField().getY()+ay);
			int chkres = this.checkField(f, ftc); 
			if(chkres == 0 || chkres == 2)
			{
				tmpList.add(new Field(ftc));
			}
		}
		f.setPossibleFields(tmpList);
	}
	
	private void computeBishop(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		for(int j = 0; j < 4; j++)
		{
			outerloop:
			for(int i = 1; i < 8; i++)
			{
				int ax = 0, ay = 0;
				Field ftc = new Field(f.getField());
				switch(j)
				{
				case 0:
					ax = i * 1;
					ay = i * 1;
					break;
				case 1:
					ax = i * 1;
					ay = i * -1;
					break;
				case 2:
					ax = i * -1;
					ay = i * -1;
					break;
				case 3:
					ax = i * -1;
					ay = i * 1;
					break;
				}
				ftc = new Field(f.getField());
				ftc.setX(f.getField().getX()+ax);
				ftc.setY(f.getField().getY()+ay);
				int ckr = this.checkField(f, ftc);
				
				switch(ckr)
				{
				case 0:
					tmpList.add(new Field(ftc));
					break;
				case 2:
					tmpList.add(new Field(ftc));
					break outerloop;
				case 3:
					tmpList.add(new Field(ftc));
					break outerloop;	
				case 1:
					break outerloop;
				case -1:
					break outerloop;
				}
			}
		}
		f.setPossibleFields(tmpList);
	}
	
	private void computeQueen(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		for(int j = 0; j < 8; j++)
		{
			for(int i = 1; i < 8; i++)
			{
				//ftc: Field to check
				Field ftc = new Field(f.getField());
				//ax und ay are i * (-1, 0 or 1) depending on which field to check
				int ax = 0, ay = 0;
				switch(j)
				{
				case 0:
					ax = i * 0;
					ay = i * 1;
					break;
				case 1:
					ax = i * 1;
					ay = i * 1;
					break;
				case 2:
					ax = i * 1;
					ay = i * 0;
					break;
				case 3:
					ax = i * 1;
					ay = i * -1;
					break;
				case 4:
					ax = i * 0;
					ay = i * -1;
					break;
				case 5:
					ax = i * -1;
					ay = i * -1;
					break;
				case 6:
					ax = i * -1;
					ay = i * 0;
					break;
				case 7:
					ax = i * -1;
					ay = i * 1;
					break;
				}
				
				ftc.setX(f.getField().getX()+ax);
				ftc.setY(f.getField().getY()+ay);
				int ckr = this.checkField(f, ftc);
				
				if(ckr == 0)
				{
					tmpList.add(ftc);
				}
				else if(ckr == 2 || ckr == 3)
				{
					tmpList.add(ftc);
					break;
				}
				else if(ckr == -1 || ckr == 1)
				{
					break;
				}
			}
		}
		f.setPossibleFields(tmpList);
	}
	
	private void computeKing(Figure f)
	{
		List<Field> tmpList = new LinkedList<Field>();
		
		f.setPossibleFields(tmpList);
	}
	
	
	
	
}
