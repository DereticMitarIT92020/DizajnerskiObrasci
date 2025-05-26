package command;

import mvc.DrawingModel;

public class SelectCmd implements Command {
	private String name;
	private int index;
	private DrawingModel model;
	
	public SelectCmd(DrawingModel model, int index, String name) {
		this.model = model;
		this.name = name;
		this.index = index;
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
		return "select " + getIndex();
	}
	
	public void execute() {
    	model.getShapes().get(index).setSelected(true);
	}
	
	public void unexecute() {
		model.getShapes().get(index).setSelected(false);
	}
}
