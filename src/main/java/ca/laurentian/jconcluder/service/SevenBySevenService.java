package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.model.SevenBySeven;
import ca.laurentian.jconcluder.repository.NodeRepository;
import ca.laurentian.jconcluder.repository.SevenBySevenRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SevenBySevenService {

    private SevenBySevenRepository SevenBySevenRepository;
    private NodeRepository nodeRepository;

    public SevenBySevenService(ca.laurentian.jconcluder.repository.SevenBySevenRepository sevenBySevenRepository, NodeRepository nodeRepository) {
        SevenBySevenRepository = sevenBySevenRepository;
        this.nodeRepository = nodeRepository;
    }

    public void buildMatrixData(String eachNode) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<SevenBySeven> SevenBySevenList = new LinkedList<>();
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d,
                                 Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(5).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                                 1d,
                                 Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(10).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(11).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(12).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(13).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(14).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(11).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(15).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(16).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(17).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(12).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(15).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(18).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(19).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(13).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(16).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(18).getSize()),
                                 1,
                                 Double.parseDouble(listOfNodeForSize.get(20).getSize())));
        SevenBySevenList.add(
                new SevenBySeven(eachNode,
                                 1d / Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(10).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(14).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(17).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(19).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(20).getSize()),
                                 1));
        SevenBySevenRepository.saveAll(SevenBySevenList);
    }
}
