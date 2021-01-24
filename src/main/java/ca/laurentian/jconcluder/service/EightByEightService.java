package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.EightByEight;
import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.repository.EightByEightRepository;
import ca.laurentian.jconcluder.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EightByEightService {

    private EightByEightRepository EightByEightRepository;
    private NodeRepository nodeRepository;

    public EightByEightService(ca.laurentian.jconcluder.repository.EightByEightRepository eightByEightRepository, NodeRepository nodeRepository) {
        EightByEightRepository = eightByEightRepository;
        this.nodeRepository = nodeRepository;
    }

    public void buildMatrixData(String eachNode, List<Node> nodeList) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<EightByEight> EightByEightList = new LinkedList<>();
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d,
                                 Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(6).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                                 1d,
                                 Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(10).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(11).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(12).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(13).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(14).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(15).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(16).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(17).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(13).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(18).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(19).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(20).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(21).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(14).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(18).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(22).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(23).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(24).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(10).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(15).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(19).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(22).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(25).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(26).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(11).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(16).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(20).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(23).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(25).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(27).getSize())));
        EightByEightList.add(
                new EightByEight(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(12).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(17).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(21).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(24).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(26).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(27).getSize()),
                                 1));
        EightByEightRepository.saveAll(EightByEightList);
    }
}
