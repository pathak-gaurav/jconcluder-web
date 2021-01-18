package ca.laurentian.jconcluder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Node {

    @JsonProperty("NodeName")
    public String nodeName;
    @JsonProperty("ParentNode")
    public String parentNode;
    @JsonProperty("Size")
    public Object size;
}