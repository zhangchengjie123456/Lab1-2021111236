package software_lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 计算两个单词之间的最短路径
public class calcShortestPath {

    public static String CalcShortestPath(String word1, String word2, DirectedGraph graph) {
        if (!graph.nodes.containsKey(word1) || !graph.nodes.containsKey(word2)) {
            return "输入的单词不可达";
        }

        // 用Dijkstra算法计算最短路径
        Map<GraphNode, Integer> distance = new HashMap<>(); // 存储起始节点到其他节点距离
        Map<GraphNode, GraphNode> parent = new HashMap<>(); // 遍历节点的父节点，用于构建路径
        for (GraphNode node : graph.nodes.values()) {
            distance.put(node, Integer.MAX_VALUE); // 初始化。节点之间不可达
            parent.put(node, null);
        }

        PriorityQueue<NodeDistancePair> pq = new PriorityQueue<>();// 优先队列
        GraphNode startNode = graph.getNode(word1);
        distance.put(startNode, 0);
        pq.add(new NodeDistancePair(startNode, 0));
        
        // 从优先队列中提取具有最小距离的节点
        while (!pq.isEmpty()) {
            NodeDistancePair current = pq.poll();
            GraphNode currentNode = current.node;
            int currentDistance = current.distance;

            if (currentDistance > distance.get(currentNode)) {
                continue;
            }
            
            // 遍历当每个节点的邻接节点
            for (Map.Entry<GraphNode, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                GraphNode nextNode = entry.getKey(); // 邻接节点
                int edgeWeight = entry.getValue();   // 权重
                int newDistance = currentDistance + edgeWeight;
                
                //更新最短距离
                if (newDistance < distance.get(nextNode)) {
                    distance.put(nextNode, newDistance);
                    parent.put(nextNode, currentNode); // 当前节点是相邻节点在最短路径上的父节点
                    pq.add(new NodeDistancePair(nextNode, newDistance));// 确保了最短距离的节点首先被处理
                }
            }
        }

        // 构建最短路径
        List<String> shortestPathWords = new ArrayList<>();
        GraphNode endNode = graph.getNode(word2);
        while (endNode != null) {
            shortestPathWords.add(0, endNode.getWord());// 将终点节点的单词添加到列表头
            endNode = parent.get(endNode);// 从终点向起点找
        }

        // 将路径输出
        StringBuilder pathBuilder = new StringBuilder();
        // 遍历存储最短路径单词的shortestPathWords列表中的所有元素
        for (String word : shortestPathWords) {
            pathBuilder.append(word).append(" → "); // 添加单词和箭头
        }
        pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length()); // 删除最后一个单词后的的" → "
        int shortestPathLength = distance.get(graph.getNode(word2));

        return "最短路径：" + pathBuilder.toString() + "，路径长度：" + shortestPathLength;
    }

    // 辅助类，表示节点与其距离的键值对
    static class NodeDistancePair implements Comparable<NodeDistancePair> {
        GraphNode node;
        int distance;

        NodeDistancePair(GraphNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistancePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
