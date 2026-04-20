package fr.isep.projectweb.model.dao;

import fr.isep.projectweb.model.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByUserIdOrderByCreatedAtDesc(UUID userId);

    List<Post> findByLocationIdOrderByCreatedAtDesc(UUID locationId);

    List<Post> findByEventIdOrderByCreatedAtDesc(UUID eventId);

    @Query("""
            SELECT p
            FROM Post p
            WHERE LOWER(COALESCE(p.title, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(COALESCE(p.content, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
            ORDER BY p.createdAt DESC
            """)
    List<Post> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
