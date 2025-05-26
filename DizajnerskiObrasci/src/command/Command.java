package command;

public interface Command {
	
    void execute();
	
	void unexecute();
	
	String getName();
	
	void setName(String name);
	
	String toFileFormat();
	
	int getIndex();
}
