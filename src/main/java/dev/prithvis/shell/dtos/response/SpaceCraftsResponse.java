package dev.prithvis.shell.dtos.response;

import dev.prithvis.shell.models.SpaceCraft;
import lombok.Data;

import java.util.List;

@Data
public class SpaceCraftsResponse {
    private List<SpaceCraft> spacecrafts;
}
