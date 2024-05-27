package software_lab1;

import java.util.HashMap;
import java.util.Map;

// 有向图类
public class DirectedGraph {
    Map<String, GraphNode> nodes;

    public DirectedGraph() {
        nodes = new HashMap<>();
    }

    public void addNode(String word) {
        if (!nodes.containsKey(word)) {
            nodes.put(word, new GraphNode(word));
        }
    }

    public void addEdge(String word1, String word2) {
        GraphNode node1 = nodes.get(word1);
        GraphNode node2 = nodes.get(word2);
        if (node1 != null && node2 != null) {
            // 在添加边的时候更新权重值
            int weight = 1; // 默认权重为1
            if (node1.getAdjacentNodes().containsKey(node2)) {
            	// node2在node1邻接节点表中
                weight = node1.getAdjacentNodes().get(node2) + 1; // 更新权重值
            }
            node1.addAdjacentNode(node2, weight);
        }
    }

    public GraphNode getNode(String word) {
        return nodes.get(word);
    }
}
