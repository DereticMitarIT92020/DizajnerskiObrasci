package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.CustomShape;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import hexagon.Hexagon;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawingRecord implements Record {
	private DrawingFrame frame;
	private DrawingModel model;
	
	public DrawingRecord() {

	}
	
	public DrawingRecord(DrawingFrame frame, DrawingModel model) {
		this.frame = frame;
		this.model = model;
	}
	
	public void save() {     
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Public\\"));
		jFileChooser.setDialogTitle("Save file");
		
		int result = jFileChooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
				os.writeObject(this.model.getShapes());	
				os.close();
				JOptionPane.showMessageDialog(null,"Successful!", "Success",JOptionPane.INFORMATION_MESSAGE);
			}catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Not found", "Error",JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File not saved", "Error",JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				
			}
		}
	}
	
	public Object load() throws FileNotFoundException {
		ObjectInputStream is;
		try {
			JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Public\\"));
			jFileChooser.setDialogTitle("Open file");
			
			int result = jFileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String path = jFileChooser.getSelectedFile().getAbsolutePath();
	
				is = new ObjectInputStream(new FileInputStream(path));
				for (CustomShape cs : this.model.getShapes()) {
					this.model.remove(cs);
				}
				
				this.model.addShapes((List<CustomShape>)is.readObject());
				
				is.close();
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File not loaded.","Error!",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"File not loaded.","Error!",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return null;
	} 
	
}
