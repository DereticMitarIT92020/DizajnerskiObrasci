package command;

import geometry.Rectangle;

public class ModifyRectCmd implements Command {
	private String name;
	private int index;
	private Rectangle oldRect;
	private Rectangle newRect;
	private Rectangle original;
	
	public ModifyRectCmd(Rectangle oldRect, Rectangle newRect, int index, String name) {
		this.oldRect = oldRect;
		this.newRect = newRect;
		this.original = oldRect.clone();
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
		return "modifyrectangle " + index + " " + 
				this.newRect.getUpperLeftPoint().getX() + "," + 
				this.newRect.getUpperLeftPoint().getY() + "," + 
				this.newRect.getWidth() + "," +
				this.newRect.getHeight() + "," +
				this.newRect.getBorderColor().getRGB() + "," + 
				this.newRect.getShapeColor().getRGB() + "," + 
				this.newRect.isSelected();
	}
	
	public void execute() {
		oldRect.getUpperLeftPoint().setX(newRect.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(newRect.getUpperLeftPoint().getY());
		oldRect.setWidth(newRect.getWidth());
		oldRect.setHeight(newRect.getHeight());
		oldRect.setBorderColor(newRect.getBorderColor());
		oldRect.setShapeColor(newRect.getShapeColor());
	}
	
	public void unexecute() {
		oldRect.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldRect.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldRect.setWidth(original.getWidth());
		oldRect.setHeight(original.getHeight());
		oldRect.setBorderColor(original.getBorderColor());
		oldRect.setShapeColor(original.getShapeColor());
	}
}