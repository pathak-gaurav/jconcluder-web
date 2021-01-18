package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class JConcluderService {

    static List<List<Object>> graphData = new ArrayList<>();
    static List<Node> nodeList = new ArrayList<>();

    public List<List<Object>> getAllGraphData() {
        if (graphData.isEmpty()) {
            addIfEmptyOrResetGraph();
        }
        return graphData;
    }

    private void addIfEmptyOrResetGraph() {
        List<List<Object>> tempGraphNode = new ArrayList<>();
        List<Node> nodeArrayList = new ArrayList<>();
        nodeArrayList.add(new Node("Node", "Parent", "Size"));
        nodeArrayList.add(new Node("ROOT", null, 100));
        for (int i = 0; i < nodeArrayList.size(); i++) {
            tempGraphNode.add(i, Arrays.asList(nodeArrayList.get(i).nodeName, nodeArrayList.get(i).parentNode,
                                               nodeArrayList.get(i).size));
        }
        nodeList = nodeArrayList;
        graphData = tempGraphNode;
    }

    public List<List<Object>> resetGraph() {
        addIfEmptyOrResetGraph();
        return graphData;
    }

    public void saveNode(Node node) {
        List<Node> nodeArrayList = new ArrayList<>();
        nodeArrayList.add(new Node(node.getNodeName(), node.getParentNode(), node.getSize()));
        for (int i = 0; i < nodeArrayList.size(); i++) {
            nodeList.add(nodeList.size(), node);
            graphData.add(graphData.size(),
                          Arrays.asList(nodeArrayList.get(i).getNodeName(), nodeArrayList.get(i).getParentNode(),
                                        Integer.parseInt(nodeArrayList.get(i).getSize().toString())));
        }
    }

    public List<String> getListOfNodes() {
        if (nodeList.get(0).getNodeName().equals("Node")) {
            nodeList.remove(0);
        }
        return nodeList.stream().map(e -> new String(e.getNodeName())).collect(Collectors.toList());

    }

    public boolean findNode(Node node) {
        boolean flag = false;
        for (Node nd : nodeList) {
            if (nd.getNodeName().equals(node.getNodeName())) {
                flag = true;
            }
        }
        return flag;
    }

    public Node findNodeByNode(String nodeName) {
        Node tempNode = null;
        for (Node nd : nodeList) {
            if (nd.getNodeName().equals(nodeName)) {
                tempNode = nd;
            } else {
               tempNode = null;
            }
        }
        return tempNode;
    }

    public List<Node> getNodesForTable() {
        CopyOnWriteArrayList<Node> getNodeForTable = new CopyOnWriteArrayList<>(nodeList);
        for (Node node : getNodeForTable) {
            if (node.getNodeName().equals("Node")) {
                getNodeForTable.remove(node);
            }
            if (node.getNodeName().equals("ROOT")) {
                getNodeForTable.remove(node);
            }
        }
        return getNodeForTable;

    }

    public void deleteNode(String nodeName) {
        CopyOnWriteArrayList<Node> tempNodeList = new CopyOnWriteArrayList<>(nodeList);
        for (Node node:tempNodeList) {
            if(node.getNodeName().equalsIgnoreCase(nodeName)){
                tempNodeList.remove(node);
            }
        }
        List<Object> ob = new ArrayList<>();
        for (List<Object> object : graphData){
            if(object.get(0).toString().equalsIgnoreCase(nodeName)){
                ob=object;
            }
        }
        graphData.remove(ob);
        nodeList = tempNodeList;
    }

    public void updateNode(Node node) {
        for(Node nd:nodeList){
            if(nd.getNodeName().equalsIgnoreCase(node.getNodeName())){
                nd.setParentNode(node.getParentNode());
                nd.setSize(node.getSize());
            }
        }
        for (List<Object> object : graphData){
            if(object.get(0).toString().equalsIgnoreCase(node.getNodeName())){
               object.set(1,node.getParentNode());
               object.set(2,node.getSize());
            }
        }
    }
}
