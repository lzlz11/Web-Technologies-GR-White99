package fr.isep.projectweb.controller;

import fr.isep.projectweb.model.dto.request.CreateEventRequest;
import fr.isep.projectweb.model.dto.request.UpdateEventRequest;
import fr.isep.projectweb.model.entity.Event;
import fr.isep.projectweb.model.entity.User;
import fr.isep.projectweb.model.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "CRUD endpoints for events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @Operation(summary = "Create an event")
    public Event createEvent(@RequestBody CreateEventRequest request, @AuthenticationPrincipal User user) {
        return eventService.createEvent(request, user);
    }

    @GetMapping
    @Operation(summary = "Get all events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one event by id")
    public Event getEventById(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an event")
    public Event updateEvent(@PathVariable UUID id, @RequestBody UpdateEventRequest request) {
        // TODO: Add authorization check to ensure only the organizer can update the event
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        // TODO: Add authorization check to ensure only the organizer can delete the event
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
