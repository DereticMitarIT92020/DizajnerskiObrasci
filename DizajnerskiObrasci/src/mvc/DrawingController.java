package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;

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
import gui.DlgCircleDrawingApp;
import gui.DlgDonutDrawingApp;
import gui.DlgHexagonDrawingApp;
import gui.DlgRectangleDrawingApp;
import gui.modify_dialogs.DlgCircleModify;
import gui.modify_dialogs.DlgDonutModify;
import gui.modify_dialogs.DlgHexagonModify;
import gui.modify_dialogs.DlgLineModify;
import gui.modify_dialogs.DlgPointModify;
import gui.modify_dialogs.DlgRectangleModify;
import hexagon.Hexagon;
import observer.Observable;
import observer.Observer;
import strategy.CommandLogRecord;
import strategy.DrawingRecord;
import strategy.RecordManager;
import visitor.ShapeVisitor;
import visitor.ValidationVisitor;

public class DrawingController implements Observable {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point lineOriginPoint = null;
	private List<Observer> observers = new ArrayList<Observer>();
	private List<Command> commandsForUndo = new ArrayList<Command>();
	private List<Command> commandsForRedo = new ArrayList<Command>();
	private List<Command> allCommands = new ArrayList<Command>();
	private List<String> commandsFromFile = new ArrayList<String>();
	
	ShapeVisitor validationVisitor = new ValidationVisitor();
   
	LogManager logManager;
	
