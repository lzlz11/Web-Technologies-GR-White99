package fr.isep.projectweb.model.dao;

import fr.isep.projectweb.model.entity.LocationAccessibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationAccessibilityRepository extends JpaRepository<LocationAccessibility, UUID> {

    Optional<LocationAccessibility> findByLocationId(UUID locationId);
}
