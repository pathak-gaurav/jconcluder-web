package ca.laurentian.jconcluder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class EightByEight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nodeName;
    private double column1;
    private double column2;
    private double column3;
    private double column4;
    private double column5;
    private double column6;
    private double column7;
    private double column8;

    public EightByEight(String nodeName, double column1, double column2, double column3, double column4,
                        double column5, double column6, double column7, double column8) {
        this.nodeName = nodeName;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
        this.column7 = column7;
        this.column8 = column8;
    }
}
