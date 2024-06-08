package com.springactionsdeploy.api.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springactionsdeploy.api.application.review.ReviewService;
import com.springactionsdeploy.api.domain.auth.entity.AuthUser;
import com.springactionsdeploy.api.dto.page.PageResponse;
import com.springactionsdeploy.api.dto.review.request.ReviewCreateDto;
import com.springactionsdeploy.api.dto.review.response.ReviewReadDto;
import com.springactionsdeploy.global.auth.annotation.Auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid ReviewCreateDto dto, @Auth AuthUser authUser) {
		reviewService.create(dto, authUser);

		return ResponseEntity.ok("OK");
	}

	@GetMapping("/{gallery-id}")
	public PageResponse<ReviewReadDto> readAll(
		@PathVariable(name = "gallery-id") Long galleryId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		return reviewService.readAll(galleryId, page, size);
	}
}
