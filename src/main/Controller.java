package main;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import algorithm.BFS;
import algorithm.BellmanFord;
import algorithm.DFS;
import algorithm.Dijkstra;


public class Controller {

	@FXML
	private AnchorPane editPage;
	
	@FXML
	private WebView cshock;
	
	@FXML
	private Pane drawPane;
	
	private ShowCanvas patternCanvas;
	private ShowCanvas shadow;
	
	@FXML
	private Pane drawPane2;
	
	private ShowCanvas patternCanvas2;
	private ShowCanvas shadow2;
	
	@FXML
	private ToggleGroup Add;
	
	@FXML
	private RadioButton addNode;
	
	@FXML
	private RadioButton addNode2;
	
	@FXML
	private RadioButton addPath;
	
	@FXML
	private RadioButton addPath2;
	
	@FXML
	private Button run;
	
	@FXML
	private Button run2;
    
	@FXML
	private TableView<Variable> table;
	
	@FXML
	private TableColumn<Variable, String> name;
	
	@FXML
	private TableColumn<Variable, String> value;
	
	public ArrayList<Node> nodeList = new ArrayList<Node>();
	public ArrayList<Path> pathList = new ArrayList<Path>();
	public LinkedList<Integer> output = new LinkedList<Integer>();
	public LinkedList<Character> output2 = new LinkedList<Character>();
	public int[] result;
	public int startNode;
	public int x, y, x1, x2, y1, y2, op;
	public char[] charAs;
	public char startpoint;
	public Iterator<Integer> ot;
	public char ans;
	/* Open the main page 
	 * of different algorithm
	 * 
	 * 
	 */
	public void initialize() {
		WebEngine webEngine = cshock.getEngine();
		if(op == 1) {
			webEngine.load(getClass().getResource("code_bfs.html").toExternalForm());
		}
		else if(op == 2) {
			webEngine.load(getClass().getResource("code_dfs.html").toExternalForm());
		}
        else if(op == 3) {
        	webEngine.load(getClass().getResource("code_dijk.html").toExternalForm());
		}
        else if(op == 4) {
        	webEngine.load(getClass().getResource("code_bellmanford.html").toExternalForm());
        }
 

		name.setCellValueFactory(cellData -> cellData.getValue().getName());
        value.setCellValueFactory(cellData -> cellData.getValue().getValue());
		patternCanvas = new ShowCanvas(drawPane, 562, 642);
		patternCanvas.setOnMouseClicked(e -> {
			x = (int)Math.floor(e.getX() / 40);
		    y = (int)Math.floor(e.getY() / 40);
		    
		    if(addNode.isSelected() && !addPath.isSelected()) {
		    	int n = findNode(x, y); 

			    if(n < 0) {
			    	patternCanvas.drawNode(x, y);
			        nodeList.add(new Node(x, y, nodeList.size()));
			    }
			}
		});
		
		patternCanvas.setOnMousePressed(e -> {
			if(!addNode.isSelected() && addPath.isSelected()) {
			    x1 = (int)Math.floor(e.getX() / 40);
			    y1 = (int)Math.floor(e.getY() / 40);
			    shadow = new ShowCanvas(drawPane, 562, 642);
			}
		});
		
		patternCanvas.setOnMouseDragged(e -> {
			if(!addNode.isSelected() && addPath.isSelected()) {
			    double xs = e.getX();
		        double ys = e.getY();
		        shadow.clearCanvas();
		        shadow.drawShadow(x1, y1, xs, ys);
			}
		});
		
		patternCanvas.setOnMouseReleased(e ->{
			if(!addNode.isSelected() && addPath.isSelected()) {
			    x2 = (int)Math.floor(e.getX() / 40);
			    y2 = (int)Math.floor(e.getY() / 40);
			    drawPane.getChildren().remove(shadow);
				int a = findNode(x1, y1);
				int b = findNode(x2, y2);

			    if(a >= 0 && b >= 0 && a != b) {
			    	int l;
			    	if (op == 7)
			    		l = patternCanvas.drawDirection(x1, y1, x2, y2);
			    	else
			    		l = patternCanvas.drawLine(x1, y1, x2, y2, op);
			    	pathList.add(new Path(a, b, l, pathList.size()));
			    }
			}
		});
		
		if(op != 7) {
		    patternCanvas2 = new ShowCanvas(drawPane2, 562, 642);
		    patternCanvas2.setOnMouseClicked(e -> {
			    x = (int)Math.floor(e.getX() / 40);
		        y = (int)Math.floor(e.getY() / 40);
		    
		        if(addNode2.isSelected() && !addPath2.isSelected()) {
		    	    int n = findNode(x, y); 
		    	
		    	    if(n < 0) {
		    	        patternCanvas2.drawNode(x, y);
		                nodeList.add(new Node(x, y, nodeList.size()));
		    	    }
			    }
		    });
		
		    patternCanvas2.setOnMousePressed(e -> {
		        if(!addNode2.isSelected() && addPath2.isSelected()) {
		            x1 = (int)Math.floor(e.getX() / 40);
		             y1 = (int)Math.floor(e.getY() / 40);
		             shadow2 = new ShowCanvas(drawPane2, 562, 642);
		        }
		    });
		
		    patternCanvas2.setOnMouseDragged(e -> {
		        if(!addNode2.isSelected() && addPath2.isSelected()) {
		            double xs = e.getX();
		            double ys = e.getY();
		            shadow2.clearCanvas();
		            shadow2.drawShadow(x1, y1, xs, ys);
		        }
		    });
		
		    patternCanvas2.setOnMouseReleased(e ->{
		        if(!addNode2.isSelected() && addPath2.isSelected()) {
		            x2 = (int)Math.floor(e.getX() / 40);
		            y2 = (int)Math.floor(e.getY() / 40);
		            drawPane2.getChildren().remove(shadow2);
		            int a = findNode(x1, y1); 
		            int b = findNode(x2, y2);

		            if(a >= 0 && b >= 0 && a != b) {
		                int l = patternCanvas2.drawLine(x1, y1, x2, y2, op);
		                pathList.add(new Path(a, b, l, pathList.size()));
		            }
		        }
		    });
		}
	}
	
