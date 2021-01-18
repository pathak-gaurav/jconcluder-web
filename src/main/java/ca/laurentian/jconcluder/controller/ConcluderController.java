package ca.laurentian.jconcluder.controller;

import ca.laurentian.jconcluder.model.Node;
import ca.laurentian.jconcluder.service.JConcluderService;
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
    public String getPieChart(Model model) {
        List<List<Object>> graphData = concluderService.getAllGraphData();
        List<Node> nodesForTable = concluderService.getNodesForTable();
        model.addAttribute("chartData", graphData);
        model.addAttribute("nodesForTable", nodesForTable);
        return "jconcluder";
    }

    @GetMapping("/showAddNodeForm")
    public String showAddNodeForm(Model model) {
        List<String> listOfNodes = concluderService.getListOfNodes();
        Node node = new Node();
        model.addAttribute("node", node);
        model.addAttribute("listOfNodes", listOfNodes);
        return "add_node";
    }

    @GetMapping("/resetGraph")
    public String resetGraph(Model model) {
        model.addAttribute("chartData", concluderService.resetGraph());
        return "redirect:/";
    }

    @PostMapping("/saveNode")
    public String saveEmployee(@ModelAttribute("node") Node node, Model model) {
        List<String> listOfNodes = concluderService.getListOfNodes();
        model.addAttribute("listOfNodes", listOfNodes);
        if (concluderService.findNode(node)) {
            model.addAttribute("existedNodeName", "Node name already exist, try different name");
            return "add_node";
        } else {
            concluderService.saveNode(node);
            return "redirect:/";
        }
    }

    @GetMapping("/deleteNode/{nodeName}")
    public String deleteNode(@PathVariable("nodeName") String nodeName, Model model) {
        concluderService.deleteNode(nodeName);
        return "redirect:/";
    }

    @GetMapping("/showFormUpdateNode/{nodeName}")
    public String showFormUpdateNode(@PathVariable("nodeName")String nodeName,Model model){
        Node node = concluderService.findNodeByNode(nodeName);
        List<String> listOfNodes = concluderService.getListOfNodes();
        model.addAttribute("node", node);
        model.addAttribute("listOfNodes", listOfNodes);
        return "update_node";
    }

    @PostMapping("/updateNode")
    public String updateNode(@ModelAttribute("node") Node node, Model model) {
        List<String> listOfNodes = concluderService.getListOfNodes();
        model.addAttribute("listOfNodes", listOfNodes);
        if(node.getNodeName().equals(node.getParentNode())){
            model.addAttribute("nodeNameIssue","Node Name and Parent Node cannot be same. Try different");
            return "update_node";
        }
        concluderService.updateNode(node);
        return "redirect:/";
    }

}
