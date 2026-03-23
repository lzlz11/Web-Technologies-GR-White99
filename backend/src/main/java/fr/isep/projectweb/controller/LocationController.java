package fr.isep.projectweb.controller;

import fr.isep.projectweb.model.dto.request.CreateLocationRequest;
import fr.isep.projectweb.model.dto.request.UpdateLocationRequest;
import fr.isep.projectweb.model.entity.Location;
import fr.isep.projectweb.model.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Locations", description = "CRUD endpoints for locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @Operation(summary = "Create a location")
    public Location createLocation(@RequestBody CreateLocationRequest request) {
        return locationService.createLocation(request);
    }

    @GetMapping
    @Operation(summary = "Get all locations")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one location by id")
    public Location getLocationById(@PathVariable UUID id) {
        return locationService.getLocationById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a location")
    public Location updateLocation(@PathVariable UUID id, @RequestBody UpdateLocationRequest request) {
        return locationService.updateLocation(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a location")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
