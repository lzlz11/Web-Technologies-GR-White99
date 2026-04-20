package fr.isep.projectweb.model.dao;

import fr.isep.projectweb.model.entity.PostReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostReviewRepository extends JpaRepository<PostReview, UUID> {

    List<PostReview> findByPostIdOrderByCreatedAtDesc(UUID postId);

    Optional<PostReview> findByIdAndPostId(UUID id, UUID postId);
}
