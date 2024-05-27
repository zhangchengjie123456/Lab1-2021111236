package software_lab1;

import java.util.List;
import java.util.Random;

// 根据桥接词生成新文本
public class generateNewText {

	public static String GenerateNewText(String inputText, DirectedGraph graph) {
	    if (inputText.isEmpty() || graph == null) {
	        return null;
	    }
	    
	    // 使用正则表达式将字符串分割成单词，\\s+表示一个或多个空格字符
        String[] words = inputText.split("\\s+");
        StringBuilder resultBuilder = new StringBuilder();
        
        // 计算两两相邻单词的桥接词
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // 查找桥接词
            List<String> bridgeWords = queryBridgeWords.findBridgeWords(graph, word1, word2);

            if (bridgeWords.isEmpty()) {
            	// 如果不存在桥接词，把word1加入新文本中
                resultBuilder.append(word1).append(" ");
            } else {
                // 如果存在桥接词，随机选择一个插入
                Random rand = new Random();
                String randomBridgeWord = bridgeWords.get(rand.nextInt(bridgeWords.size()));
                resultBuilder.append(word1).append(" ").append(randomBridgeWord).append(" ");
            }
        }

        // 单独处理。向新文本添加最后一个单词
        resultBuilder.append(words[words.length - 1]);

        // 返回生成的新文本
        return resultBuilder.toString();
    }
}
