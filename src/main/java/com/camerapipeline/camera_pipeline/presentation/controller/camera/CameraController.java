package com.camerapipeline.camera_pipeline.presentation.controller.camera;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.presentation.controller.ControllerAbstract;
import com.camerapipeline.camera_pipeline.presentation.dto.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.mapper.core.Mapper;
import com.camerapipeline.camera_pipeline.provider.services.camera.CameraService;

@RestController
@RequestMapping("/camera")
public class CameraController extends ControllerAbstract<Camera, CameraDTO, Integer>{
    public CameraController(CameraService service, Mapper<Camera, CameraDTO> mapper) {
        super(service, mapper);
    }

    @GetMapping
	public ResponseEntity<?> search(
			Principal principal,
			@RequestParam String name,
			Pageable pageable) {
        CameraDTO search = new CameraDTO().name(name);
        Page<CameraDTO> list = mapper.toDTOPage(
            service.search(
                pageable, 
                principal, 
                mapper.fromDTO(search)
            )
        );
		
		return new ResponseEntity<Page<CameraDTO>>(list, HttpStatus.OK);
	}

    @GetMapping("/verify-name")
	public ResponseEntity<?> verifyName(
        Principal principal,
        @RequestParam String name,
        @RequestParam(required = false) Integer id
        ) {

        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((CameraService) service).checkValidName(name, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

    @GetMapping("/verify-url")
	public ResponseEntity<?> verifyUrl(
        Principal principal,
        @RequestParam String url,
        @RequestParam(required = false) Integer id
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((CameraService) service).checkValidUrl(url, id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

    @GetMapping("/verify-used")
	public ResponseEntity<?> verifyUsed(
        Principal principal,
        @RequestParam Integer id
        ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put(
            "valid", 
            ((CameraService) service).checkIfItUsed(id, principal)
        );
		
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

    @PatchMapping("/{id}/state")
	public ResponseEntity<?> setStatus(Principal principal,
        @PathVariable("id") Integer id,
        @RequestParam(name="active", required=true) Boolean active) {
        CameraDTO response = mapper.toDTO(
            ((CameraService) service)
                .setActive(id, active, principal)
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }
}