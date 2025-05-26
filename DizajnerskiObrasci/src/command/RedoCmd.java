package command;

import geometry.CustomShape;
import mvc.DrawingModel;

public class RedoCmd implements Command {
	private String name;
	private int index;
	private Command command;
	
	public RedoCmd(String name) {
		this.name = name;
	}
	
	public RedoCmd(Command command, int index, String name) {
		this.command = command;
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
		return "Redo";
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void execute() {
    	command.execute();
	}
	
	public void unexecute() {
		command.unexecute();
	}
}
