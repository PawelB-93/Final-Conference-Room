package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomDto;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomTransformer {

    public ConferenceRoomDto entityToDto(ConferenceRoom conferenceRoom) {
        ConferenceRoomDto conferenceRoomDto = new ConferenceRoomDto();
        conferenceRoomDto.setConferenceRoomName(conferenceRoom.getRoomName());
        conferenceRoomDto.setOrganizationName(conferenceRoom.getOrganization().getName());
        conferenceRoomDto.setSittingPlaces(conferenceRoom.getSittingPlaces());
        conferenceRoomDto.setStandingPlaces(conferenceRoom.getStandingPlaces());
        conferenceRoomDto.setLevel(conferenceRoom.getLevel());
        conferenceRoomDto.setAvailability(conferenceRoom.isAvailability());
        return conferenceRoomDto;
    }

    public ConferenceRoom requestToEntity(ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setRoomName(conferenceRoomRequest.getConferenceRoomName());
        conferenceRoom.setLevel(conferenceRoomRequest.getLevel());
        conferenceRoom.setSittingPlaces(conferenceRoomRequest.getSittingPlaces());
        conferenceRoom.setStandingPlaces(conferenceRoomRequest.getStandingPlaces());
        conferenceRoom.setAvailability(true);
        Organization organization = new Organization();
        organization.setName(conferenceRoomRequest.getOrganizationName());
        conferenceRoom.setOrganization(organization);
        return conferenceRoom;
    }
}
