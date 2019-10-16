package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

import main.Node;
import main.Path;

public class BellmanFord {

    public static int[] bellmanFord(ArrayList<Node> nodeList, ArrayList<Path> pathList, int start) {
        int distances[] = new int[nodeList.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        for(int j = 0; j < nodeList.size() + 1; j++) {
            for (int i = 0; i < pathList.size(); i++) {
                if (distances[pathList.get(i).getA()] != Integer.MAX_VALUE || distances[pathList.get(i).getB()] != Integer.MAX_VALUE) {
                    if (distances[pathList.get(i).getA()] + pathList.get(i).getLength() < distances[pathList.get(i).getB()] && distances[pathList.get(i).getA()] + pathList.get(i).getLength() > 0)
                        distances[pathList.get(i).getB()] = distances[pathList.get(i).getA()] + pathList.get(i).getLength();
                
                    if (distances[pathList.get(i).getB()] + pathList.get(i).getLength() < distances[pathList.get(i).getA()] && distances[pathList.get(i).getB()] + pathList.get(i).getLength() > 0)
                        distances[pathList.get(i).getA()] = distances[pathList.get(i).getB()] + pathList.get(i).getLength();
                }
            }
        }
        System.out.println(Arrays.toString(distances));
        
        return distances;
    }
}
