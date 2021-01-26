package ca.laurentian.jconcluder.model;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="ThreeByThree")
public class ThreeByThree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nodeName;
    private double column1;
    private double column2;
    private double column3;
    private double geometricMean;
    private double normalisation;
    private double reconstructedGMColumn1;
    private double reconstructedGMColumn2;
    private double reconstructedGMColumn3;
    private String finalDifferenceColumn1;
    private String finalDifferenceColumn2;
    private String finalDifferenceColumn3;


    public ThreeByThree(String nodeName, double column1, double column2, double column3) {
        this.nodeName = nodeName;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public ThreeByThree(double reconstructedGMColumn1, double reconstructedGMColumn2, double reconstructedGMColumn3) {
        this.reconstructedGMColumn1 = reconstructedGMColumn1;
        this.reconstructedGMColumn2 = reconstructedGMColumn2;
        this.reconstructedGMColumn3 = reconstructedGMColumn3;
    }
}
