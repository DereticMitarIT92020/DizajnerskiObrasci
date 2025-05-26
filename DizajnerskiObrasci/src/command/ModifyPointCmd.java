package command;

import geometry.Point;

public class ModifyPointCmd implements Command {
	private String name;
	private int index;
	private Point oldPoint;
	private Point newPoint;
	private Point original;
	
	public ModifyPointCmd(Point oldPoint, Point newPoint, int index, String name) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
		this.original = oldPoint.clone();
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
		return "modifypoint " + index  + " " + 
				this.newPoint.getX() + "," + 
				this.newPoint.getY() + "," + 
				this.newPoint.getColor().getRGB() + "," + 
				this.newPoint.isSelected();
	}
	
	public void execute() {
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		oldPoint.setColor(newPoint.getColor());
	}
	
	public void unexecute() {
		oldPoint.setX(original.getX());
		oldPoint.setY(original.getY());
		oldPoint.setColor(original.getColor());
	}
}
