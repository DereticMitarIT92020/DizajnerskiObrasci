package gui;

import java.awt.event.*;    
import java.awt.*;    
import javax.swing.*;
public class ColorChooser extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Color chosenColor = Color.white;
	private boolean forShape;
	private boolean okPressed;

	public Color getChosenColor() {
		return chosenColor;
	}
	public boolean isForShape() {
		return forShape;
	}
	public boolean isOkPressed() {
		return okPressed;
	}
	public void setChosenColor(Color chosenColor) {
		this.chosenColor = chosenColor;
	}
	public void setForShape(boolean forShape) {
		this.forShape = forShape;
	}
	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}

	JButton b;    
    Container c;    
    public ColorChooser(){
	   setTitle("SELECT COLOR");
       c=getContentPane();
       c.setLayout(new FlowLayout());         
       b=new JButton("CHOOSE COLOR");    
       b.addActionListener(this);         
       c.add(b);    
    
    JButton btnNewButton = new JButton("OK");
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		okPressed = true;
    		dispose();
    	}
    });
    getContentPane().add(btnNewButton);
}    
    public void actionPerformed(ActionEvent e) {    
       Color initialcolor = Color.RED;    
       Color color = JColorChooser.showDialog(this,"Select a color",initialcolor);    
       c.setBackground(color);
       chosenColor = color;
    }    
  
    public static void main(String[] args) {    
       ColorChooser ch=new ColorChooser();    
       ch.setSize(400,400);    
       ch.setVisible(true);    
       ch.setDefaultCloseOperation(EXIT_ON_CLOSE);    
    }    
}  