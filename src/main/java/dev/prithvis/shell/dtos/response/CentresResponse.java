package dev.prithvis.shell.dtos.response;

import dev.prithvis.shell.models.Centre;
import lombok.Data;

import java.util.List;

@Data
public class CentresResponse {
    private List<Centre> centres;
}
