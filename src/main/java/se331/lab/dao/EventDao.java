package se331.lab.dao;

import se331.lab.entity.Event;
import org.springframework.data.domain.Page;


public interface EventDao {
    Integer getEventSize();
    Page<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event save(Event event);
}
