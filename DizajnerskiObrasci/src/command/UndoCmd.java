package command;

public class UndoCmd implements Command {
	private String name;
	private int index;
	private Command command;
	
	public UndoCmd(String name) {
		this.name = name;
	}
	
	public UndoCmd(Command command, int index, String name) {
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
		return "Undo";
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void execute() {
    	command.unexecute();
	}
	
	public void unexecute() {
		command.execute();
	}
}
