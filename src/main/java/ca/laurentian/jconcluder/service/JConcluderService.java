package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.repository.NodeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JConcluderService {

    private NodeRepository nodeRepository;

    public JConcluderService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<List<Object>> getAllGraphData() throws JsonProcessingException {
        List<Node> allNodes = nodeRepository.findAll();
        if (allNodes.isEmpty()) {
            addIfEmptyOrResetGraph();
        }
        return repositoryToGraphNode();
    }

    private List<List<Object>> repositoryToGraphNode() throws JsonProcessingException {
        List<Node> allNodes = nodeRepository.findAll();
        List<List<Object>> graphData = new LinkedList<>();
        for (int i = 0; i < allNodes.size(); i++) {
            if (i == 0) {
                graphData.add(i, Arrays.asList(allNodes.get(i).getNodeName(), allNodes.get(i).getParentNode(),
                                               allNodes.get(i).getSize()));
            } else {
                graphData.add(i, Arrays.asList(allNodes.get(i).getNodeName(), allNodes.get(i).getParentNode(),
                                               Integer.parseInt(allNodes.get(i).getSize())));
            }
        }
        return graphData;
    }

    private void addIfEmptyOrResetGraph() {
        nodeRepository.deleteAll();
        List<Node> nodeLinkedList = new LinkedList<>();
        nodeLinkedList.add(new Node("Node", "Parent", "Size"));
        nodeLinkedList.add(new Node("ROOT", null, "100"));
        nodeRepository.saveAll(nodeLinkedList);
    }

    public List<List<Object>> resetGraph() throws JsonProcessingException {
        addIfEmptyOrResetGraph();
        return repositoryToGraphNode();
    }

    public void saveNode(Node node) {
        nodeRepository.save(node);
    }

    public List<String> getListOfNodesNames() {
        List<Node> listOfAllNode = nodeRepository.findAll();
        return listOfAllNode.stream().filter(e -> !e.getNodeName().equalsIgnoreCase("Node")).map(
                e1 -> new String(e1.getNodeName())).collect(Collectors.toList());
    }

    public Node findNodeByNode(Long id) {
        return nodeRepository.findById(id).orElse(null);
    }

    public List<Node> getNodesForTable() {
        List<Node> listOfAllNode = nodeRepository.findAll();

        return listOfAllNode.stream().filter(node -> !node.getNodeName().equalsIgnoreCase("Node")
                                                     && !node.getNodeName().equalsIgnoreCase("ROOT")).collect(
                Collectors.toList());

    }

    public void deleteNode(Long id) {
        Node node = nodeRepository.findById(id).orElse(null);
        if (node != null) {
            nodeRepository.delete(node);
        }

    }

    public void updateNode(Node node) {
        Node nodeFromRepo = nodeRepository.findById(node.getId()).orElse(null);
        nodeFromRepo.setNodeName(node.getNodeName());
        nodeFromRepo.setParentNode(node.getParentNode());
        nodeFromRepo.setSize(node.getSize());
        nodeRepository.save(node);
    }
}
