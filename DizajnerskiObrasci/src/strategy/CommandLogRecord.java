package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.AddCircleCmd;
import command.AddDonutCmd;
import command.AddHexagonCmd;
import command.AddLineCmd;
import command.AddPointCmd;
import command.AddRectCmd;
import command.BringToBackShapeCmd;
import command.BringToFrontShapeCmd;
import command.Command;
import command.DiselectCmd;
import command.LogManager;
import command.ModifyCircleCmd;
import command.ModifyDonutCmd;
import command.ModifyHexagonCmd;
import command.ModifyLineCmd;
import command.ModifyPointCmd;
import command.ModifyRectCmd;
import command.RedoCmd;
import command.RemoveShapesCmd;
import command.SelectCmd;
import command.ToBackShapeCmd;
import command.ToFrontShapeCmd;
import command.UndoCmd;
import geometry.Circle;
import geometry.CustomShape;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CommandLogRecord implements Record {
	private List<Command> commands;
	private DrawingController controller;
	
	
	public CommandLogRecord() {

	}
	
	public CommandLogRecord(DrawingController controller, List<Command> commands) {
		controller = controller;
		this.commands = commands;
	}
	
    public void save() {
    	JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Public\\"));
		jFileChooser.setDialogTitle("Save file");
		int result = jFileChooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath() + ".log";
			try {
				FileWriter fw = new FileWriter(path);
				for(Command c: commands) {
					fw.write(c.toFileFormat() + System.lineSeparator());
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Saving failed.", "Failed!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
    
	public Object load() throws FileNotFoundException {
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Public\\"));
		jFileChooser.setDialogTitle("Open file");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Text file", "log"));
		
		List<String> list = new ArrayList<String>();
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				BufferedReader buffer = new BufferedReader(new FileReader(path));
				
				String line;
				while((line = buffer.readLine()) != null) {
					list.add(line);
				}
				
				buffer.close();
				
				return list;
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Format problem","Error",JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"File not loaded.","Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		return null;
	}
}
