package dev.prithvis.shell.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SpaceCraft {
    @Id
    private Long id;
    @Column
    private String name;

    @Override
    public String toString() {
        return "SpaceCraft{" +"\n\t"+
                "id=" + id +"\n\t"+
                ",name='" + name + "'\n" +
                '}';
    }
}
