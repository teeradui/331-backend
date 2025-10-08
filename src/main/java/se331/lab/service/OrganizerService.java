package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Organizer;
import java.util.List;
import java.util.Optional;

public interface OrganizerService {
    List<Organizer> getAllOrganizers();
    Page<Organizer> getOrganizer(Integer page, Integer pageSize);

    Optional<Organizer> getById(Long id);
    Organizer save(Organizer organizer);



    /*Integer getOrganizerCount();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);*/
}
