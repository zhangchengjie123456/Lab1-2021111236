package software_lab1;

import java.util.HashMap;
import java.util.Map;

// 节点类
public class GraphNode {
    String word;
    Map<GraphNode, Integer> adjacentNodes; // 保存节点相邻节点的信息和权值

    public GraphNode(String word) {
        this.word = word.toLowerCase();
        adjacentNodes = new HashMap<>();
    }

    public String getWord() {
        return word;
    }

    public Map<GraphNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(GraphNode node, int weight) {
        adjacentNodes.put(node, weight);
    }
}
