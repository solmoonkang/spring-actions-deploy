package com.springactionsdeploy.api.domain.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springactionsdeploy.api.domain.gallery.entity.Gallery;
import com.springactionsdeploy.api.domain.gallery.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByGallery(Gallery gallery);
}
