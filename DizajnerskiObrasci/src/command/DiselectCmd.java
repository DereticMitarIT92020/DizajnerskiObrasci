package command;

import java.util.List;

import mvc.DrawingModel;

public class DiselectCmd implements Command {
	private String name;
	private List<Integer> indices;
	private DrawingModel model;
	
	public DiselectCmd(DrawingModel model, List<Integer> indices, String name) {
		this.model = model;
		this.name = name;
		this.indices = indices;
	}
	
	public String getName() {
        return this.name;
    }
	
	public void setName(String name) {
        this.name = name;
    }
	
	// TODO: FIX
	public int getIndex() {
		return this.indices.get(0);
	}
	
	public String toFileFormat() {
		String text = "";
		for (int i = 0; i < indices.size(); i++) {
			text += String.valueOf(indices.get(i));
			if (i < indices.size() - 1) {
				text += ",";
			}
		}
		
		return "diselect " + text;
	}
	
	public void execute() {
		for (Integer index: this.indices) {
			model.getShapes().get(index).setSelected(false);
		}
	}
	
	public void unexecute() {
		for (Integer index: this.indices) {
			model.getShapes().get(index).setSelected(true);
		}
	}
}
