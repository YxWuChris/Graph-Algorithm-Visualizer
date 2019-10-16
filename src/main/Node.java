package main;

import javafx.fxml.*;
import javafx.scene.layout.Pane;
/*
 * Field and Method of the Node
 */

public class Node {

	@FXML
	private Pane drawPane;
	
	private int x;
	private int y;
	private int m;
	private boolean highlight;
	//private ShowCanvas dr = new ShowCanvas(drawPane, 562, 642); ;
	
	public Node(int nx, int ny, int mark) {
		x = nx;
		y = ny;
		m = mark;
		highlight = false;
	}
	
	public int getX() {
		return x;
	}
	
    public int getY() {
		return y;
	}
    
    public int getMark() {
		return m;
	}
    public void highlight() {
    	//FXMLLoader loader = new FXMLLoader();
    	//GraphicsContext gc ;
    	//dr.drawNode(getGraphicsContext2D(),getX(), getY(),Color.RED);
    }
    
    public boolean isHighlight() {
		return highlight;
	}
}
