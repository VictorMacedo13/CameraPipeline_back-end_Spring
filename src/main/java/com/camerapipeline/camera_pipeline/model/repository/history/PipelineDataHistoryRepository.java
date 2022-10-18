package com.camerapipeline.camera_pipeline.model.repository.history;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;

public interface PipelineDataHistoryRepository extends JpaRepository<PipelineDataHistory, UUID>{
    
}