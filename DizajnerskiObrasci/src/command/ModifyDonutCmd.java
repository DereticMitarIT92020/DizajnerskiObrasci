package command;

import geometry.Donut;

public class ModifyDonutCmd implements Command {
	private String name;
	private int index;
	private Donut newDonut;
	private Donut oldDonut;
	private Donut original;
	
	public ModifyDonutCmd(Donut oldDonut, Donut newDonut, int index, String name) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
		this.original = oldDonut.clone();
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
		return "modifydonut " + index + " " +
				this.newDonut.getCenter().getX() + "," + 
				this.newDonut.getCenter().getY() + "," + 
				this.newDonut.getRadius() + "," +
				this.newDonut.getInnerRadius() + "," +
				this.newDonut.getBorderColor().getRGB() + "," + 
				this.newDonut.getShapeColor().getRGB() + "," + 
				this.newDonut.isSelected();
	}
	
	public void execute() {
		try {
			oldDonut.setRadius(newDonut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setInnerRadius(newDonut.getInnerRadius());
		oldDonut.setCenter(newDonut.getCenter());
		oldDonut.setBorderColor(newDonut.getBorderColor());
		oldDonut.setShapeColor(newDonut.getShapeColor());
	}
	
	public void unexecute() {
		try {
			oldDonut.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setInnerRadius(original.getInnerRadius());
		oldDonut.setCenter(original.getCenter());
		oldDonut.setBorderColor(original.getBorderColor());
		oldDonut.setShapeColor(original.getShapeColor());
	}
}
