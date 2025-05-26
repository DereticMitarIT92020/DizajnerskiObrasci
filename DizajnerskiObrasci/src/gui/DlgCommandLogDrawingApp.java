package gui;


import java.awt.EventQueue;

import geometry.Point;
import geometry.Rectangle;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class DlgCommandLogDrawingApp extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmStackdereticMitar;
	private JList commandList;
	JButton btnNewButton = new JButton("UNEXECUTE");
	JButton btnNewButton_1 = new JButton("EXECUTE");

	private DefaultListModel<String> commandModel = new DefaultListModel<String>();

	ArrayList valList = new ArrayList();
	
	public JList getCommandList() {
		return commandList;
	}

	public void setCommandList(JList commandList) {
		this.commandList = commandList;
	}

	public DefaultListModel<String> getCommandModel() {
		return commandModel;
	}

	public void setCommandModel(DefaultListModel<String> commandModel) {
		this.commandModel = commandModel;
	}

	public JFrame getFrmStackdereticMitar() {
		return frmStackdereticMitar;
	}

	public void setFrmStackdereticMitar(JFrame frmStackdereticMitar) {
		this.frmStackdereticMitar = frmStackdereticMitar;
	}
	
	public JButton getUnexecuteButton() {
		return btnNewButton;
	}

	public JButton getExecuteButton() {
		return btnNewButton_1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgCommandLogDrawingApp window = new DlgCommandLogDrawingApp();
					//window.frmStackdereticMitar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DlgCommandLogDrawingApp() {
		initialize();
	}
	
	
	public void initialize() {
		frmStackdereticMitar = new JFrame();
		frmStackdereticMitar.setTitle("Command log (Deretic Mitar IT 09-2020)");
		//frmStackdereticMitar.setVisible(true);
		frmStackdereticMitar.setBounds(100, 100, 350, 448);
		frmStackdereticMitar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStackdereticMitar.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(247, 250, 220));
		frmStackdereticMitar.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("COMMAND LOG");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 27));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(247, 250, 220));
		frmStackdereticMitar.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setMargin(new Insets(15, 15, 15, 15));
		panel_1.add(btnNewButton);
		
		
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1 .setMargin(new Insets(15, 15, 15, 15));
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		commandList = new JList();
		scrollPane.setViewportView(commandList);
		commandList.setModel(commandModel);
		frmStackdereticMitar.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
