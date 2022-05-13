package com.sda.transporeon.finalconferenceroom.organization.model;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizationId;
    private String name;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    private List<ConferenceRoom> conferenceRooms = new ArrayList<>();
}
