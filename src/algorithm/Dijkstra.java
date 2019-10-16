package algorithm;

import java.util.*;

import main.Node;
import main.Path;

public class Dijkstra {

	private static int MAX = 10000;
	
	public static int[] dijkstra(ArrayList<Node> nodeList, ArrayList<Path> pathList, int start) {
		int[][] weight = new int[nodeList.size()][nodeList.size()];
		
		for (int i = 0; i < nodeList.size(); i++) {
			for (int j = 0; j < nodeList.size(); j++) {
				if (i == j)
					continue;
				weight[i][j] = MAX;
			}
		}
		
		for (int i = 0; i < pathList.size(); i++) {
			Path p = pathList.get(i);
			int a = p.getA();
			int b = p.getB();
			weight[a][b] = p.getLength();
			weight[b][a] = p.getLength();
		}
		
		int[] shortPath = calculate(weight, start);

		return shortPath;
	}

	public static int[] calculate(int[][] weight, int start) {
		int n = weight.length;
		int[] shortPath = new int[n];
		int[] visited = new int[n];

		shortPath[start] = 0;
		visited[start] = 1;
		for (int count = 1; count <= n - 1; count++) {
			int k = -1;
			int dmin = MAX;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			shortPath[k] = dmin;
			visited[k] = 1;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
				}
			}
		}
		return shortPath;
	}
}
