package dev.prithvis.shell.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.prithvis.shell.models.CustomerSatellite;
import lombok.Data;

import java.util.List;

@Data
public class CustomerSatellitesResponse {
    @JsonProperty("customer_satellites")
    private List<CustomerSatellite> customerSatellites;
}
