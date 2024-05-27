package software_lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 文本转换为有向图类
public class TextToDirectedGraph {

    public static DirectedGraph createDirectedGraphFromFile(String filePath) {
        DirectedGraph graph = new DirectedGraph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String prevWord = null;

            while ((line = reader.readLine()) != null) {
                StringBuilder currentWord = new StringBuilder();
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) { 
                    	// 是字母则转换为小写加入当前单词
                        currentWord.append(Character.toLowerCase(c));
                    } else if (Character.isDigit(c)) { 
                        // 跳过数字字符
                    } else {
                        if (currentWord.length() > 0) {
                            String word = currentWord.toString();
                            graph.addNode(word);
                            if (prevWord != null) {
                                graph.addNode(prevWord);// 将前一个单词加入图中
                                graph.addEdge(prevWord, word);// 加边
                            }
                            prevWord = word;
                            currentWord.setLength(0); // 重置StringBuilder以构建下一个单词
                        }
                    }
                }
                // 最后一个单词后无非字母字符，单独处理
                if (currentWord.length() > 0) {
                    String word = currentWord.toString();
                    graph.addNode(word);
                    if (prevWord != null) {
                        graph.addNode(prevWord);
                        graph.addEdge(prevWord, word);
                    }
                    prevWord = word;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
