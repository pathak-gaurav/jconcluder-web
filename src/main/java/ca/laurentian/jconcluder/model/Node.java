package ca.laurentian.jconcluder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "NodeName",
        "ParentNode",
        "Size"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("NodeName")
    public String nodeName;
    @JsonProperty("ParentNode")
    public String parentNode;
    @JsonProperty("Size")
    public String size;

    public Node(String nodeName, String parentNode, String size) {
        this.nodeName = nodeName;
        this.parentNode = parentNode;
        this.size = size;
    }
}