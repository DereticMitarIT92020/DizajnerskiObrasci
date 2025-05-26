package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgHexagonDrawingApp extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField textField_2;
	protected JTextField textField;
	protected JTextField textField_1;
	private boolean okPressed;
	private boolean borderColorChangePressed;
	private boolean shapeColorChangePressed;

	public boolean isBorderColorChangePressed() {
		return borderColorChangePressed;
	}

	public void setBorderColorChangePressed(boolean borderColorChangePressed) {
		this.borderColorChangePressed = borderColorChangePressed;
	}

	public boolean isShapeColorChangePressed() {
		return shapeColorChangePressed;
	}

	public void setShapeColorChangePressed(boolean shapeColorChangePressed) {
		this.shapeColorChangePressed = shapeColorChangePressed;
	}

	public boolean isOkPressed() {
		return okPressed;
	}

	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}

	public JTextField getTextField_2() {
		return textField_2;
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

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagonDrawingApp dialog = new DlgHexagonDrawingApp(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagonDrawingApp(boolean modifyMode) {
		setTitle("HEXAGON (Drawing)");
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
				JLabel lblNewLabel = new JLabel("HEXAGON");
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
			panel.setLayout(new GridLayout(0, 1, 0, 60));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 2, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new GridLayout(0, 1, 0, 0));
			 if (modifyMode == true){
					{
						JLabel lblNewLabel_1 = new JLabel("Center X");
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
						panel_2.add(lblNewLabel_1);
					}
					{
						textField = new JTextField();
						panel_2.add(textField);
						textField.setColumns(10);
					}
					{
						JLabel lblNewLabel_2 = new JLabel("Center Y");
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
						panel_2.add(lblNewLabel_2);
					}
					{
						textField_1 = new JTextField();
						panel_2.add(textField_1);
						textField_1.setColumns(10);
					}
			}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new GridLayout(0, 1, 0, 0));
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
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton borderColorButton = new JButton("BORDER COLOR");
				JButton shapeColorButton = new JButton("SHAPE COLOR");
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							getRadius();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Invalid data", "Error", JOptionPane.WARNING_MESSAGE);
							return;
							// TODO: handle exception
						}
						
						okPressed = true;
						dispose();
					}
				});
				borderColorButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						borderColorChangePressed = true;
						dispose();
					}
				});
				shapeColorButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						shapeColorChangePressed = true;
						dispose();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				if(modifyMode) {
					buttonPane.add(borderColorButton);
					buttonPane.add(shapeColorButton);
				}
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public int getRadius() {
		return Integer.parseInt(textField_2.getText());
	}
}
