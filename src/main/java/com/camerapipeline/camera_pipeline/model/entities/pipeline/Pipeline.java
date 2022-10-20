package com.camerapipeline.camera_pipeline.model.entities.pipeline;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Pipeline implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Column(length = 60)
    private String name;

    private String description;

    @NotNull
    private boolean isActive;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "pipeline", cascade = CascadeType.REMOVE)
    private List<PDI> PDIList;

    public Pipeline id(Integer id) {
        setId(id);
        return this;
    }

    public Pipeline name(String name) {
        setName(name);
        return this;
    }

    public Pipeline description(String description) {
        setDescription(description);
        return this;
    }

    public Pipeline isActive(boolean isActive) {
        setActive(isActive);
        return this;
    }

    public Pipeline PDIList(List<PDI> PDIList) {
        setPDIList(PDIList);
        return this;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User getUser() {
        return user;
    }
}
