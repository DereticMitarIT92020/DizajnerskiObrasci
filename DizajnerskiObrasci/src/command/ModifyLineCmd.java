package command;

import geometry.Line;

public class ModifyLineCmd implements Command {
	private String name;
	private int index;
	private Line oldLine;
	private Line newLine;
	private Line original;
	
	public ModifyLineCmd(Line oldLine, Line newLine, int index, String name) {
		this.oldLine = oldLine;
		this.newLine = newLine;
		this.original = oldLine.clone();
		this.name = name;
		this.index = index;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
	public int getIndex() {
		return this.index;
	}
	
	public String toFileFormat() {
		return "modifyline " + index + " " +
				this.newLine.getStartPoint().getX() + "," + 
				this.newLine.getStartPoint().getY() + "," + 
				this.newLine.getEndPoint().getX() + "," + 
				this.newLine.getEndPoint().getY() + "," + 
				this.newLine.getColor().getRGB() + "," +
				this.newLine.isSelected();
	}
	
	public void execute() {
		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setColor(newLine.getColor());
	}
	
	public void unexecute() {
		oldLine.getStartPoint().setX(original.getStartPoint().getX());
		oldLine.getStartPoint().setY(original.getStartPoint().getY());
		oldLine.getEndPoint().setX(original.getEndPoint().getX());
		oldLine.getEndPoint().setY(original.getEndPoint().getY());
		oldLine.setColor(original.getColor());
	}
}
