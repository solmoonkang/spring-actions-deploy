package com.springactionsdeploy.api.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springactionsdeploy.api.domain.gallery.entity.Gallery;
import com.springactionsdeploy.api.domain.member.entity.Member;
import com.springactionsdeploy.api.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Page<Review> findAllByGalleryOrderByCreatedAtDesc(Gallery gallery, Pageable pageable);

	boolean existsByMemberAndGallery(Member member, Gallery gallery);
}
