package mvc;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import gui.ColorChooser;
import gui.DlgCircleDrawingApp;
import gui.DlgCommandLogDrawingApp;
import gui.DlgDeleteDrawingApp;
import gui.DlgDonutDrawingApp;
import gui.DlgHexagonDrawingApp;
import gui.DlgValidDataMissing;
import hexagon.Hexagon;
import observer.Observer;
import gui.DlgPointDrawingApp;
import gui.DlgRectangleDrawingApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private boolean modifyMode = false;
	
	private JToggleButton tglbtnNewToggleButton = new JToggleButton("POINT");
	private JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("LINE");
	private JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("RECTANGLE");
	private JToggleButton tglbtnNewToggleButton_3 = new JToggleButton("CIRCLE");
	private JToggleButton tglbtnNewToggleButton_4 = new JToggleButton("DONUT");
	private JToggleButton tglbtnNewToggleButton_6 = new JToggleButton("HEXAGON");
	private JToggleButton tglbtnNewToggleButton_5 = new JToggleButton("SELECT");
	private JButton btnNewButton_5 = new JButton("MODIFY");
	private JButton btnNewButton_6 = new JButton("DELETE");
	private JButton btnNewButton_7 = new JButton("UNDO");
	private JButton btnNewButton_8 = new JButton("REDO");
	private JButton btnNewButton_9 = new JButton("");
	private JButton btnNewButton_10 = new JButton("");
	private JButton btnNewButton_11 = new JButton("TO FRONT");
	private JButton btnNewButton_12 = new JButton("TO BACK");
	private JButton btnNewButton_13 = new JButton("BRING TO FRONT");
	private JButton btnNewButton_14 = new JButton("BRING TO BACK");
	private JButton btnNewButton_15 = new JButton("SAVE LOG");
	private JButton btnNewButton_16 = new JButton("SAVE DRAW");
	private JButton btnNewButton_17 = new JButton("READ LOG");
	private JButton btnNewButton_18 = new JButton("READ DRAW");
	private JButton btnNewButton_19 = new JButton("NEXT COMMAND");
	
	private DefaultListModel<String> listModel;
	
	public JToggleButton getPointButton() {
		return tglbtnNewToggleButton;
	}

	public JToggleButton getLineButton() {
		return tglbtnNewToggleButton_2;
	}

	public JToggleButton getRectButton() {
		return tglbtnNewToggleButton_1;
	}

	public JToggleButton getCircleButton() {
		return tglbtnNewToggleButton_3;
	}

	public JToggleButton getDonutButton() {
		return tglbtnNewToggleButton_4;
	}

	public JToggleButton getHexagonButton() {
		return tglbtnNewToggleButton_6;
	}

	public void setTglbtnNewToggleButton(JToggleButton tglbtnNewToggleButton) {
		this.tglbtnNewToggleButton = tglbtnNewToggleButton;
	}

	public void setTglbtnNewToggleButton_2(JToggleButton tglbtnNewToggleButton_2) {
		this.tglbtnNewToggleButton_2 = tglbtnNewToggleButton_2;
	}

	public void setTglbtnNewToggleButton_1(JToggleButton tglbtnNewToggleButton_1) {
		this.tglbtnNewToggleButton_1 = tglbtnNewToggleButton_1;
	}

	public void setTglbtnNewToggleButton_3(JToggleButton tglbtnNewToggleButton_3) {
		this.tglbtnNewToggleButton_3 = tglbtnNewToggleButton_3;
	}

	public void setTglbtnNewToggleButton_4(JToggleButton tglbtnNewToggleButton_4) {
		this.tglbtnNewToggleButton_4 = tglbtnNewToggleButton_4;
	}

	public void setTglbtnNewToggleButton_6(JToggleButton tglbtnNewToggleButton_6) {
		this.tglbtnNewToggleButton_6 = tglbtnNewToggleButton_6;
	}

	public void setTglbtnNewToggleButton_5(JToggleButton tglbtnNewToggleButton_5) {
		this.tglbtnNewToggleButton_5 = tglbtnNewToggleButton_5;
	}
	
	private DlgDeleteDrawingApp deleteObj = new DlgDeleteDrawingApp();
	
	private ColorChooser colorChooser = new ColorChooser();
	private Color currentBorderColor = Color.BLACK;
	private Color currentShapeColor = Color.WHITE;
	
	private DlgValidDataMissing validDataMissingDlg = new DlgValidDataMissing();
	
	private DlgCommandLogDrawingApp commandLogDlg = new DlgCommandLogDrawingApp();
	
	public DlgCommandLogDrawingApp getCommandLogDlg() {
		return commandLogDlg;
	}

	public void setCommandLogDlg(DlgCommandLogDrawingApp commandLogDlg) {
		this.commandLogDlg = commandLogDlg;
	}

	public DlgValidDataMissing getValidDataMissingDlg() {
		return validDataMissingDlg;
	}

	public void setValidDataMissingDlg(DlgValidDataMissing validDataMissingDlg) {
		this.validDataMissingDlg = validDataMissingDlg;
	}

	public JToggleButton getSelectButton() {
		return tglbtnNewToggleButton_5;
	}
	
	public JButton getModifyButton() {
		return btnNewButton_5;
	}
	
	public JButton getDeleteButton() {
		return btnNewButton_6;
	}
	
	public JButton getUndoButton() {
		return btnNewButton_7;
	}

	public JButton getRedoButton() {
		return btnNewButton_8;
	}

	public void setUndoButton(JButton btnNewButton_7) {
		this.btnNewButton_7 = btnNewButton_7;
	}

	public void setRedoButton(JButton btnNewButton_8) {
		this.btnNewButton_8 = btnNewButton_8;
	}
	
	public JButton getToFrontButton() {
		return btnNewButton_11;
	}
	
    public JButton getToBackButton() {
    	return btnNewButton_12;
	}
    
    public JButton getBringToFrontButton() {
    	return btnNewButton_13;
	}
    
    public JButton getBringToBackButton() {
    	return btnNewButton_14;
	}
	
	public JButton getBorderColorButton() {
		return btnNewButton_9;
	}

	public JButton getShapeColorButton() {
		return btnNewButton_10;
	}

	public void setBtnNewButton_9(JButton btnNewButton_9) {
		this.btnNewButton_9 = btnNewButton_9;
	}

	public void setBtnNewButton_10(JButton btnNewButton_10) {
		this.btnNewButton_10 = btnNewButton_10;
	}
	
	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}

	public Color getCurrentShapeColor() {
		return currentShapeColor;
	}

	public void setCurrentBorderColor(Color currentBorderColor) {
		this.currentBorderColor = currentBorderColor;
	}

	public void setCurrentShapeColor(Color currentShapeColor) {
		this.currentShapeColor = currentShapeColor;
	}

	public ColorChooser getColorChooser() {
		return colorChooser;
	}

	public void setColorChooser(ColorChooser colorChooser) {
		this.colorChooser = colorChooser;
	}

	public boolean isModifyMode() {
		return modifyMode;
	}

	public void setModifyMode(boolean modifyMode) {
		this.modifyMode = modifyMode;
	}
	
	public DlgDeleteDrawingApp getDeleteObj() {
		return deleteObj;
	}

	
	public void setDeleteObj(DlgDeleteDrawingApp deleteObj) {
		this.deleteObj = deleteObj;
	}

	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mouseClickedView(e);
			}
		});
		
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_5.isEnabled()) {
					return;
				}
				controller.mouseClickedModifyButton();
			}
		});
		
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_6.isEnabled()) {
					return;
				}
				
				controller.mouseClickedDeleteButton(e);
			}
		});
		
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_7.isEnabled()) {
					return;
				}
				
				controller.mouseClickedUndoButton(e);
			}
		});
		
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_8.isEnabled()) {
					return;
				}
				
				controller.mouseClickedRedoButton(e);
			}
		});
		
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_9.isEnabled()) {
					return;
				}
				
				controller.mouseClickedBorderColorButton(e);
			}
		});
		
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_10.isEnabled()) {
					return;
				}
				
				controller.mouseClickedShapeColorButton(e);
			}
		});
		
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_11.isEnabled()) {
					return;
				}
				
				controller.shapeToFront();
			}
		});
		
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_12.isEnabled()) {
					return;
				}
				
				controller.shapeToBack();
			}
		});
		
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_13.isEnabled()) {
					return;
				}
				
				controller.bringShapeToFront();
			}
		});
		
		btnNewButton_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_14.isEnabled()) {
					return;
				}
				
				controller.bringShapeToBack();
			}
		});
		
		btnNewButton_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_15.isEnabled()) {
					return;
				}
				
				controller.saveCommands();
			}
		});
		
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnNewButton_16.isEnabled()) {
					return;
				}
				
				controller.saveDrawing();
			}
		});
		
		btnNewButton_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_17.isEnabled()) {
					return;
				}
				
				controller.loadCommands();
			}
		});
		
		btnNewButton_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_18.isEnabled()) {
					return;
				}
				
				controller.loadDrawing();
			}
		});
		
		btnNewButton_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!btnNewButton_19.isEnabled()) {
					return;
				}
				
				controller.executeNextCommand();
			}
		});
		
		commandLogDlg.getExecuteButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!commandLogDlg.isEnabled()) {
					return;
				}
				
				controller.logGoDown();
			}
		});
		
		commandLogDlg.getUnexecuteButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.logGoUp();
			}
		});
	}
	
	public void initialize() {			
			
			this.setTitle("DRAWING (Deretic Mitar IT 09-2020)");
			this.setBounds(100, 100, 949, 507);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.getContentPane().setLayout(new BorderLayout(0, 0));
		
			JPanel panel = new JPanel();
			this.getContentPane().add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
		
			buttonGroup.add(tglbtnNewToggleButton);
			tglbtnNewToggleButton.setForeground(Color.WHITE);
			tglbtnNewToggleButton.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton);
			
			buttonGroup.add(tglbtnNewToggleButton_2);
			tglbtnNewToggleButton_2.setForeground(Color.WHITE);
			tglbtnNewToggleButton_2.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton_2.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton_2);
			
			buttonGroup.add(tglbtnNewToggleButton_1);
			tglbtnNewToggleButton_1.setForeground(Color.WHITE);
			tglbtnNewToggleButton_1.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton_1.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton_1);
			
			buttonGroup.add(tglbtnNewToggleButton_3);
			tglbtnNewToggleButton_3.setForeground(Color.WHITE);
			tglbtnNewToggleButton_3.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton_3.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton_3);
			
			buttonGroup.add(tglbtnNewToggleButton_4);
			tglbtnNewToggleButton_4.setForeground(Color.WHITE);
			tglbtnNewToggleButton_4.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton_4.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton_4);
			
			buttonGroup.add(tglbtnNewToggleButton_6);
			tglbtnNewToggleButton_6.setForeground(Color.WHITE);
			tglbtnNewToggleButton_6.setBackground(new Color(220, 20, 60));
			tglbtnNewToggleButton_6.setFont(new Font("Tahoma", Font.BOLD, 19));
			panel.add(tglbtnNewToggleButton_6);
			
			JPanel panelBottom = new JPanel();
			panelBottom.setLayout(new BorderLayout());
			
			JPanel panel_1 = new JPanel();
			this.getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panel_1.setLayout(new GridLayout(0, 4, 0, 0));
			
			panelBottom.add(panel_1, BorderLayout.CENTER);
			
			buttonGroup.add(tglbtnNewToggleButton_5);
			tglbtnNewToggleButton_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
			tglbtnNewToggleButton_5.setEnabled(false);
			panel_1.add(tglbtnNewToggleButton_5);
			
			btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_1.add(btnNewButton_5);
			btnNewButton_5.setEnabled(false);
			
			btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_1.add(btnNewButton_6);
			btnNewButton_6.setEnabled(false);
			
			btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_1.add(btnNewButton_7);
			btnNewButton_7.setEnabled(false);
			
			btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_1.add(btnNewButton_8);
			btnNewButton_8.setEnabled(false);
			
			btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_9.setBackground(this.currentBorderColor);
			panel_1.add(btnNewButton_9);
			
			btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_10.setBackground(this.currentShapeColor);
			panel_1.add(btnNewButton_10);
			
			JPanel panel_2 = new JPanel();
			this.getContentPane().add(panel_2, BorderLayout.NORTH);
			panel_2.setLayout(new GridLayout(0, 4, 0, 0));
			
			btnNewButton_15.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_15.setEnabled(false);
			panel_2.add(btnNewButton_15);
			
			btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_16.setEnabled(false);
			panel_2.add(btnNewButton_16);
			
			btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_2.add(btnNewButton_17);
			
			btnNewButton_18.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			panel_2.add(btnNewButton_18);
			
			btnNewButton_19.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_19.setEnabled(false);
			panel_1.add(btnNewButton_19);
			
			btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_11.setEnabled(false);
			btnNewButton_11.setBackground(Color.WHITE);
			btnNewButton_11.setEnabled(false);
			panel_2.add(btnNewButton_11);
			
			btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_12.setBackground(Color.WHITE);
			btnNewButton_12.setEnabled(false);
			panel_2.add(btnNewButton_12);
			
			btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_13.setBackground(Color.WHITE);
			btnNewButton_13.setEnabled(false);
			panel_2.add(btnNewButton_13);
			
			btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			btnNewButton_14.setBackground(Color.WHITE);
			btnNewButton_14.setEnabled(false);
			panel_2.add(btnNewButton_14);
			
			JList<String> logList = new JList<String>();
			JScrollPane scrollPane = new JScrollPane(logList);
			listModel = new DefaultListModel<String>();
			logList.setModel(listModel);
			panelBottom.add(scrollPane, BorderLayout.SOUTH);
			
			this.getContentPane().add(view, BorderLayout.CENTER);
		}
	
	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	
	public String getCurrentlySelectedShape() {
		if (tglbtnNewToggleButton.isSelected()) {
			return Point.class.toString();
		} else if (tglbtnNewToggleButton_1.isSelected()) {
			return Rectangle.class.toString();
		} else if (tglbtnNewToggleButton_2.isSelected()) {
			return Line.class.toString();
		} else if (tglbtnNewToggleButton_3.isSelected()) {
			return Circle.class.toString();
		} else if (tglbtnNewToggleButton_4.isSelected()) {
			return Donut.class.toString();
		} else if (tglbtnNewToggleButton_6.isSelected()) {
			return Hexagon.class.toString();
		} else {
			return "";
		}
	}

	@Override
	public void update(int createdShapesCount, int selectedShapesCount, int executedCommandsCount,
			int unexecutedCommandsCount, int currentShapeIndex, int commandsFromFileCount) {
		
		if (executedCommandsCount > 0) {
			btnNewButton_7.setEnabled(true);
		} else {
			btnNewButton_7.setEnabled(false);
		}
		
		if (unexecutedCommandsCount > 0) {
			btnNewButton_8.setEnabled(true);
		} else {
			btnNewButton_8.setEnabled(false);
		}
		
		if (createdShapesCount > 0) {
			getSelectButton().setEnabled(true);
		} else {
			getSelectButton().setEnabled(true);
		}
		
		if (selectedShapesCount == 1) {
			getModifyButton().setEnabled(true);
			
			if (currentShapeIndex < createdShapesCount - 1) {
				getToFrontButton().setEnabled(true);
				getBringToFrontButton().setEnabled(true);
			} else {
				getToFrontButton().setEnabled(false);
				getBringToFrontButton().setEnabled(false);
			}
			
			if (currentShapeIndex > 0) {
				getToBackButton().setEnabled(true);
				getBringToBackButton().setEnabled(true);
			} else {
				getToBackButton().setEnabled(false);
				getBringToBackButton().setEnabled(false);
			}
			
			
		} else {
			getModifyButton().setEnabled(false);
			getToFrontButton().setEnabled(false);
			getToBackButton().setEnabled(false);
			getBringToFrontButton().setEnabled(false);
			getBringToBackButton().setEnabled(false);
		}
		
		if (selectedShapesCount >= 1) {
			getDeleteButton().setEnabled(true);
		} else {
			getDeleteButton().setEnabled(false);
		}
		
		if (createdShapesCount > 0) {
			btnNewButton_16.setEnabled(true);
		} else {
			btnNewButton_16.setEnabled(false);
		}
		
		if (executedCommandsCount > 0 || unexecutedCommandsCount > 0) {
			btnNewButton_15.setEnabled(true);
		} else {
			btnNewButton_15.setEnabled(false);
		}
		
		if (commandsFromFileCount > 0) {
			btnNewButton_19.setEnabled(true);
		} else {
			btnNewButton_19.setEnabled(false);
		}
	}
	
	public void printLog(String log) {
		listModel.addElement(log);
	}
}
