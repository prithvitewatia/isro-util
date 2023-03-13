package dev.prithvis.shell.models;

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
public class Launcher {
    @Id
    @Column
    private String id;

    @Override
    public String toString() {
        return "Launchers{" +"\n\t"+
                "id='" + id + '\'' +"\n"+
                '}';
    }
}
