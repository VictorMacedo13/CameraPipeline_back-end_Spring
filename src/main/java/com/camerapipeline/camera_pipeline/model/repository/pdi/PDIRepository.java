package com.camerapipeline.camera_pipeline.model.repository.pdi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface PDIRepository extends RepositoryAbstract<PDI, Integer> {
    
    @Override
    @Query(value = "SELECT p FROM PDI p, ModelPDI m "+
        "WHERE m.id = p.modelPdi.id AND m.user.id = ?1")
    Page<PDI> findAll(Pageable pageable, Integer userId);
}