package command;

import geometry.Rectangle;
import mvc.DrawingModel;

public class AddRectCmd implements Command {
	private String name;
	private int index;
	private Rectangle rectangle;
	private DrawingModel model;
	private String log = "";
	
	public AddRectCmd(Rectangle rectangle, DrawingModel model, int index, String name) {
		this.rectangle = rectangle;
		this.model = model;
		this.name = name;
		this.index = index;
		log = "addrectangle " + 
				this.rectangle.getUpperLeftPoint().getX() + "," + 
				this.rectangle.getUpperLeftPoint().getY() + "," + 
				this.rectangle.getWidth() + "," +
				this.rectangle.getHeight() + "," +
				this.rectangle.getBorderColor().getRGB() + "," + 
				this.rectangle.getShapeColor().getRGB() + "," + 
				this.rectangle.isSelected();
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
		model.add(rectangle);
	}
	
	public void unexecute() {
		model.getShapes().remove(index);
	}
}
