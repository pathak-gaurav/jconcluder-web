package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.FourByFour;
import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.repository.FourByFourRepository;
import ca.laurentian.jconcluder.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FourByFourService {

    private FourByFourRepository FourByFourRepository;
    private NodeRepository nodeRepository;

    public FourByFourService(ca.laurentian.jconcluder.repository.FourByFourRepository fourByFourRepository,
                             NodeRepository nodeRepository) {
        FourByFourRepository = fourByFourRepository;
        this.nodeRepository = nodeRepository;
    }

    public void buildMatrixData(String eachNode, List<Node> nodeList) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<FourByFour> FourByFourList = new LinkedList<>();
        FourByFourList.add(
                new FourByFour(eachNode, 1d, Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(2).getSize())));
        FourByFourList.add(
                new FourByFour(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()), 1d,
                               Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                               Double.parseDouble(listOfNodeForSize.get(4).getSize())));
        FourByFourList.add(
                new FourByFour(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                               1, Double.parseDouble(listOfNodeForSize.get(5).getSize())));
        FourByFourList.add(
                new FourByFour(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                               1d / Double.parseDouble(listOfNodeForSize.get(5).getSize()), 1));
        FourByFourRepository.saveAll(FourByFourList);
    }
}
