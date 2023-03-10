package com.camerapipeline.camera_pipeline.model.repository.pdi;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

@Repository
public interface ModelPDIRepository extends RepositoryAbstract<ModelPDI, Integer>  {
    
    @Query(value = "SELECT p FROM ModelPDI p WHERE LOWER(p.name) = LOWER(:name) AND p.user.id = :userId")
    Optional<ModelPDI> findByName(@Param("name") String name, @Param("userId") Integer id);

    @Query(value = "SELECT p FROM ModelPDI p WHERE p.URL = :url AND p.user.id = :userId")
    Optional<ModelPDI> findByURL(@Param("url") String url, @Param("userId") Integer id);

    
    Optional<ModelPDI> findByName(@Param("name") String name);

    @Override
    @Query(value = "SELECT m FROM ModelPDI m WHERE m.user.id = ?1")
    Page<ModelPDI> findAll(Pageable pageable, Integer userId);
}
