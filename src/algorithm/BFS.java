package algorithm;

import java.util.*;

import main.Node;
import main.Path;

public class BFS {

	public static LinkedList<Integer> bfs(ArrayList<Node> nodeList, ArrayList<Path> pathList, int start) {
		HashMap<Integer, LinkedList<Integer>> graph = new HashMap<Integer, LinkedList<Integer>>();
		Iterator<Node> nodeIt = nodeList.iterator();
        while(nodeIt.hasNext()) {
        	Node n = nodeIt.next();
        	int m = n.getMark();
        	LinkedList<Integer> linkedNode = new LinkedList<Integer>();
        	for(int i = 0; i < pathList.size(); i++) {
        		Path p = pathList.get(i);
        		int a = p.getA();
        		int b = p.getB();
        		if(m == a) {
        			linkedNode.add(b);
        		}
        		else if(m == b) {
        			linkedNode.add(a);
        		}
        	}
        	if(!linkedNode.isEmpty()) {
        	    graph.put(m, linkedNode);
        	}
        }
        
        LinkedList<Integer> output = new LinkedList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
		
        while (!q.isEmpty()) {
            int top = q.poll();
            int add = 1;
            for(int i = 0; i < output.size(); i++) {
            	if(top == output.get(i)) {
            		add = 0;
                    break;
            	}
            }
            
            if(add == 1)
                output.add(top);
            
            for(Integer i : graph.get(top)) {
                if(!output.contains(i)) {
                    q.add(i);
                }
            }
        }
		return output;
	}
}
