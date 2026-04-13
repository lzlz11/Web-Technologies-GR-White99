package fr.isep.projectweb.model.service;

import fr.isep.projectweb.model.dao.EventRepository;
import fr.isep.projectweb.model.dao.LocationDAO;
import fr.isep.projectweb.model.dto.request.CreateEventRequest;
import fr.isep.projectweb.model.dto.request.UpdateEventRequest;
import fr.isep.projectweb.model.entity.Event;
import fr.isep.projectweb.model.entity.Location;
import fr.isep.projectweb.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final LocationDAO locationDAO;

    public EventService(EventRepository eventRepository, LocationDAO locationDAO) {
        this.eventRepository = eventRepository;
        this.locationDAO = locationDAO;
    }

    public Event createEvent(CreateEventRequest request, User organizer) {
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

    public Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event updateEvent(UUID id, UpdateEventRequest request) {
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
