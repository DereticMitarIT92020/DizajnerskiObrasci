package command;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class AddHexagonCmd implements Command {
	private String name;
	private int index;
	private HexagonAdapter hexagon;
	private DrawingModel model;
	private String log = "";
	
	public AddHexagonCmd(HexagonAdapter hexagon, DrawingModel model, int index ,String name) {
		this.hexagon = hexagon;
		this.model = model;
		this.name = name;
		this.index = index;
		log = "addhexagon " + 
				this.hexagon.getCenterX()+ "," + 
				this.hexagon.getCenterY() + "," + 
				this.hexagon.getRadius() + "," +
				this.hexagon.getBorderColor().getRGB() + "," + 
				this.hexagon.getShapeColor().getRGB() + "," + 
				this.hexagon.isSelected();
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
		model.add(hexagon);
	}
	
	public void unexecute() {
		model.remove(hexagon);
	}
}
