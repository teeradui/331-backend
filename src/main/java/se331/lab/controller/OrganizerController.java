package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;
    @GetMapping("/organizer")
    ResponseEntity<?> getOrganizers() {
        return ResponseEntity.ok(organizerService.getAllOrganizers());
    }

    /*private final OrganizerService organizerService;

    // GET /organizers?_limit=2&_page=1
    @GetMapping("/organizers")
    public ResponseEntity<List<Organizer>> getOrganizers(
            @RequestParam(value = "_limit", required = false) Integer limit,
            @RequestParam(value = "_page",  required = false) Integer page) {

        int total = organizerService.getOrganizerCount();
        List<Organizer> data = organizerService.getOrganizers(limit, page);

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(total)) // เผื่อ client ใช้ทำ pagination
                .body(data);
    }

    // GET /organizers/{id}
    @GetMapping("/organizers/{id}")
    public ResponseEntity<Organizer> getOrganizer(@PathVariable Long id) {
        Organizer o = organizerService.getOrganizer(id);
        if (o == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer id not found");
        }
        return ResponseEntity.ok(o);
    }

    /*@PostMapping("/organizers")
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer body) {
        if (body.getOrganizationName() == null || body.getOrganizationName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "organizationName is required");
        }
        Organizer saved = organizerService.save(body);
        return ResponseEntity
                .created(URI.create("/organizers/" + saved.getId()))
                .body(saved);
    }*/
}
