package ca.laurentian.jconcluder.service;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.model.ThreeByThree;
import ca.laurentian.jconcluder.repository.NodeRepository;
import ca.laurentian.jconcluder.repository.ThreeByThreeRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class ThreeByThreeService {

    private ThreeByThreeRepository threeByThreeRepository;
    private NodeRepository nodeRepository;
    DecimalFormat df = new DecimalFormat("#.######");

    public ThreeByThreeService(ThreeByThreeRepository threeByThreeRepository, NodeRepository nodeRepository) {
        df.setMaximumFractionDigits(6);
        this.threeByThreeRepository = threeByThreeRepository;
        this.nodeRepository = nodeRepository;
    }

    public List<ThreeByThree> buildMatrixData(String eachNode) {
        List<Node> listOfNodeForSize = nodeRepository.findByParentNode(eachNode);
        List<ThreeByThree> threeByThreeList = new LinkedList<>();
        threeByThreeList.add(
                new ThreeByThree(eachNode, 1d, Double.parseDouble(listOfNodeForSize.get(0).getSize()),
                                 Double.parseDouble(listOfNodeForSize.get(1).getSize())));
        threeByThreeList.add(
                new ThreeByThree(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(0).getSize()), 1d,
                                 Double.parseDouble(listOfNodeForSize.get(2).getSize())));
        threeByThreeList.add(
                new ThreeByThree(eachNode, 1d / Double.parseDouble(listOfNodeForSize.get(1).getSize()),
                                 1d / Double.parseDouble(listOfNodeForSize.get(2).getSize()),
                                 1));
        threeByThreeRepository.saveAll(threeByThreeList);
        insertGeometricMean(eachNode);
        insertNormalisation(eachNode);
        insertReconstructedGM(eachNode);
        insertInDifferenceMatrix(eachNode);
        return threeByThreeRepository.findByNode(eachNode);
    }

    public void insertGeometricMean(String eachNode) {
        List<ThreeByThree> byNode = threeByThreeRepository.findByNode(eachNode);
        for (ThreeByThree node : byNode) {
            node.setGeometricMean(Math.cbrt(node.getColumn1() * node.getColumn2() * node.getColumn3()));
            threeByThreeRepository.save(node);
        }
    }

    private void insertNormalisation(String eachNode) {
        List<ThreeByThree> byNode = threeByThreeRepository.findByNode(eachNode);
        for (ThreeByThree node : byNode) {
            node.setNormalisation(node.getGeometricMean()
                                  / (byNode.get(0).getGeometricMean() + byNode.get(1).getGeometricMean() +
                                     byNode.get(2).getGeometricMean()));
            threeByThreeRepository.save(node);
        }
    }

    private void insertReconstructedGM(String eachNode) {
        List<ThreeByThree> byNode = threeByThreeRepository.findByNode(eachNode);
        byNode.get(0).setReconstructedGMColumn1(1);
        byNode.get(0).setReconstructedGMColumn2((byNode.get(0).getNormalisation() / byNode.get(1).getNormalisation()));
        byNode.get(0).setReconstructedGMColumn3((byNode.get(0).getNormalisation() / byNode.get(2).getNormalisation()));

        byNode.get(1).setReconstructedGMColumn1(
                1 / (byNode.get(0).getNormalisation() / byNode.get(1).getNormalisation()));
        byNode.get(1).setReconstructedGMColumn2(1);
        byNode.get(1).setReconstructedGMColumn3((byNode.get(1).getNormalisation() / byNode.get(2).getNormalisation()));

        byNode.get(2).setReconstructedGMColumn1(
                1 / (byNode.get(0).getNormalisation() / byNode.get(2).getNormalisation()));
        byNode.get(2).setReconstructedGMColumn2(
                1 / (byNode.get(1).getNormalisation() / byNode.get(2).getNormalisation()));
        byNode.get(2).setReconstructedGMColumn3(1);
        threeByThreeRepository.saveAll(byNode);
    }

    //Double.valueOf(df.format())
    private void insertInDifferenceMatrix(String eachNode) {
        List<ThreeByThree> byNode = threeByThreeRepository.findByNode(eachNode);
        byNode.get(0).setFinalDifferenceColumn1(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(0).getColumn1() - byNode.get(0).getReconstructedGMColumn1())),
                                   2)));
        byNode.get(0).setFinalDifferenceColumn2(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(0).getColumn2() - byNode.get(0).getReconstructedGMColumn2())), 2)));
        byNode.get(0).setFinalDifferenceColumn3(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(0).getColumn3() - byNode.get(0).getReconstructedGMColumn3())), 2)));

        byNode.get(1).setFinalDifferenceColumn1(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(1).getColumn1() - byNode.get(1).getReconstructedGMColumn1())), 2)));
        byNode.get(1).setFinalDifferenceColumn2(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(1).getColumn2() - byNode.get(1).getReconstructedGMColumn2())), 2)));
        byNode.get(1).setFinalDifferenceColumn3(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(1).getColumn3() - byNode.get(1).getReconstructedGMColumn3())), 2)));

        byNode.get(2).setFinalDifferenceColumn1(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(2).getColumn1() - byNode.get(2).getReconstructedGMColumn1())), 2)));
        byNode.get(2).setFinalDifferenceColumn2(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(2).getColumn2() - byNode.get(2).getReconstructedGMColumn2())), 2)));
        byNode.get(2).setFinalDifferenceColumn3(
                df.format(Math.pow(Double.parseDouble(
                        df.format(byNode.get(2).getColumn3() - byNode.get(2).getReconstructedGMColumn3())), 2)));

        threeByThreeRepository.saveAll(byNode);
    }

}