    public void changeToBFS() {
    	op = 1;
    	initialize();
    	reset();
	}
	
	public void changeToDFS() {
		op = 2;
		initialize();
		reset();
	}
	
	public void changeToDijkstra() {
    	op = 3;
    	initialize();
    	reset();
	}
	
	public void changeToBellmanFord() {
		op = 4;
		initialize();
		reset();
	}
	
	/*
	 * go back to previous page
	 */
	public void goBack() {
		editPage.getScene().getWindow().hide();
		Main.recover();
	}
	
	/*
	 * Find the specific id of node
	 */
	
	public int findNode(int x, int y) {
		Iterator<Node> nodeIt = nodeList.iterator();
		while(nodeIt.hasNext()) {
		    Node n = nodeIt.next();
		    int nx = n.getX();
		    int ny = n.getY();
		    if(nx == x && ny == y)
		    	return n.getMark();
		}
		return -1;
	}
	
	
	public Node getNode(int id) {
		Iterator<Node> nodeIt = nodeList.iterator();
		while(nodeIt.hasNext()) {
		    Node n = nodeIt.next();
		    int mark = n.getMark();
		    if(mark == id)
		    	return n;
		}
		return null;
	}
	
	public void calculate() {
		if(!nodeList.isEmpty() && !pathList.isEmpty()) {
			try {
				if (op != 5 && op != 6 && op != 7) {
				    TextInputDialog dialog = new TextInputDialog();
				    dialog.setTitle("Text Input Dialog");
				    dialog.setHeaderText("Please input the start node");
				    Optional<String> start = dialog.showAndWait();
				   
					if(start.isPresent()) {
				      charAs = start.get().toCharArray();
				      startpoint = charAs[0];
				      startNode = (int)startpoint-65;
				    }
				    else {
					    throw new Exception();
				    }
				
				    if(getNode(startNode) == null) {
					    throw new Exception();
				    }
				}
			}
			catch(Exception e) {
				Alert _alert = new Alert(Alert.AlertType.INFORMATION);
	            _alert.setTitle("Error");
	            _alert.setHeaderText("Warning");
	            _alert.setContentText("Invalid start node, please check again!");
	            _alert.show();
	            return;
			}
			/*
			 * Different algorithm caculate the result
			 */
		    switch(op) {
		    case 1:
		        output = BFS.bfs(nodeList, pathList, startNode);
		        ot = output.iterator();
		    	while(ot.hasNext()) {
				    	int n = ot.next();
				    	ans = (char)(n+65);
				    	output2.add(ans);
		    	}
		        
		        ObservableList<Variable> data1 = FXCollections.observableArrayList();
		        data1.add(new Variable("result", output2.toString()));
		        table.setItems(data1);
		        Timeline timeline = new Timeline();
		        for(int i = 0; i < output.size(); i++) {
				    int a = output.get(i);
				    Iterator<Node> nodeIt = nodeList.iterator(); 
				    while(nodeIt.hasNext()) {
				    	Node n = nodeIt.next();
				    	if(n.getMark()== a) {
						    int x = n.getX();
					    	int y = n.getY();
				    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i), e-> visualizeNode(x, y)));
			    		}
			    	}
			    }
		        timeline.play();
		        break;
		
