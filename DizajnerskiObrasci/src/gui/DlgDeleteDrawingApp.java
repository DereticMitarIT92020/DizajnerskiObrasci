package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDeleteDrawingApp extends JDialog {
	
	private static final long serialVersionUID = 1L;

	protected boolean wantDelete;

	private final JPanel contentPanel = new JPanel();

	public boolean isWantDelete() {
		return wantDelete;
	}

	public void setWantDelete(boolean wantDelete) {
		this.wantDelete = wantDelete;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDeleteDrawingApp dialog = new DlgDeleteDrawingApp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDeleteDrawingApp() {
		
		setTitle("Delete object (DRAWING)");
		setBounds(100, 100, 450, 150);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		{
			JLabel lblNewLabel = new JLabel("Are you sure that you want to delete selected object?");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("YES");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						wantDelete = true;
						dispose();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("NO");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						wantDelete = false;
						dispose();
						
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		
		
	}

}
