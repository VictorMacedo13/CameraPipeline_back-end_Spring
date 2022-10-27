package com.camerapipeline.camera_pipeline.model.repository.pdi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface PDIRepository extends RepositoryAbstract<PDI, Integer> {
    
    @Override
    @Query(value = "SELECT p FROM PDI p, DigitalProcess d "+
        "WHERE d.id = p.digitalProcess.id AND d.user.id = ?1")
    Page<PDI> findAll(Pageable pageable, Integer userId);

    @Query(value = "SELECT p FROM PDI p "+
        "WHERE p.digitalProcess.id = :#{#digitalID}")
    List<PDI> findByDigitalProcess(@Param("digitalID") Integer digitalID);

    @Modifying
    @Query(value = "DELETE FROM PDI p "+
        "WHERE p.pipeline.id = :#{#pipelineID}")
    void deleteInBatch(@Param("pipelineID") Integer pipelineID);
}
