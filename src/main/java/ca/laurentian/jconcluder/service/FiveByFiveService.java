package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.FiveByFive;
import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.repository.FiveByFiveRepository;
import ca.laurentian.jconcluder.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FiveByFiveService {

    private FiveByFiveRepository FiveByFiveRepository;
    private NodeRepository nodeRepository;

    public FiveByFiveService(ca.laurentian.jconcluder.repository.FiveByFiveRepository fiveByFiveRepository,
                             NodeRepository nodeRepository) {
        FiveByFiveRepository = fiveByFiveRepository;
        this.nodeRepository = nodeRepository;
    }

    public void buildMatrixData(String eachNode) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<FiveByFive> FiveByFiveList = new LinkedList<>();
        FiveByFiveList.add(
                new FiveByFive(eachNode, 1d, Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(3).getSize())));
        FiveByFiveList.add(
                new FiveByFive(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()), 1d,
                               Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(6).getSize())));
        FiveByFiveList.add(
                new FiveByFive(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                               1, Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(8).getSize())));
        FiveByFiveList.add(
                new FiveByFive(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(7).getSize()), 1,
                               Double.parseDouble(listOfNodeForSize.get(9).getSize())));
        FiveByFiveList.add(
                new FiveByFive(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(9).getSize()), 1));
        FiveByFiveRepository.saveAll(FiveByFiveList);
    }
}
