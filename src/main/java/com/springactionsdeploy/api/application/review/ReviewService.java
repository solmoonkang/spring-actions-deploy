package com.springactionsdeploy.api.application.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.springactionsdeploy.api.domain.auth.entity.AuthUser;
import com.springactionsdeploy.api.domain.gallery.entity.Gallery;
import com.springactionsdeploy.api.domain.gallery.repository.GalleryRepository;
import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.member.repository.MemberRepository;
import com.springactionsdeploy.api.domain.review.entity.Review;
import com.springactionsdeploy.api.domain.review.repository.ReviewRepository;
import com.springactionsdeploy.api.dto.page.PageInfo;
import com.springactionsdeploy.api.dto.page.PageResponse;
import com.springactionsdeploy.api.dto.review.request.ReviewCreateDto;
import com.springactionsdeploy.api.dto.review.response.ReviewReadDto;
import com.springactionsdeploy.global.error.exception.BadRequestException;
import com.springactionsdeploy.global.error.exception.NotFoundException;
import com.springactionsdeploy.global.error.model.ErrorCode;

import lombok.RequiredArgsConstructor;

@Transactional
@RestController
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final GalleryRepository galleryRepository;
	private final MemberRepository memberRepository;

	public void create(ReviewCreateDto dto, AuthUser authUser) {
		final Member member = memberRepository.findByEmail(authUser.email())
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_MEMBER_NOT_FOUND));
		final Gallery gallery = galleryRepository.findById(dto.galleryId())
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_GALLERY_NOT_FOUND));
		final Review review = Review.create(dto, gallery, member);

		validateAlreadyReview(member, gallery);

		reviewRepository.save(review);
	}

	private void validateAlreadyReview(Member member, Gallery gallery) {
		if (reviewRepository.existsByMemberAndGallery(member, gallery)) {
			throw new BadRequestException(ErrorCode.FAIL_ALREADY_CREATED_REVIEW);
		}
	}

	@Transactional(readOnly = true)
	public PageResponse<ReviewReadDto> readAll(Long galleryId, int page, int size) {
		final Gallery gallery = galleryRepository.findById(galleryId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_GALLERY_NOT_FOUND));
		final Pageable pageable = PageRequest.of(page, size);
		final Page<Review> reviews = reviewRepository.findAllByGalleryOrderByCreatedAtDesc(gallery, pageable);
		final PageInfo pageInfo = new PageInfo(reviews.getNumber(), reviews.isLast());

		return new PageResponse<>(reviews.map(Review::toReviewReadDto).toList(), pageInfo);
	}
}
