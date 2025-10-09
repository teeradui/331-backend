package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.repository.EventRepository;

import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
@Profile("db")
public class EventDaoDbImpl implements EventDao{
    final EventRepository eventRepository;

    @Override
    public  Integer getEventSize() {
        return  Math.toIntExact(eventRepository.count());
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Event getEvent(Long id){
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getEvents(String title,String description, String organizer, Pageable page) {
        return eventRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizerNameIgnoreCaseContaining(title, description,organizer, page);
    }

    // ✅ เพิ่ม (OR)
    @Override
    public Page<Event> getEventsOr(String q, Pageable pageable) {
        return eventRepository.findByTitleContainingOrDescriptionContaining(q, q, pageable);
    }

    // ✅ เพิ่ม (AND)
    @Override
    public Page<Event> getEventsAnd(String q, Pageable pageable) {
        return eventRepository.findByTitleContainingAndDescriptionContaining(q, q, pageable);
    }
}
