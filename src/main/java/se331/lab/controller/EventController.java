package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;

import jakarta.annotation.PostConstruct;
import se331.lab.service.EventService;
import se331.lab.util.LabMapper;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
final EventService eventService;
    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                           @RequestParam(value = "_page", required = false) Integer page,
                                           @RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "description", required = false) String description,
                                           @RequestParam(value = "organizer", required = false) String organizer) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Event> pageOutput;
        if (title == null && description == null && organizer == null) {
            pageOutput = eventService.getEvents(perPage, page);
        } else {
            pageOutput = eventService.getEvents(title, description,organizer, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders resonseHeaders = new HttpHeaders();
        resonseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getEventDto(pageOutput.getContent()), resonseHeaders, HttpStatus.OK);
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable ("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        Event output = eventService.save(event);
        return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
    }

    @GetMapping("events/search/or")
    public ResponseEntity<?> searchEventsOr(
            @RequestParam("q") String q,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Page<Event> result = eventService.searchOr(q, PageRequest.of(page, size));
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(result.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getEventDto(result.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("events/search/and")
    public ResponseEntity<?> searchEventsAnd(
            @RequestParam("q") String q,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Page<Event> result = eventService.searchAnd(q, PageRequest.of(page, size));
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(result.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getEventDto(result.getContent()), headers, HttpStatus.OK);
    }
}
