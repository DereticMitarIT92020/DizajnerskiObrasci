package command;

import geometry.Circle;

public class ModifyCircleCmd implements Command {
	private String name;
	private int index;
	private Circle oldCircle;
	private Circle newCircle;
	private Circle original;
	
	public ModifyCircleCmd(Circle oldCircle, Circle newCircle, int index, String name) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
		this.original = oldCircle.clone();
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
		return "modifycircle " + index + " " + 
				this.newCircle.getCenter().getX() + "," + 
				this.newCircle.getCenter().getY() + "," + 
				this.newCircle.getRadius() + "," + 
				this.newCircle.getBorderColor().getRGB() + "," + 
				this.newCircle.getShapeColor().getRGB() + "," + 
				this.newCircle.isSelected();
	}
	
	public void execute() {
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setCenter(newCircle.getCenter());
		oldCircle.setBorderColor(newCircle.getBorderColor());
		oldCircle.setShapeColor(newCircle.getShapeColor());
	}
	
	public void unexecute() {
		try {
			oldCircle.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setCenter(original.getCenter());
		oldCircle.setBorderColor(original.getBorderColor());
		oldCircle.setShapeColor(original.getShapeColor());
	}
}
