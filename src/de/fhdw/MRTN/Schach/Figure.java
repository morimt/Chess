package de.fhdw.MRTN.Schach;

import java.util.List;

public class Figure {
	
	private Field field;
	private String name;
	private String side;
	private List<Field> possibleFields;
	
	public Figure(Field nf, String name, String side) {
		this.setField(nf);
		this.setName(name);
		this.setSide(side);
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
	
	public String getSide()
	{
		return this.side;
	}
	
	private void setSide(String side)
	{
		this.side = side;
	}
	
	public String getFig()
	{
		return this.name.substring(0, this.name.indexOf('-')).toLowerCase();
	}

	public void move(Field f)
	{
		//TODO
	}
	
	public void move(char x, char y)
	{
		this.move(new Field(x, y));
	}
	
	
}
