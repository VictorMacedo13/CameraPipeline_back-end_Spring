package com.camerapipeline.camera_pipeline.model.entities.email;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.camerapipeline.camera_pipeline.model.enums.StatusEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "mail")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String nomeDestinatario;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDataEmail;
    private StatusEmail statusEmail;
    
    private int resolut;
	
}
