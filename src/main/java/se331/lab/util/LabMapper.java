package se331.lab.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.entity.*;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto (List <Event> events);
    @Mapping(source = "images", target = "images")  // ✅ บังคับ mapping ตรงนี้
    @Mapping(source = "ownEvents", target = "ownEvents")
    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO (List<Organizer> organizers);
    ParticipantDTO getParticipantDto(Participant participant);
    List<ParticipantDTO> getParticipantDTO(List<Participant> participants);


}


