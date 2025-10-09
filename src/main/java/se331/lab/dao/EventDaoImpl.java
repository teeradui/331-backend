package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.repository.EventRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class EventDaoImpl implements EventDao{
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
        return eventRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizerNameIgnoreCaseContaining(title, description, organizer, page);
    }

    @Override
    public Page<Event> getEventsOr(String q, Pageable page) {
        return eventRepository.findByTitleContainingOrDescriptionContaining(q, q, page);
    }

    @Override
    public Page<Event> getEventsAnd(String title, Pageable page) {
        return eventRepository.findByTitleContainingAndDescriptionContaining(title, title, page);
    }

}
