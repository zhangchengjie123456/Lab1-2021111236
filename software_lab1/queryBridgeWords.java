package software_lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 查询桥接词类
public class queryBridgeWords {

    public static List<String> findBridgeWords(DirectedGraph graph, String word1, String word2) {
        List<String> bridgeWords = new ArrayList<>();
        if (!graph.nodes.containsKey(word1) || !graph.nodes.containsKey(word2)) {
        	//不存在
            return bridgeWords;
        }

        GraphNode node1 = graph.getNode(word1);
        GraphNode node2 = graph.getNode(word2);

        // 获取与word1相邻的节点
        Map<GraphNode, Integer> adjacentWords1 = node1.getAdjacentNodes();
        for (GraphNode adjacentNode : adjacentWords1.keySet()) {
            // 如果相邻节点也与word2相邻，则该相邻节点是桥接词
            if (adjacentNode.getAdjacentNodes().containsKey(node2)) {
                bridgeWords.add(adjacentNode.getWord());
            }
        }
        return bridgeWords;
    }
}
