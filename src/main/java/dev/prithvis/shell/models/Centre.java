package dev.prithvis.shell.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Centre {
    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    @JsonProperty("Place")
    private String place;
    @Column
    @JsonProperty("State")
    private String state;

    @Override
    public String toString() {
        return "Centre{" +"\n\t"+
                "id=" + id +"\n\t"+
                ", name='" + name + '\'' +"\n\t"+
                ", place='" + place + '\'' +"\n\t"+
                ", state='" + state + '\'' +"\n"+
                '}';
    }
}
