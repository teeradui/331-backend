package se331.lab.dao;

import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerDao {
    Integer getOrganizerCount();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
}