		    case 2:
		    	output = DFS.dfs(nodeList, pathList, startNode);
		    	ot = output.iterator();
		    	while(ot.hasNext()) {
				    	int n = ot.next();
				    	ans = (char) (n + 65);
				    	output2.add(ans);
		    	}
		    	ObservableList<Variable> data2 = FXCollections.observableArrayList();
		        data2.add(new Variable("result", output2.toString()));
		        table.setItems(data2);
		        Timeline timeline2 = new Timeline();
		        for(int i = 0; i < output.size(); i++) {
			    	int a = output.get(i);
			    	Iterator<Node> nodeIt = nodeList.iterator(); 
				    while(nodeIt.hasNext()) {
				    	Node n = nodeIt.next();
				    	if(n.getMark()== a) {
				    		int x = n.getX();
				    		int y = n.getY();
				    		timeline2.getKeyFrames().add(new KeyFrame(Duration.seconds(i), e-> visualizeNode(x, y)));
				    	}
				    }
			    }
		        timeline2.play();
		        break;
		    
		    case 3:
		        result = Dijkstra.dijkstra(nodeList, pathList, startNode);
		        ObservableList<Variable> data3 = FXCollections.observableArrayList();
		        data3.add(new Variable("shortest path from start node to others", Arrays.toString(result)));
		        table.setItems(data3);
		        Timeline timeline3 = new Timeline();
		        for(int i = 0; i < result.length; i++) {
		        	int distance = result[i];
		        	if(startNode != i) {
			    	    Node a = getNode(startNode);
			    	    Node b = getNode(i);
			    	    int ax = a.getX();
			    	    int ay = a.getY();
			    	    int bx = b.getX();
			    	    int by = b.getY();
			    	    timeline3.getKeyFrames().add(new KeyFrame(Duration.seconds(i), e-> visualizePath(ax, ay, bx, by, distance)));
		        	}
			    }
		        timeline3.play();
		        break;
		    
		    case 4:
		        result = BellmanFord.bellmanFord(nodeList, pathList, startNode);
		        
		        ObservableList<Variable> data4 = FXCollections.observableArrayList();
		        data4.add(new Variable("shortest path from start node to others", Arrays.toString(result)));
		        table.setItems(data4);
		        Timeline timeline4 = new Timeline();
		        for(int i = 0; i < result.length; i++) {
		        	int distance = result[i];
		        	if(startNode != i) {
			    	    Node a = getNode(startNode);
			    	    Node b = getNode(i);
			    	    int ax = a.getX();
			    	    int ay = a.getY();
			    	    int bx = b.getX();
			    	    int by = b.getY();
			    	    timeline4.getKeyFrames().add(new KeyFrame(Duration.seconds(i), e-> visualizePath(ax, ay, bx, by, distance)));
		        	}
			    }
		        timeline4.play();
		        break;
		    
		  
		
			

		    }
		}
		else {
			Alert _alert = new Alert(Alert.AlertType.INFORMATION);
	        _alert.setTitle("Error");
	        _alert.setHeaderText("Warning");
	        _alert.setContentText("Invalid input graph, please check again!");
	        _alert.show();
		}
	}
	
	public void visualizeNode(int x, int y) {
		if(op % 2 == 1)
            patternCanvas.drawNodeTrace(x, y);
		else
			patternCanvas2.drawNodeTrace(x, y);
	}
	
	public void visualizePath(int ax, int ay, int bx, int by, int distance) {
		if(op % 2 == 1)
            patternCanvas.drawPathTrace(ax, ay, bx, by, distance);
		else
			patternCanvas2.drawPathTrace(ax, ay, bx, by, distance);
	}
	
	public void reset() {
		patternCanvas.clearCanvas();
		if(op != 7)
		patternCanvas2.clearCanvas();
		nodeList.clear();
		pathList.clear();
		output.clear();
	}
	
	public void save() {
    	FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            try {
            	WritableImage writableImage;
            	if(op % 2 == 1) {
                    writableImage = drawPane.snapshot(new SnapshotParameters(), null);
            	}
            	else {
            	    writableImage = drawPane2.snapshot(new SnapshotParameters(), null);
            	}
            	RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
