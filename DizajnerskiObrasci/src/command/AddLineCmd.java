package command;

import geometry.Line;
import mvc.DrawingModel;

public class AddLineCmd implements Command {
	private String name;
	private int index;
	private Line line;
	private DrawingModel model;
	private String log = "";
	
	public AddLineCmd(Line line, DrawingModel model, int index, String name) {
		this.line = line;
		this.model = model;
		this.index = index;
		this.name = name;
		log = "addline " + 
				this.line.getStartPoint().getX() + "," + 
				this.line.getStartPoint().getY() + "," + 
				this.line.getEndPoint().getX() + "," + 
				this.line.getEndPoint().getY() + "," + 
				this.line.getColor().getRGB() + "," +
				this.line.isSelected();
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
		model.add(line);
	}
	
	public void unexecute() {
		model.getShapes().remove(index);
	}
}

