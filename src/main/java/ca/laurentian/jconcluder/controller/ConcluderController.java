package ca.laurentian.jconcluder.controller;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.model.ThreeByThree;
import ca.laurentian.jconcluder.service.JConcluderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ConcluderController {

    private JConcluderService concluderService;

    public ConcluderController(JConcluderService concluderService) {
        this.concluderService = concluderService;
    }

    @GetMapping("/")
    public String getPieChart(Model model)  {
        loadHomePageDetails(model);
        return "jconcluder";
    }

    private void loadHomePageDetails(Model model)  {
        List<List<Object>> graphData = concluderService.getAllGraphData();
        List<Node> nodesForTable = concluderService.getNodesForTable();
        model.addAttribute("chartData", graphData);
        model.addAttribute("nodesForTable", nodesForTable);

        //Analyze Part
        List<String> listOfNodes = concluderService.getListOfNodesNames();
        Node node = new Node();
        model.addAttribute("node", node);
        model.addAttribute("listOfNodes", listOfNodes);
    }

    @GetMapping("/showAddNodeForm")
    public String showAddNodeForm(Model model) {
        List<String> listOfNodes = concluderService.getListOfNodesNames();
        Node node = new Node();
        model.addAttribute("node", node);
        model.addAttribute("listOfNodes", listOfNodes);
        return "add_node";
    }

    @GetMapping("/resetGraph")
    public String resetGraph(Model model) throws JsonProcessingException {
        model.addAttribute("chartData", concluderService.resetGraph());
        return "redirect:/";
    }

    @PostMapping("/saveNode")
    public String saveEmployee(@ModelAttribute("node") Node node, Model model) {
        List<String> listOfNodes = concluderService.getListOfNodesNames();
        model.addAttribute("listOfNodes", listOfNodes);
        if (listOfNodes.contains(node.getNodeName())) {
            model.addAttribute("existedNodeName", "Node name already exist, try different name");
            return "add_node";
        } else {
            concluderService.saveNode(node);
            return "redirect:/";
        }
    }

    @GetMapping("/deleteNode/{id}")
    public String deleteNode(@PathVariable("id") Long id, Model model) {
        concluderService.deleteNode(id);
        return "redirect:/";
    }

    @GetMapping("/showFormUpdateNode/{id}")
    public String showFormUpdateNode(@PathVariable("id")Long id,Model model){
        Node node = concluderService.findNodeByNode(id);
        List<String> listOfNodes = concluderService.getListOfNodesNames();
        model.addAttribute("node", node);
        model.addAttribute("listOfNodes", listOfNodes);
        return "update_node";
    }

    @PostMapping("/updateNode")
    public String updateNode(@ModelAttribute("node") Node node, Model model) {
        List<String> listOfNodes = concluderService.getListOfNodesNames();
        model.addAttribute("listOfNodes", listOfNodes);
        Node nodeById = concluderService.findNodeByNode(node.getId());
        if(node.getNodeName().equals(node.getParentNode())){
            model.addAttribute("nodeNameIssue","Node Name and Parent Node cannot be same. Try different");
            return "update_node";
        }else if(node.getParentNode().equalsIgnoreCase(nodeById.getNodeName())){
            model.addAttribute("nodeNameIssue","You are trying to assign parent node, which you are going to remove by renaming node name. Try different");
            return "update_node";
        }
        concluderService.updateNode(node);
        return "redirect:/";
    }

    @PostMapping("/showMatrix")
    public String showMatrix(@ModelAttribute("node") Node node, Model model) {
        String parentNode = node.getParentNode();
        if(concluderService.findByParentNode(parentNode).size()!=3){
            model.addAttribute("nodeNameIssue","Selected Node does not have size 3 or more.");
            loadHomePageDetails(model);
            return "jconcluder";
        }
        List<ThreeByThree> threeByThreeList = concluderService.showAllMatrix(parentNode);
        model.addAttribute("threeByThreeList",threeByThreeList);

        return "show_matrix";
    }

    //move whole showMatrix to Controller so no list hassel issue.
}
