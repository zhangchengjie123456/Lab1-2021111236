package software_lab1;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

// 可视化模块
public class showDirectedGraph {

    public static void visualizeGraph(DirectedGraph directedGraph) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        
        Map<String, Object> edgeStyle = graph.getStylesheet().getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_FONTSIZE, 24); // 设置权重字号
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#FF0000"); // 设置权重字体颜色为红色
        
        graph.getModel().beginUpdate();
        try {
            Map<String, Object> vertexMap = new HashMap<>();
            for (String nodeName : directedGraph.nodes.keySet()) {
                Object vertex = graph.insertVertex(parent, null, nodeName, 50, 50, 80, 30);
                vertexMap.put(nodeName, vertex);
            }

            for (GraphNode node : directedGraph.nodes.values()) {
                for (Map.Entry<GraphNode, Integer> entry : node.getAdjacentNodes().entrySet()) {
                    GraphNode adjacentNode = entry.getKey();
                    int weight = entry.getValue();
                    Object edge = graph.insertEdge(parent, null, weight, vertexMap.get(node.getWord()), vertexMap.get(adjacentNode.getWord()));
                    // 根据权重更新边的大小
                    graph.updateCellSize(edge);
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.execute(graph.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(800, 600);  // 调整边大小

        try {
            File imgFile = new File("C:\\Users\\M\\Desktop\\graph.png");
            ImageIO.write(mxCellRenderer.createBufferedImage(graph, null, 2, Color.WHITE, true, null), "PNG", imgFile);
            System.out.println("图像已保存到: " + imgFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
