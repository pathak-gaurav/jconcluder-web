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
@Table
public class FiveByFive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nodeName;
    private double column1;
    private double column2;
    private double column3;
    private double column4;
    private double column5;

    public FiveByFive(String nodeName, double column1, double column2, double column3, double column4, double column5) {
        this.nodeName = nodeName;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
    }
}
