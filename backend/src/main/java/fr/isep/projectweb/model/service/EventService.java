package fr.isep.projectweb.model.service;

import fr.isep.projectweb.model.dao.EventRepository;
import fr.isep.projectweb.model.dao.LocationDAO;
import fr.isep.projectweb.model.dto.request.EventRequest;
import fr.isep.projectweb.model.entity.Event;
import fr.isep.projectweb.model.entity.Location;
import fr.isep.projectweb.model.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private static final int SEARCH_RESULT_LIMIT = 20;

    private final EventRepository eventRepository;
    private final LocationDAO locationDAO;

    public EventService(EventRepository eventRepository, LocationDAO locationDAO) {
        this.eventRepository = eventRepository;
        this.locationDAO = locationDAO;
    }

    public Event createEvent(EventRequest request, User organizer) {
        Location location = locationDAO.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setCategory(request.getCategory());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setCapacity(request.getCapacity());
        event.setPrice(request.getPrice());
        event.setIsVirtual(request.getIsVirtual());
        event.setStatus(request.getStatus());
        event.setLocation(location);
        event.setOrganizer(organizer);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByOrganizerId(UUID organizerId) {
        return eventRepository.findByOrganizerIdOrderByStartTimeAsc(organizerId);
    }

    public List<Event> getEventsByLocationId(UUID locationId) {
        return eventRepository.findByLocationIdOrderByStartTimeAsc(locationId);
    }

    public List<Event> searchEvents(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Keyword must not be blank");
        }

        return eventRepository.searchByKeyword(keyword.trim(), PageRequest.of(0, SEARCH_RESULT_LIMIT));
    }

    public Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event updateEvent(UUID id, EventRequest request) {
        Event event = getEventById(id);
        Location location = locationDAO.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setCategory(request.getCategory());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setCapacity(request.getCapacity());
        event.setPrice(request.getPrice());
        event.setIsVirtual(request.getIsVirtual());
        event.setStatus(request.getStatus());
        event.setLocation(location);

        return eventRepository.save(event);
    }

    public void deleteEvent(UUID id) {
        eventRepository.deleteById(id);
    }
}