	CommandLogRecord commandLogRecord;
	DrawingRecord drawingRecord;
	RecordManager recordManager = new RecordManager();
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		
		commandLogRecord = new CommandLogRecord(this, allCommands);
		drawingRecord = new DrawingRecord(frame, model);
		
	}
	
	public void mouseClickedView(MouseEvent e) {
		
		String currentlySelectedShape = frame.getCurrentlySelectedShape();
		if (currentlySelectedShape.equals("")) {
			performSelect(e.getX(), e.getY());
		} else {
			boolean isLineOriginPointEmpty = lineOriginPoint == null;
			drawShape(e.getX(), e.getY(), currentlySelectedShape);
			if (isLineOriginPointEmpty && lineOriginPoint != null) {
				return;
			}
		}
		
		doPostActionWork();
	}
	
	public void performSelect(int x, int y) {
		List<CustomShape> selectedShapes = new ArrayList<CustomShape>();
		for (CustomShape shape: this.model.getShapes()) {
			if (shape.isSelected()) {
				selectedShapes.add(shape);
			}
		}
		
		CustomShape clickedShape = null;
		for (CustomShape shape : this.model.getShapes()) {
			if (shape.contains(x,y)) {
				clickedShape = shape;
			}
		}

		if (clickedShape == null) {
			if ( selectedShapes.size() > 0) {
				List<Integer> toDiselect = new ArrayList<Integer>();
				String text = "";
				for (CustomShape shape : selectedShapes) {
					toDiselect.add(this.model.getShapes().indexOf(shape));
					text += shape.toString() + "$";
				}
				DiselectCmd command = new DiselectCmd(model, toDiselect , "Diselect " + text);
				command.execute();
				commandsForUndo.add(command);
				commandsForRedo.clear();
				allCommands.add(command);
				frame.printLog(command.getName());
			} else {
				return;
			}
		} else if (clickedShape.isSelected()) {
			List<Integer> toDiselect = new ArrayList<Integer>();
			toDiselect.add(this.model.getShapes().indexOf(clickedShape));
			DiselectCmd command = new DiselectCmd(model, toDiselect , "Diselect " + clickedShape.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		} else {
			int index = this.model.getShapes().indexOf(clickedShape);
			SelectCmd command = new SelectCmd(model, index, "Select " + clickedShape.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		}
		
		doPostActionWork();
	}
	
	public void drawShape(int x, int y, String nameOfShape) {
		if (nameOfShape.equals(Point.class.toString())) {
			drawPoint(x, y);
		} else if (nameOfShape.equals(Line.class.toString())) {
			drawLine(x, y);
		} else if (nameOfShape.equals(Rectangle.class.toString())) {
			drawRectangle(x, y);
		}  else if (nameOfShape.equals(Circle.class.toString())) {
			drawCircle(x, y);
		} else if (nameOfShape.equals(Donut.class.toString())) {
			drawDonut(x, y);
		} else if (nameOfShape.equals(Hexagon.class.toString())) {
			drawHexagon(x,y);
		}
	}
	
	public void drawPoint(int x, int y) {
		
		Point p = Point.builder().x(x).y(y).color(this.frame.getCurrentBorderColor()).build();
		int index = this.model.getShapes().size();
		AddPointCmd command = new AddPointCmd(p, model, index, "Draw Point " + p.toString());
		command.execute();
		commandsForUndo.add(command);
		commandsForRedo.clear();
		allCommands.add(command);
		frame.printLog(command.getName());
	}
	
	public void drawLine(int x, int y) {
		if (lineOriginPoint == null) {
			lineOriginPoint = new Point(x,y);
			return;
		}
		
		Point p = new Point();
		p.setX(x);
		p.setY(y);
		
		int index = this.model.getShapes().size();
		Line line = Line.builder().startPoint(lineOriginPoint).endPoint(p).color(this.frame.getCurrentBorderColor()).build();
		AddLineCmd command = new AddLineCmd(line, model, index, "Draw Line " + line.toString());
		command.execute();
		commandsForUndo.add(command);
		commandsForRedo.clear();
		allCommands.add(command);
		frame.printLog(command.getName());
	}
	
	public void drawRectangle(int x, int y) {
		DlgRectangleDrawingApp dialog = new DlgRectangleDrawingApp(false);
		dialog.setVisible(true);
		
		int index = this.model.getShapes().size();
		if (dialog.isOkPressed()) {
			
			Rectangle rectangle = Rectangle.builder().upperLeftPoint(new Point (x,y)).width(dialog.getRectWidth())
					.height(dialog.getRectHeight()).borderColor(this.frame.getCurrentBorderColor())
					.shapeColor(this.frame.getCurrentShapeColor()).build();
			AddRectCmd command = new AddRectCmd(rectangle, model, index, "Rectangle " + rectangle.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		}
		
	}
	
	public void drawCircle(int x, int y) {
		DlgCircleDrawingApp dialog = new DlgCircleDrawingApp(false);
		dialog.setVisible(true);
		
		int index = this.model.getShapes().size();
		if (dialog.isOkPressed()) {
			
			Circle circle = Circle.builder().center(new Point (x,y)).radius(dialog.getRadius()).borderColor(this.frame.getCurrentBorderColor())
					.shapeColor(this.frame.getCurrentShapeColor()).build();
			AddCircleCmd command = new AddCircleCmd(circle, model, index, "Circle " + circle.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		}
		
	}
	
	public void drawDonut(int x, int y) {
		DlgDonutDrawingApp dialog = new DlgDonutDrawingApp(false);
		dialog.setVisible(true);
		
		int index = this.model.getShapes().size();
		if (dialog.isOkPressed()) {
			
			Donut donut = Donut.donutBuilder().center(new Point (x,y)).radius(dialog.getRadius()).innerRadius(dialog.getInnerRadius())
					.borderColor(this.frame.getCurrentBorderColor()).shapeColor(this.frame.getCurrentShapeColor()).build();
			AddDonutCmd command = new AddDonutCmd(donut, model, index, "Donut " + donut.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		}
	}
	
	public void drawHexagon(int x, int y) {
		DlgHexagonDrawingApp dialog = new DlgHexagonDrawingApp(false);
		dialog.setVisible(true);
		
		int index = this.model.getShapes().size();
		if (dialog.isOkPressed()) {
		
			HexagonAdapter hexagon = HexagonAdapter.builder().x(x).y(y).r(dialog.getRadius()).borderColor(this.frame.getCurrentBorderColor())
					.shapeColor(this.frame.getCurrentShapeColor()).build();
			AddHexagonCmd command = new AddHexagonCmd(hexagon, model, index, "Hexagon " + hexagon.toString());
			command.execute();
			commandsForUndo.add(command);
			commandsForRedo.clear();
			allCommands.add(command);
			frame.printLog(command.getName());
		}
	}
	
	public void doPostActionWork() {
		
		notifyObservers();
		frame.repaint();
		
		lineOriginPoint = null;
	}
	
	public void mouseClickedUndoButton(MouseEvent e) {
		Command command = commandsForUndo.get(commandsForUndo.size() - 1);
		commandsForUndo.remove(commandsForUndo.size() - 1);
		commandsForRedo.add(command);
		command.unexecute();
		allCommands.add(new UndoCmd(""));
		frame.printLog("Undo command " + command.getName());
		doPostActionWork();
	}
	
    public void mouseClickedRedoButton(MouseEvent e) {
    	Command command = commandsForRedo.get(commandsForRedo.size() - 1);
		commandsForRedo.remove(commandsForRedo.size()-1);
		commandsForUndo.add(command);
		command.execute();
		allCommands.add(new RedoCmd(""));
		frame.printLog("Redo command " + command.getName());
		doPostActionWork();    	
	}
	public void shapeToBack() {
		if (frame.getToBackButton().isEnabled()) {
		  CustomShape selectedShape = this.returnSelectedShape();
		  Command toBackShapeCmd = new ToBackShapeCmd(selectedShape, model, model.getShapes().indexOf(selectedShape), "To back " + selectedShape.toString());
		  frame.repaint();
		  toBackShapeCmd.execute();
		  commandsForUndo.add(toBackShapeCmd);
		  commandsForRedo.clear();
		  allCommands.add(toBackShapeCmd);
		  frame.printLog(toBackShapeCmd.getName());
		  doPostActionWork();
		}
	}
	
	public void shapeToFront() {
		if (frame.getToFrontButton().isEnabled()) {
		 CustomShape selectedShape = this.returnSelectedShape();
		 Command toFrontShapeCmd = new ToFrontShapeCmd(selectedShape, model, model.getShapes().indexOf(selectedShape), "To front " + selectedShape.toString());
		 toFrontShapeCmd.execute();
		 commandsForUndo.add(toFrontShapeCmd);
		 commandsForRedo.clear();
		 allCommands.add(toFrontShapeCmd);
		 frame.printLog(toFrontShapeCmd.getName());
		 doPostActionWork();
	    }
	}
	
    public void bringShapeToBack() {
    	if(frame.getBringToBackButton().isEnabled()) {
    		CustomShape selectedShape = this.returnSelectedShape();
    		Command bringToBackShapeCmd = new BringToBackShapeCmd(selectedShape, model, model.getShapes().indexOf(selectedShape), "Bring shape to back " + selectedShape.toString());
    		bringToBackShapeCmd.execute();
   		 	commandsForUndo.add(bringToBackShapeCmd);
   		 	commandsForRedo.clear();
   		 	allCommands.add(bringToBackShapeCmd);
   		 	frame.printLog(bringToBackShapeCmd.getName());
   		    doPostActionWork();
    	}
	}
	
	public void bringShapeToFront() {
		if(frame.getBringToFrontButton().isEnabled()) {
			CustomShape selectedShape = this.returnSelectedShape();
    		Command bringToFrontShapeCmd = new BringToFrontShapeCmd(selectedShape, model, model.getShapes().indexOf(selectedShape), "Bring shape to front " + selectedShape.toString());
    		bringToFrontShapeCmd.execute();
   		 	commandsForUndo.add(bringToFrontShapeCmd);
   		 	commandsForRedo.clear();
   		 	allCommands.add(bringToFrontShapeCmd);
   		 	frame.printLog(bringToFrontShapeCmd.getName());
   		    doPostActionWork();
		}	
	}
    
	private CustomShape returnSelectedShape() {
		CustomShape selectedShape = null;
		for(int i = 0; i < model.getShapes().size(); i++) {
    		if (model.getShapes().get(i).isSelected()) {
    			selectedShape = model.getShapes().get(i);
    		}
    	}
		
		return selectedShape;
	}
	
	
	public void loadCommands() {
		recordManager.setRecord(commandLogRecord);
		try {
			commandsFromFile = (List<String>) recordManager.load();
		} catch (FileNotFoundException e) {
			 //TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		doPostActionWork();
	}
	
    public void saveCommands() {
		recordManager.setRecord(commandLogRecord);
	    recordManager.save();
	}
	
	public void loadDrawing() {
		recordManager.setRecord(drawingRecord);
		try {
			recordManager.load();
			doPostActionWork();
		} catch (FileNotFoundException e) {
			 //TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		doPostActionWork();
	}
	
	public void saveDrawing() {;
		recordManager.setRecord(drawingRecord);
	    recordManager.save();
	}
	
	public void logGoUp() {
		logManager.goUp();
	}
	
	public void logGoDown() {
		logManager.goDown();
	}
	
	public void mouseClickedModifyButton() {
		CustomShape selectedShape = null;
		for (CustomShape cs: this.model.getShapes()) {
			if (cs.isSelected()) {
				selectedShape = cs;
				break;
			}
		}
		
		if (selectedShape instanceof Point) {
			DlgPointModify dlg = new DlgPointModify();
			dlg.setPoint((Point) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getPoint().accept(validationVisitor)) {
					Point point = dlg.getPoint();
					ModifyPointCmd command = new ModifyPointCmd((Point) selectedShape, point, this.model.getShapes().indexOf((Point)selectedShape), "Modify Point");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		} else if (selectedShape instanceof Line) {
			DlgLineModify dlg = new DlgLineModify();
			dlg.setLine((Line) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getLine().accept(validationVisitor)) {
					Line line = dlg.getLine();
					ModifyLineCmd command = new ModifyLineCmd((Line) selectedShape, line, this.model.getShapes().indexOf((Line)selectedShape), "Modify Line");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		} else if (selectedShape instanceof Rectangle) {
			DlgRectangleModify dlg = new DlgRectangleModify();
			dlg.setRectangle((Rectangle) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getRectangle().accept(validationVisitor)) {
					Rectangle rectangle = dlg.getRectangle();
					ModifyRectCmd command = new ModifyRectCmd((Rectangle) selectedShape, rectangle, this.model.getShapes().indexOf((Rectangle)selectedShape), "Modify Rectangle");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		} else if (selectedShape instanceof HexagonAdapter) {
			DlgHexagonModify dlg = new DlgHexagonModify();
			dlg.setHexagon((HexagonAdapter) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getHexagon().accept(validationVisitor)) {
					HexagonAdapter hexagon = dlg.getHexagon();
					ModifyHexagonCmd command = new ModifyHexagonCmd((HexagonAdapter) selectedShape, hexagon, this.model.getShapes().indexOf((HexagonAdapter)selectedShape), "Modify Hexagon");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		} else if (selectedShape instanceof Donut) {
			DlgDonutModify dlg = new DlgDonutModify();
			dlg.setDonut((Donut) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getDonut().accept(validationVisitor)) {
					Donut donut = dlg.getDonut();
					ModifyDonutCmd command = new ModifyDonutCmd((Donut) selectedShape, donut, this.model.getShapes().indexOf((Donut)selectedShape), "Modify Donut");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		} else if (selectedShape instanceof Circle) {
			DlgCircleModify dlg = new DlgCircleModify();
			dlg.setCircle((Circle) selectedShape);
			dlg.setVisible(true);
			
			if (dlg.isOkPressed()) {
				if (dlg.getCircle().accept(validationVisitor)) {
					Circle circle = dlg.getCircle();
					ModifyCircleCmd command = new ModifyCircleCmd((Circle) selectedShape, circle, this.model.getShapes().indexOf((Circle)selectedShape), "Modify Circle");
					command.execute();
					allCommands.add(command);
					frame.printLog(command.getName());
					commandsForRedo.clear();
					commandsForUndo.add(command);
				} else {
					frame.getValidDataMissingDlg().setVisible(true);
				};
			}
		}
		
		doPostActionWork();
		
	}

	public void mouseClickedDeleteButton(MouseEvent e) {
		List<CustomShape> shapes = this.model.getShapes();
		List<CustomShape> selectedShapes = new ArrayList<CustomShape>();
		for (CustomShape shape: shapes) {
			if (shape.isSelected()) {
				selectedShapes.add(shape);
			}
		}
		
		String text = "";
		for (CustomShape s : shapes) {
			text += s.toString();
		}
		
		RemoveShapesCmd command = new RemoveShapesCmd(selectedShapes, 0, model, "Remove " + text);
		command.execute();
		commandsForUndo.add(command);
		commandsForRedo.clear();
		allCommands.add(command);
		frame.printLog(command.getName());
		doPostActionWork();
		
	}

	@Override
	public void addObservers(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObservers(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		
		int createdShapesCount = 0;
		int selectedShapesCount = 0;
		int executedCommandsCount = commandsForUndo.size();
		int unexecutedCommandsCount = commandsForRedo.size();
		int currentShapePosition = 0;
		int commandsFromFileCount = commandsFromFile.size();
		
		for (CustomShape shape : this.model.getShapes()) {
			createdShapesCount++;
			if (shape.isSelected()) {
				selectedShapesCount++;
				currentShapePosition = this.model.getShapes().indexOf(shape);
			}
		}
		
		for (Observer observer : this.observers) {	
			observer.update(createdShapesCount, selectedShapesCount, executedCommandsCount, unexecutedCommandsCount, currentShapePosition, commandsFromFileCount);
		}
		
	}
	
	// Color buttons actions 
    
    public void mouseClickedBorderColorButton(MouseEvent e) {
    	Color color =  JColorChooser.showDialog(this.frame, "SELECT BORDER COLOR", null);
		if (color != null) {
		  frame.setCurrentBorderColor(color);
		  frame.getBorderColorButton().setBackground(color);
		}	
		
    	frame.repaint();
	}

    public void mouseClickedShapeColorButton(MouseEvent e) {
    	Color color =  JColorChooser.showDialog(this.frame, "SELECT SHAPE COLOR", null);
		if (color != null) {
		  frame.setCurrentShapeColor(color);
		  frame.getShapeColorButton().setBackground(color);
		}	
    	
    	frame.repaint();
    }
    
    public void executeNextCommand() {
    	String row = commandsFromFile.get(0);
    	commandsFromFile.remove(0);
    	
    	if (row.startsWith("addpoint")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x = Integer.parseInt(properties[0]);
    		int y = Integer.parseInt(properties[1]);
    		Color color = new Color(Integer.parseInt(properties[2]));
    		Point point = new Point();
    		point.setX(x);
    		point.setY(y);
    		point.setColor(color);
    		AddPointCmd command = new AddPointCmd(point, model, model.getShapes().size(), row);
    		command.execute();
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    		frame.printLog(row);
    	} else if (row.startsWith("addline")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int x2 = Integer.parseInt(properties[2]);
    		int y2 = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Line line = new Line();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		Point end = new Point();
    		end.setX(x2);
    		end.setY(y2);
    		line.setStartPoint(start);
    		line.setEndPoint(end);
    		line.setColor(color);
    		AddLineCmd command = new AddLineCmd(line, model, model.getShapes().size(), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("addcircle")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		Color color = new Color(Integer.parseInt(properties[3]));
    		Color colorInside = new Color(Integer.parseInt(properties[4]));
    		Circle circle = new Circle();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		circle.setCenter(start);
    		try {
    		circle.setRadius(radius);
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		circle.setBorderColor(color);
    		circle.setShapeColor(colorInside);
    		AddCircleCmd command = new AddCircleCmd(circle, model, model.getShapes().size(), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("adddonut")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		int innerRadius = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Color colorInside = new Color(Integer.parseInt(properties[5]));
    		Donut donut = new Donut();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		donut.setCenter(start);
    		try {
    			donut.setRadius(radius);
    			donut.setInnerRadius(innerRadius);
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		donut.setBorderColor(color);
    		donut.setShapeColor(colorInside);
    		AddDonutCmd command = new AddDonutCmd(donut, model, model.getShapes().size(), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("addrectangle")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int height = Integer.parseInt(properties[2]);
    		int width = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Color colorInside = new Color(Integer.parseInt(properties[5]));
    		Rectangle rectangle = new Rectangle();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		rectangle.setUpperLeftPoint(start);
    		rectangle.setHeight(height);
    		rectangle.setWidth(width);
    		
    		rectangle.setBorderColor(color);
    		rectangle.setShapeColor(colorInside);
    		AddRectCmd command = new AddRectCmd(rectangle, model, model.getShapes().size(), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("addhexagon")) {
    		String[] parts = row.split(" ");
    		String[] properties = parts[1].split(",");
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		Color color = new Color(Integer.parseInt(properties[3]));
    		Color colorInside = new Color(Integer.parseInt(properties[4]));
    		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(x1, y1, radius));
    		hexagon.setBorderColor(color);
    		hexagon.setShapeColor(colorInside);
    		AddHexagonCmd command = new AddHexagonCmd(hexagon, model, model.getShapes().size(), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("select")) { 
    		String[] parts = row.split(" ");
    		SelectCmd command = new SelectCmd(model, Integer.parseInt(parts[1]), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("diselect")) { 
    		String[] parts = row.split(" ");
    		String[] indices = parts[1].split(",");
    		List<Integer> numberIndices = new ArrayList<Integer>();
    		for (String index : indices) {
    			numberIndices.add(Integer.parseInt(index));
    		}
    		DiselectCmd command = new DiselectCmd(model, numberIndices, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("remove")) { 
    		List<CustomShape> shapes = this.model.getShapes();
    		List<CustomShape> selectedShapes = new ArrayList<CustomShape>();
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShapes.add(shape);
    			}
    		}
    		
    		RemoveShapesCmd command = new RemoveShapesCmd(selectedShapes, 0, model, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifypoint")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x = Integer.parseInt(properties[0]);
    		int y = Integer.parseInt(properties[1]);
    		Color color = new Color(Integer.parseInt(properties[2]));
    		Point point = new Point();
    		point.setX(x);
    		point.setY(y);
    		point.setColor(color);
    		
    		ModifyPointCmd command = new ModifyPointCmd((Point)selectedShape, point, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifyline")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int x2 = Integer.parseInt(properties[2]);
    		int y2 = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Line line = new Line();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		Point end = new Point();
    		end.setX(x2);
    		end.setY(y2);
    		line.setStartPoint(start);
    		line.setEndPoint(end);
    		line.setColor(color);
    		
    		ModifyLineCmd command = new ModifyLineCmd((Line)selectedShape, line, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifycircle")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		Color color = new Color(Integer.parseInt(properties[3]));
    		Color colorInside = new Color(Integer.parseInt(properties[4]));
    		Circle circle = new Circle();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		circle.setCenter(start);
    		try {
    		circle.setRadius(radius);
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		circle.setBorderColor(color);
    		circle.setShapeColor(colorInside);
    		ModifyCircleCmd command = new ModifyCircleCmd((Circle)selectedShape, circle, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifydonut")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		int innerRadius = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Color colorInside = new Color(Integer.parseInt(properties[5]));
    		Donut donut = new Donut();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		donut.setCenter(start);
    		try {
    			donut.setRadius(radius);
    			donut.setInnerRadius(innerRadius);
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		donut.setBorderColor(color);
    		donut.setShapeColor(colorInside);
    		
    		ModifyDonutCmd command = new ModifyDonutCmd((Donut)selectedShape, donut, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifyhexagon")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int radius = Integer.parseInt(properties[2]);
    		Color color = new Color(Integer.parseInt(properties[3]));
    		Color colorInside = new Color(Integer.parseInt(properties[4]));
    		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(x1, y1, radius));
    		hexagon.setBorderColor(color);
    		hexagon.setShapeColor(colorInside);
    		ModifyHexagonCmd command = new ModifyHexagonCmd((HexagonAdapter)selectedShape, hexagon, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("modifyrectangle")) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		String[] parts = row.split(" ");
    		String[] properties = parts[2].split(",");
    		
    		int x1 = Integer.parseInt(properties[0]);
    		int y1 = Integer.parseInt(properties[1]);
    		int height = Integer.parseInt(properties[2]);
    		int width = Integer.parseInt(properties[3]);
    		Color color = new Color(Integer.parseInt(properties[4]));
    		Color colorInside = new Color(Integer.parseInt(properties[5]));
    		Rectangle rectangle = new Rectangle();
    		Point start = new Point();
    		start.setX(x1);
    		start.setY(y1);
    		rectangle.setUpperLeftPoint(start);
    		rectangle.setHeight(height);
    		rectangle.setWidth(width);
    		
    		rectangle.setBorderColor(color);
    		rectangle.setShapeColor(colorInside);
    		ModifyRectCmd command = new ModifyRectCmd((Rectangle)selectedShape,rectangle, 0, row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("bringtofront") ) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		BringToFrontShapeCmd command = new BringToFrontShapeCmd(selectedShape, model, this.model.getShapes().indexOf(selectedShape), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("bringtoback") ) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		BringToBackShapeCmd command = new BringToBackShapeCmd(selectedShape, model, this.model.getShapes().indexOf(selectedShape), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("tofront") ) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		ToFrontShapeCmd command = new ToFrontShapeCmd(selectedShape, model, this.model.getShapes().indexOf(selectedShape), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("toback") ) {
    		List<CustomShape> shapes = this.model.getShapes();
    		CustomShape selectedShape = null;
    		for (CustomShape shape: shapes) {
    			if (shape.isSelected()) {
    				selectedShape = shape;
    			}
    		}
    		
    		ToBackShapeCmd command = new ToBackShapeCmd(selectedShape, model, this.model.getShapes().indexOf(selectedShape), row);
    		command.execute();
    		frame.printLog(row);
    		commandsForUndo.add(command);
   		 	commandsForRedo.clear();
   		 	allCommands.add(command);
    	} else if (row.startsWith("Undo")) {
    		mouseClickedUndoButton(null);
    	} else if (row.startsWith("Redo")) {
    		mouseClickedRedoButton(null);
    	}
    	 
    	doPostActionWork();
    }
 	
}