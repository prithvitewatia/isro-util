package dev.prithvis.shell.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class CustomerSatellite {
    @Id
    @Column
    private String id;
    @Column
    private String country;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("launch_date")
    private LocalDate launchDate;
    @Column
    private Double mass;
    @Column
    private String launcher;

    @Override
    public String toString() {
        return "CustomerSatellite{" + "\n\t" +
                "id='" + id + '\'' + "\n\t" +
                ", country='" + country + '\'' + "\n\t" +
                ", launchDate=" + launchDate + "\n\t" +
                ", mass=" + mass + "\n\t" +
                ", launcher='" + launcher + '\'' + "\n" +
                '}';
    }
}
