package command;

import adapter.HexagonAdapter;

public class ModifyHexagonCmd implements Command {
	private String name;
	private int index;
	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original;
	
	public ModifyHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon, int index, String name) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
		this.original  = oldHexagon.clone();
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
		return "modifyhexagon " + index + " " + 
				this.newHexagon.getCenterX()+ "," + 
				this.newHexagon.getCenterY() + "," + 
				this.newHexagon.getRadius() + "," +
				this.newHexagon.getBorderColor().getRGB() + "," + 
				this.newHexagon.getShapeColor().getRGB() + "," + 
				this.newHexagon.isSelected();
	}
	
	public void execute() {
		oldHexagon.setCenterX(newHexagon.getCenterX());
		oldHexagon.setCenterY(newHexagon.getCenterY());
		oldHexagon.setRadius(newHexagon.getRadius());
		oldHexagon.setBorderColor(newHexagon.getBorderColor());
		oldHexagon.setShapeColor(newHexagon.getShapeColor());
	}
	
	public void unexecute() {
		oldHexagon.setCenterX(original.getCenterX());
		oldHexagon.setCenterY(original.getCenterY());
		oldHexagon.setRadius(original.getRadius());
		oldHexagon.setBorderColor(original.getBorderColor());
		oldHexagon.setShapeColor(original.getShapeColor());
	}
}
