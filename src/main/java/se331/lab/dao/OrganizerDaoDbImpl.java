/*package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;
import se331.lab.repository.OrganizerRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizerDaoDbImpl implements OrganizerDao {
    private final OrganizerRepository repo;

    @Override
    public Integer getOrganizerCount() {
        return Math.toIntExact(repo.count());
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        int ps = (pageSize == null || pageSize < 1) ? 10 : Math.min(pageSize, 100);
        int p  = (page == null || page < 1) ? 1 : page;
        Pageable pageable = PageRequest.of(p - 1, ps, Sort.by("id").ascending());
        return repo.findAll(pageable).getContent();
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Organizer save(Organizer o) {
        return repo.save(o);
    }
}*/
