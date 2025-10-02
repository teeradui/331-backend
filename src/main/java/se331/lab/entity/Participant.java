package se331.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude

    Long id;
    String name;
    String telNo;
    @ManyToMany
    List<Event> eventHistory;
}
