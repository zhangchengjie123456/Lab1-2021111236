package software_lab1;

import java.util.List;
import java.util.Scanner;

// 主函数
public class Main {

    public static void main(String[] args) {
        // 输入文本，转换为有向图
        String filePath;
        DirectedGraph graph = null;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("请输入文本文件路径：");
            filePath = scanner.nextLine();
            graph = TextToDirectedGraph.createDirectedGraphFromFile(filePath);

            System.out.println("图的节点数：" + graph.nodes.size());
            int edgeCount = 0;
            for (GraphNode node : graph.nodes.values()) {
                edgeCount += node.getAdjacentNodes().size();
            }
            System.out.println("图的边数：" + edgeCount);
            // 可视化
            showDirectedGraph.visualizeGraph(graph);
                       
            while (true) {
                System.out.println("请输入数字选择功能：");
                System.out.println("1. 查询桥接词");
                System.out.println("2. 生成新文本");
                System.out.println("3. 计算两个单词之间的最短路径");
                System.out.println("4. 随机游走");
                System.out.print("请输入选项：");
                String option = scanner.nextLine();

                switch (option) {
                	// 查询桥接词
                    case "1":
                        System.out.println("请输入两个单词，查询它们的桥接词：");
                        System.out.print("word1：");
                        String word1 = scanner.nextLine();
                        System.out.print("word2：");
                        String word2 = scanner.nextLine();

                        List<String> bridgeWords = queryBridgeWords.findBridgeWords(graph, word1, word2);

                        if (!graph.nodes.containsKey(word1) || !graph.nodes.containsKey(word2)) {
                            System.out.println("No " + word1 + " or " + word2 + " in the graph!");
                        } else if (bridgeWords.isEmpty()) {
                            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
                        } else {
                            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords));
                        }
                        break;
                        
                    // 生成新文本
                    case "2":
                        System.out.println("请输入一行新文本:");
                        String inputText = scanner.nextLine();
                        String result = generateNewText.GenerateNewText(inputText,graph);
                        System.out.print("输出结果：" + result);
                        break;
                    
                    // 计算两个单词之间的最短路径
                    case "3":
                        System.out.println("请输入两个单词，计算最短路径：");
                        System.out.print("word1：");
                        String word3 = scanner.nextLine();
                        System.out.print("word2：");
                        String word4 = scanner.nextLine();
                        String path = calcShortestPath.CalcShortestPath(word3, word4, graph);
                        System.out.print(path);
                        break;
                        
                    // 随机游走
                    case "4":
                    	System.out.println("随机游走序列：");
                    	String walkResult = randomWalk.RandomWalk(graph);
                    	System.out.println(walkResult);
                    	break;
                    	
                    default:
                        System.out.println("请重新输入");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
