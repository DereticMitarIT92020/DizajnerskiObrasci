package gui.modify_dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DlgPointModify extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected JTextField textField_1;
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

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPointModify dialog = new DlgPointModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPointModify() {
		setTitle("POINT (Modify)");
		setBounds(100, 100, 509, 365);
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0,100));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("POINT");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("X Coordinate");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel.add(lblNewLabel_1);
			}
			{
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
				panel.add(textField);
				textField.setColumns(10);
				
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Y Coordinate");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel.add(lblNewLabel_2);
			}
			{
				textField_1 = new JTextField();
				textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				borderColor = new JPanel();
				borderColor.setBackground(Color.BLACK);
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
	
	public void setPoint(Point point) {
		textField.setText(String.valueOf(point.getX()));
		textField_1.setText(String.valueOf(point.getY()));
		borderColor.setBackground(point.getColor());
	}
	
	public boolean validateDialog() {
		try {
			int coordX = Integer.parseInt(textField.getText());
			int coordY = Integer.parseInt(textField_1.getText());
			
			if (coordX < 0 || coordY < 0) {
				throw new Exception();
			}
			
			return true;
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Point getPoint() {
		int coordX = Integer.parseInt(textField.getText());
		int coordY = Integer.parseInt(textField_1.getText());
		Color color = borderColor.getBackground();
		Point p = new Point();
		p.setX(coordX);
		p.setY(coordY);
		p.setColor(color);
		return p;
	}

}
