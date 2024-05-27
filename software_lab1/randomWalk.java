package software_lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

// 随机游走类
public class randomWalk {

    public static String RandomWalk(DirectedGraph graph) {
        if (graph.nodes.isEmpty()) { // 检查图是否为空
            return null;
        }

        List<String> visitedNodes = new ArrayList<>();
        List<String> visitedEdges = new ArrayList<>();
        Random rand = new Random();
        GraphNode currentNode = getRandomNode(graph, rand);
        visitedNodes.add(currentNode.getWord());

        try {
        	FileWriter writer = new FileWriter("C:\\Users\\M\\Desktop\\random_walk.txt");
            while (true) {
                Map<GraphNode, Integer> adjacentNodes = currentNode.getAdjacentNodes();
                
                // 如果当前节点不存在出边，则终止随机游走
                if (adjacentNodes.isEmpty()) {
                    break;
                }
                List<GraphNode> nodesList = new ArrayList<>(adjacentNodes.keySet());
                GraphNode nextNode = nodesList.get(rand.nextInt(nodesList.size()));

                // 获取当前节点到下一个节点的边
                String edge = currentNode.getWord() + " -> " + nextNode.getWord();
                
                // 如果已经访问过该边，则终止随机游走
                if (visitedEdges.contains(edge)) {
                    break;
                }

                // 记录访问的节点和边
                visitedNodes.add(nextNode.getWord());
                visitedEdges.add(edge);
                // 更新当前节点
                currentNode = nextNode;
            }
            writer.write(returnResult(visitedNodes));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnResult(visitedNodes);
    }
    
    // 随机获取节点
    private static GraphNode getRandomNode(DirectedGraph graph, Random rand) {
        List<GraphNode> nodesList = new ArrayList<>(graph.nodes.values());
        if (nodesList.isEmpty()) {
            return null; // 图中无节点
        }
        return nodesList.get(rand.nextInt(nodesList.size()));
    }

    // 输出游走序列
    private static String returnResult(List<String> visitedNodes) {
        StringBuilder result = new StringBuilder();
        for (String node : visitedNodes) {
            result.append(node).append(" ");
        }
        return result.toString();
    }
}

