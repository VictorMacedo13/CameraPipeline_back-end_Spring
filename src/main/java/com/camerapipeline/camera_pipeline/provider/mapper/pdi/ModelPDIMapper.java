package com.camerapipeline.camera_pipeline.provider.mapper.pdi;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.modelpdi.ModelPdiDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.parameter.ParameterDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;

@Component
public class ModelPDIMapper extends Mapper<ModelPDI, ModelPdiDTO> {
    @Autowired
    ParameterMapper parameterMapper;

    @Override
    public ModelPdiDTO toDTO(ModelPDI model) {
        TypeMap<ModelPDI, ModelPdiDTO> typeMap = getTypeMap(
            ModelPDI.class, 
            ModelPdiDTO.class
        );
        Converter<List<Parameter>, List<ParameterDTO>> converterParameterList = 
            ctx -> ctx.getSource() == null ?
                null :
                parameterMapper.toDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterParameterList)
                    .map(
                        ModelPDI::getParameters, 
                        ModelPdiDTO::setParameters
                    );
            }
        );

        ModelPdiDTO dto = modelMapper.map(
            model, 
            ModelPdiDTO.class
        ); 
        return dto;
    }

    @Override
    public ModelPDI fromDTO(ModelPdiDTO dto) {
        TypeMap<ModelPdiDTO, ModelPDI> typeMap = getTypeMap(
            ModelPdiDTO.class, 
            ModelPDI.class
        );
        Converter<List<ParameterDTO>, List<Parameter>> converterParameterList = 
            ctx -> ctx.getSource() == null ?
                null :
                parameterMapper.fromDTOList(ctx.getSource());

        typeMap.addMappings(
            mapper -> {
                mapper.using(converterParameterList)
                    .map(
                        ModelPdiDTO::getParameters, 
                        ModelPDI::setParameters
                    );
            }
        );

        ModelPDI model = modelMapper.map(
            dto, 
            ModelPDI.class
        ); 
        return model;
    }
    
}
