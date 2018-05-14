package de.fhdw.MRTN.Schach;

import java.util.List;

public class Figure {
	
	private Field field;
	private String name;	//e.g. "Pawn-Black"
	private List<Field> possibleFields;
	
	public Figure(Field nf, String n, Board b) {
		this.setField(nf);
		this.setName(n);
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getPossibleFields() {
		return possibleFields;
	}

	public void setPossibleFields(List<Field> possibleFields) {
		this.possibleFields = possibleFields;
	}

	public boolean move(Field f)
	{
		//TODO
		return false;
	}
	
	public boolean move(char x, char y)
	{
		return this.move(new Field(x, y));
	}



}
