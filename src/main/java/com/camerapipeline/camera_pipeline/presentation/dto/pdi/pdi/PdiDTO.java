package com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.pdi.Position;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.valueparameter.ValueParameterDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class PdiDTO {
    @Schema(
        title = "PDI identifier",
        name = "id",
        type = "int",
        example = "65",
		required = false
    )
    private Integer id;

    @Schema(
      name = "index",
      example = "1",
      type = "int",
      required = true
    )
    protected Integer index;

    @Schema(
      title = "PDI Position",
      name = "position",
		  required = false
    )
    private Position position;

    @Schema(
      title = "PDI Children",
      name = "children",
		  required = false
    )
    private List<Integer> children = new ArrayList<>();

    @Schema(
        title = "Digital process (Pipeline or ModelPDI)",
        name = "digitalProcess",
		required = true
    )
    @NotNull
    private DigitalProcessDTO digitalProcess;
    
    @Schema(
        title = "PDI Value Parameters",
        name = "valueParameters",
		required = false
    )
    private List<ValueParameterDTO> valueParameters = new ArrayList<>();

    @Schema(
        title = "Pipeline identifier",
        name = "pipelineId",
        example = "22",
        type = "int",
		required = true
    )
    @NotNull
    private Integer pipelineId;

}
