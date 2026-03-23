package fr.isep.projectweb.model.dao;

import fr.isep.projectweb.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationDAO extends JpaRepository<Location, UUID> {
}