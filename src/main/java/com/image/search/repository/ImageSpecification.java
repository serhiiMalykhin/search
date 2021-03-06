package com.image.search.repository;

import com.image.search.model.dto.SearchRequest;
import com.image.search.model.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class ImageSpecification implements Specification<Image> {

    private final SearchRequest request;

    @Override
    public Predicate toPredicate
            (Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (request.getId() != null) {
            return builder.equal(root.get("id"), request.getId());
        } else if (request.getFullPictureUrl() != null) {
            return builder.equal(root.get("fullPictureUrl"), request.getFullPictureUrl());
        } else if (request.getCroppedPictureUrl() != null) {
            return builder.equal(root.get("croppedPictureUrl"), request.getCroppedPictureUrl());
        }
        Predicate predicate = null;
        if (request.getTags() != null) {
            predicate = builder.like(
                    root.get("tags"), "%" + request.getTags() + "%");
        }
        if (request.getAuthor() != null) {
            predicate = builder.like(
                    root.get("author"), "%" + request.getAuthor() + "%");
        }
        if (request.getCamera() != null) {
            predicate = builder.like(root.get("camera"), "%" + request.getCamera() + "%");
        }
        return predicate;
    }
}
