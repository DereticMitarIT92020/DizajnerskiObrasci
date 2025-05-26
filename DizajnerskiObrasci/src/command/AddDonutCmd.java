package command;

import geometry.Donut;
import mvc.DrawingModel;

public class AddDonutCmd implements Command {
	private String name;
	private int index;
	private Donut donut;
	private DrawingModel model;
	private String log = "";
	
	public AddDonutCmd(Donut donut, DrawingModel model, int index, String name) {
		this.donut = donut;
		this.model = model;
		this.name = name;
		this.index = index;
		log = "adddonut " + 
				this.donut.getCenter().getX() + "," + 
				this.donut.getCenter().getY() + "," + 
				this.donut.getRadius() + "," +
				this.donut.getInnerRadius() + "," +
				this.donut.getBorderColor().getRGB() + "," + 
				this.donut.getShapeColor().getRGB() + "," + 
				this.donut.isSelected();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
	public int getIndex() {
		return this.index;
	}
	
	public String toFileFormat() {
		return log;
	}
	
	public void execute() {
		model.add(donut);
	}
	
	public void unexecute() {
		model.getShapes().remove(index);
	}
}
