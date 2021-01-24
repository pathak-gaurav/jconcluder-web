package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.model.SixBySix;
import ca.laurentian.jconcluder.repository.NodeRepository;
import ca.laurentian.jconcluder.repository.SixBySixRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SixBySixService {

    private SixBySixRepository SixBySixRepository;
    private NodeRepository nodeRepository;

    public SixBySixService(ca.laurentian.jconcluder.repository.SixBySixRepository sixBySixRepository, NodeRepository nodeRepository) {
        SixBySixRepository = sixBySixRepository;
        this.nodeRepository = nodeRepository;
    }

    public void buildMatrixData(String eachNode, List<Node> nodeList) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<SixBySix> SixBySixList = new LinkedList<>();
        SixBySixList.add(
                new SixBySix(eachNode, 1d,
                             Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(4).getSize())));
        SixBySixList.add(
                new SixBySix(eachNode,
                             1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                             1d,
                             Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(8).getSize())));
        SixBySixList.add(
                new SixBySix(eachNode,
                             1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(5).getSize()),
                             1,
                             Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(10).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(11).getSize())));
        SixBySixList.add(
                new SixBySix(eachNode,
                             1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(6).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(9).getSize()),
                             1,
                             Double.parseDouble(listOfNodeForSize.get(12).getSize()),
                             Double.parseDouble(listOfNodeForSize.get(13).getSize())));
        SixBySixList.add(
                new SixBySix(eachNode,
                             1d / Double.parseDouble(listOfNodeForSize.get(3).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(7).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(10).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(12).getSize()),
                             1,
                             Double.parseDouble(listOfNodeForSize.get(14).getSize())));
        SixBySixList.add(
                new SixBySix(eachNode,
                             1d / Double.parseDouble(listOfNodeForSize.get(4).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(8).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(11).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(13).getSize()),
                             1d / Double.parseDouble(listOfNodeForSize.get(14).getSize()),
                             1));
        SixBySixRepository.saveAll(SixBySixList);
    }
}
