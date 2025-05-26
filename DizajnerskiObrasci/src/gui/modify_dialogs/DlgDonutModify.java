package gui.modify_dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Point;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDonutModify extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField textField_2;
	protected JTextField textField_3;
	protected JTextField textField;
	protected JTextField textField_1;
	private boolean okPressed;
	private JTextField xCoord;
	private JTextField yCoord;
	private JPanel borderColor; 
	private JPanel insideColor; 
	
	public boolean isOkPressed() {
		return okPressed;
	}

	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}


	public DlgDonutModify() {
		setTitle("DONUT(Modify)");
		setBounds(100, 100, 526, 347);
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0,0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("DONUT");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 1, 0, 30));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel_5 = new JLabel("X");
					panel_1.add(lblNewLabel_5);
				}
				{
					xCoord = new JTextField();
					xCoord.setText("");
					panel_1.add(xCoord);
					xCoord.setColumns(10);
				}
				{
					JLabel lblNewLabel_6 = new JLabel("Y");
					panel_1.add(lblNewLabel_6);
				}
				{
					yCoord = new JTextField();
					panel_1.add(yCoord);
					yCoord.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 2, 0, 0));
				{
					borderColor = new JPanel();
					borderColor.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							Color color =  JColorChooser.showDialog(null, "SELECT BORDER COLOR", null);
							if (color != null) {
							  borderColor.setBackground(color);
							}	
						}
					});
					borderColor.setBackground(new Color(0, 0, 0));
					panel_1.add(borderColor);
					borderColor.setLayout(new GridLayout(0, 1, 0, 0));
				}
				{
					insideColor = new JPanel();
					insideColor.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							Color color =  JColorChooser.showDialog(null, "SELECT INNER COLOR", null);
							if (color != null) {
							  insideColor.setBackground(color);
							}	
						}
					});
					insideColor.setBackground(new Color(255, 255, 255));
					panel_1.add(insideColor);
					insideColor.setLayout(new GridLayout(0, 1, 0, 0));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNewLabel_3 = new JLabel("Radius");
					lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel_1.add(lblNewLabel_3);
				}
				{
					textField_2 = new JTextField();
					textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
					panel_1.add(textField_2);
					textField_2.setColumns(10);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Inner radius");
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel_1.add(lblNewLabel_4);
				}
				{
					textField_3 = new JTextField();
					textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
					panel_1.add(textField_3);
					textField_3.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okPressed = true;
						dispose();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public int getRadius() {
		return Integer.parseInt(textField_2.getText());
	}
	
	public int getInnerRadius() {
		return Integer.parseInt(textField_3.getText());
	}

	public void setDonut(Donut donut) {
		textField_2.setText(String.valueOf(donut.getRadius()));
		textField_3.setText(String.valueOf(donut.getInnerRadius()));
		xCoord.setText(String.valueOf(donut.getCenter().getX()));
		yCoord.setText(String.valueOf(donut.getCenter().getY()));
		borderColor.setBackground(donut.getBorderColor());
		insideColor.setBackground(donut.getShapeColor());
	}
	
	public boolean validateDialog() {
		try {
			int coordX = Integer.parseInt(xCoord.getText());
			int coordY = Integer.parseInt(yCoord.getText());
			int radius = Integer.parseInt(textField_2.getText());
			int innerRadius = Integer.parseInt(textField_3.getText());
			
			if (coordX < 0 || coordY < 0 || radius < 0 || innerRadius < 0 ) {
				throw new Exception();
			}
			return true;
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Donut getDonut() {
		int coordX = Integer.parseInt(xCoord.getText());
		int coordY = Integer.parseInt(yCoord.getText());
		int radius = Integer.parseInt(textField_2.getText());
		int innerRadius = Integer.parseInt(textField_3.getText());
		Color border = borderColor.getBackground();
		Color inside = insideColor.getBackground();
		Donut donut = new Donut();
		donut.setCenter(new Point (coordX, coordY));
		try {
		donut.setRadius(radius);
		donut.setInnerRadius(innerRadius);
		} catch (Exception e) {
		}
		donut.setBorderColor(border);
		donut.setShapeColor(inside);
		return donut;
	}

	
}
