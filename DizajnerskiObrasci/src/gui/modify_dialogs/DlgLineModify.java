package gui.modify_dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DlgLineModify extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected JTextField textField_1;
	protected JTextField textField_2;
	protected JTextField textField_3;
	private boolean okPressed;
	private JPanel borderColor;

	public boolean isOkPressed() {
		return okPressed;
	}

	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLineModify dialog = new DlgLineModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLineModify() {
		setTitle("LINE (Modify)");
		setBounds(100, 100, 505, 345);
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0,100));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("LINE");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				borderColor = new JPanel();
				borderColor.setBackground(new Color(0, 0, 0));
				borderColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Color color =  JColorChooser.showDialog(null, "SELECT BORDER COLOR", null);
						if (color != null) {
						  borderColor.setBackground(color);
						}	
					}
				});
				panel.add(borderColor);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("Start point X");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel_1.add(lblNewLabel_1);
				}
				{
					textField = new JTextField();
					textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
					panel_1.add(textField);
					textField.setColumns(10);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Start point Y");
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel_1.add(lblNewLabel_2);
				}
				{
					textField_1 = new JTextField();
					textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					panel_1.add(textField_1);
					textField_1.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNewLabel_3 = new JLabel("End point X");
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
					JLabel lblNewLabel_4 = new JLabel("End point Y");
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
	
	public void setLine(Line line) {
		textField.setText(String.valueOf(line.getStartPoint().getX()));
		textField_1.setText(String.valueOf(line.getStartPoint().getY()));
		textField_2.setText(String.valueOf(line.getEndPoint().getX()));
		textField_3.setText(String.valueOf(line.getEndPoint().getY()));
		borderColor.setBackground(line.getColor());
	}
	
	public boolean validateDialog() {
		try {
			int coordXStart = Integer.parseInt(textField.getText());
			int coordYStart = Integer.parseInt(textField_1.getText());
			int coordXEnd = Integer.parseInt(textField_2.getText());
			int coordYEnd = Integer.parseInt(textField_3.getText());
			
			if (coordXStart < 0 || coordYStart < 0 || coordXEnd < 0 || coordYEnd < 0) {
				throw new Exception();
			}
			
			return true;
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Line getLine() {
		int coordXStart = Integer.parseInt(textField.getText());
		int coordYStart = Integer.parseInt(textField_1.getText());
		int coordXEnd = Integer.parseInt(textField_2.getText());
		int coordYEnd = Integer.parseInt(textField_3.getText());
		Color color = borderColor.getBackground();
		Line line = new Line();
		line.setStartPoint(new Point (coordXStart, coordYStart));
		line.setEndPoint(new Point (coordXEnd, coordYEnd));
		line.setColor(color);
		return line;
	}

}
