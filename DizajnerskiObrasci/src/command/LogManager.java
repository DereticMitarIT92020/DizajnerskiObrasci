package command;

import java.util.ArrayList;
import java.util.List;

import mvc.DrawingFrame;

public class LogManager {
	private DrawingFrame frame;
	private ArrayList<Command> logList = new ArrayList<Command>();
	private ArrayList<String> logTextList = new ArrayList<String>();
	private int pointer;
	
	public LogManager(DrawingFrame frame) {
		this.frame = frame;
	}
	
	public void setLogList(ArrayList<Command> commands, ArrayList<String> logsText) {
		this.logList = commands;
		this.logTextList = logsText;
		pointer = logList.size() - 1;
		setLogView();
	}
	
	public ArrayList<String> getAllLogText() {
		return logTextList;
	}
    
	private void setLogView() {
		
		for (String txt : logTextList) 
		{ 
			frame.getCommandLogDlg().getCommandModel().addElement(txt);
		}
		frame.getCommandLogDlg().getCommandList().setSelectedIndex(pointer);
	}
	
    public void insert(Command command, String logText) {
    	logList.add(command);
    	logTextList.add(logText);
    	frame.getCommandLogDlg().getCommandModel().addElement(logText);
    	frame.getCommandLogDlg().getCommandList().setSelectedIndex(++pointer);
    }
	
	public void goUp() {
		if (pointer == -1) {
			return;
		}
		Command command = logList.get(pointer);
		String commandText = logTextList.get(pointer);
		boolean isUndo = commandText.split(" ")[0].contains("Undo");
		System.out.println("Command name: " + command.getName());
		System.out.println("isUndo: " + isUndo);
		if(isUndo) 
			command.execute();
		else
		    command.unexecute();
		
		if (pointer == 0) 
			frame.getCommandLogDlg().getCommandList().clearSelection();

		pointer--;
		frame.getCommandLogDlg().getCommandList().setSelectedIndex(pointer);
		frame.repaint();
	}
	
	public void goDown() {
		if (pointer == logList.size() - 1) 
			return;

		pointer++;
		Command command = logList.get(pointer);
		String commandText = logTextList.get(pointer);
		
		boolean isUndo = commandText.split(" ")[0].contains("Undo");
		System.out.println("Command name: " + command.getName());
		System.out.println("isUndo: " + isUndo);
		
		if(isUndo) 
			command.unexecute();
		else
			command.execute();
		
		frame.getCommandLogDlg().getCommandList().setSelectedIndex(pointer);
		
		frame.repaint();
	}
	
	public boolean isOnBottom() {
		
		if (logList.size() == 0) 
			return true;
		
		boolean bottom = pointer == logList.size() - 1;
		System.out.println("On bottom: " + bottom);
		System.out.println("Pointer: " + pointer);
		System.out.println("Log size : " + logList.size());
		return pointer == logList.size() - 1;
	}
	
	public int getExecutedCommandsCount() {
		return pointer + 1;
	}
	
	public List<Command> getAllCommands(){
		return logList;
	}
}
