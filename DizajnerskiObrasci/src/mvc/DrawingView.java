
package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.CustomShape;

public class DrawingView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();
	
	public DrawingView() {
		setBackground(Color.white);
		super.repaint();
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		Iterator<CustomShape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	} 
}
