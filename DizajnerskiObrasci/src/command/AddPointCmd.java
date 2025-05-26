package command;

import geometry.Point;
import mvc.DrawingModel;

public class AddPointCmd implements Command {
	private String name;
	private int index;
	private Point point;
	private DrawingModel model;
	private String log = "";
	
	public AddPointCmd(Point point, DrawingModel model, int index, String name) {
		this.point = point;
		this.model = model;
		this.name = name;
		this.index = index;
		log = "addpoint " + 
				this.point.getX() + "," + 
				this.point.getY() + "," + 
				this.point.getColor().getRGB() + "," +
				this.point.isSelected();
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
		return log;
	}
	
	public void execute() {
		model.add(point);
	}
	
	public void unexecute() {
		model.remove(point);
	}
}
