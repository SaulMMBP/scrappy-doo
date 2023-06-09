package com.github.saulmmbp.app.controllers;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.saulmmbp.app.dto.*;
import com.github.saulmmbp.app.services.ScrappyService;

@RestController
@RequestMapping("/scrappydoo")
public class ScrappyController {
	
	private ScrappyService service;
	
	public ScrappyController(ScrappyService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<String> apiName() {
		return ResponseEntity.ok("Scrappy Doo");
	}
	
	@PostMapping
	public ResponseEntity<PagedModel<Tag>> getTag(Pageable pageable, @RequestBody Request req) {
		List<Tag> tags = service.getTag(req);
		
		// TODO: Pasar esto al service
		int fromIndex = pageable.getPageNumber() * pageable.getPageSize();
		int toIndex = (fromIndex + pageable.getPageSize()) > (tags.size() - 1) ? (tags.size() - 1) : (fromIndex + pageable.getPageSize());
		
		Page<Tag> page = new PageImpl<>(tags.subList(fromIndex, toIndex), pageable, tags.size());
		
		PagedModel<Tag> tagsPaged = PagedModel.of(page.getContent(), 
				new PageMetadata(page.getSize(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages()));
		return ResponseEntity.ok(tagsPaged);
	}
}
