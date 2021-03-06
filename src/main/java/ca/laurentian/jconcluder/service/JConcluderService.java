package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.model.ThreeByThree;
import ca.laurentian.jconcluder.repository.EightByEightRepository;
import ca.laurentian.jconcluder.repository.FiveByFiveRepository;
import ca.laurentian.jconcluder.repository.FourByFourRepository;
import ca.laurentian.jconcluder.repository.NodeRepository;
import ca.laurentian.jconcluder.repository.SevenBySevenRepository;
import ca.laurentian.jconcluder.repository.SixBySixRepository;
import ca.laurentian.jconcluder.repository.ThreeByThreeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JConcluderService {

    private NodeRepository nodeRepository;
    private ThreeByThreeService threeByThreeService;
    private FourByFourService fourByFourService;
    private FiveByFiveService fiveByFiveService;
    private SixBySixService sixBySixService;
    private SevenBySevenService sevenBySevenService;
    private EightByEightService eightByEightService;

    private ThreeByThreeRepository threeByThreeRepository;
    private FourByFourRepository fourByFourRepository;
    private FiveByFiveRepository fiveByFiveRepository;
    private SixBySixRepository sixBySixRepository;
    private SevenBySevenRepository sevenBySevenRepository;
    private EightByEightRepository eightByEightRepository;

    public JConcluderService(NodeRepository nodeRepository, ThreeByThreeService threeByThreeService,
                             FourByFourService fourByFourService, FiveByFiveService fiveByFiveService,
                             SixBySixService sixBySixService, SevenBySevenService sevenBySevenService,
                             EightByEightService eightByEightService, ThreeByThreeRepository threeByThreeRepository,
                             FourByFourRepository fourByFourRepository, FiveByFiveRepository fiveByFiveRepository,
                             SixBySixRepository sixBySixRepository, SevenBySevenRepository sevenBySevenRepository,
                             EightByEightRepository eightByEightRepository) {
        this.nodeRepository = nodeRepository;
        this.threeByThreeService = threeByThreeService;
        this.fourByFourService = fourByFourService;
        this.fiveByFiveService = fiveByFiveService;
        this.sixBySixService = sixBySixService;
        this.sevenBySevenService = sevenBySevenService;
        this.eightByEightService = eightByEightService;
        this.threeByThreeRepository = threeByThreeRepository;
        this.fourByFourRepository = fourByFourRepository;
        this.fiveByFiveRepository = fiveByFiveRepository;
        this.sixBySixRepository = sixBySixRepository;
        this.sevenBySevenRepository = sevenBySevenRepository;
        this.eightByEightRepository = eightByEightRepository;
    }

    public List<List<Object>> getAllGraphData()  {
        List<Node> allNodes = nodeRepository.findAll();
        if (allNodes.isEmpty()) {
            addIfEmptyOrResetGraph();
        }
        return repositoryToGraphNode();
    }

    private List<List<Object>> repositoryToGraphNode() {
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
        allXByXServiceDeleteRepository();
        List<Node> nodeLinkedList = new LinkedList<>();
        nodeLinkedList.add(new Node("Node", "Parent", "Size"));
        nodeLinkedList.add(new Node("ROOT", null, "100"));
        nodeRepository.saveAll(nodeLinkedList);
    }

    private void allXByXServiceDeleteRepository() {
        threeByThreeRepository.deleteAll();
        fourByFourRepository.deleteAll();
        fiveByFiveRepository.deleteAll();
        sixBySixRepository.deleteAll();
        sevenBySevenRepository.deleteAll();
        eightByEightRepository.deleteAll();
    }

    public List<List<Object>> resetGraph()  {
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
        allXByXServiceDeleteRepository();
        Node nodeFromRepo = nodeRepository.findById(node.getId()).orElse(null);
        nodeFromRepo.setNodeName(node.getNodeName());
        nodeFromRepo.setParentNode(node.getParentNode());
        nodeFromRepo.setSize(node.getSize());
        nodeRepository.save(node);
    }

    public List<ThreeByThree> showAllMatrix(String node) {
        List<Node> nodeList = nodeRepository.findByParentNode(node);
        if (nodeList.size() == 3) {
            List<ThreeByThree> byNode = threeByThreeRepository.findByNode(node);
            if(byNode.isEmpty()) {
                return threeByThreeService.buildMatrixData(node);
            }else{
                return byNode;
            }
        }
        if (nodeList.size() == 6) {
            fourByFourService.buildMatrixData(node);
        }
        if (nodeList.size() == 10) {
            fiveByFiveService.buildMatrixData(node);
        }
        if (nodeList.size() == 15) {
            sixBySixService.buildMatrixData(node);
        }
        if (nodeList.size() == 21) {
            sevenBySevenService.buildMatrixData(node);
        }
        if (nodeList.size() == 28) {
            eightByEightService.buildMatrixData(node);
        }
        return null;
    }

    public List<Node> findByParentNode(String node){
        return nodeRepository.findByParentNode(node);
    }


}
