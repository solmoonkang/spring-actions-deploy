package com.springactionsdeploy.api.domain.gallery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springactionsdeploy.api.domain.gallery.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
	Page<Gallery> findAllByIsPaidTrue(Pageable pageable);
}
