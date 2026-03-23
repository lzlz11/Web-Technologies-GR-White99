package fr.isep.projectweb.model.service;

import fr.isep.projectweb.model.dao.LocationDAO;
import fr.isep.projectweb.model.dto.request.CreateLocationRequest;
import fr.isep.projectweb.model.dto.request.UpdateLocationRequest;
import fr.isep.projectweb.model.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationDAO locationDAO;

    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location createLocation(CreateLocationRequest request) {
        Location location = new Location();
        location.setName(request.getName());
        location.setDescription(request.getDescription());
        location.setAddress(request.getAddress());
        location.setCity(request.getCity());
        location.setCountry(request.getCountry());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());

        return locationDAO.save(location);
    }

    public List<Location> getAllLocations() {
        return locationDAO.findAll();
    }

    public Location getLocationById(UUID id) {
        return findLocationById(id);
    }

    public Location updateLocation(UUID id, UpdateLocationRequest request) {
        Location location = findLocationById(id);
        location.setName(request.getName());
        location.setDescription(request.getDescription());
        location.setAddress(request.getAddress());
        location.setCity(request.getCity());
        location.setCountry(request.getCountry());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());

        return locationDAO.save(location);
    }

    public void deleteLocation(UUID id) {
        Location location = findLocationById(id);
        locationDAO.delete(location);
    }

    private Location findLocationById(UUID id) {
        return locationDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found"));
    }
}
