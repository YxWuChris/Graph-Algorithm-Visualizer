package algorithm;

import java.util.*;

import main.Node;
import main.Path;

public class DFS {
	
    public static LinkedList<Integer> dfs(ArrayList<Node> nodeList, ArrayList<Path> pathList, int start) {
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
    	Stack<Integer> s = new Stack<Integer>();
    	s.push(start);
        	
        while (!s.isEmpty()) {
            int top = s.pop();
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
                    s.push(i);
                }
            }
        }
        return output;
    }
}
