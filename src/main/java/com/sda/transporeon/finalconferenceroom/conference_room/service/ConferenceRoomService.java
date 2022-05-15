package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.NoConferenceRoomFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomDto;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.exception.NoOrganizationFoundException;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomTransformer conferenceRoomTransformer;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository, ConferenceRoomTransformer conferenceRoomTransformer,
                                 OrganizationRepository organizationRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.conferenceRoomTransformer = conferenceRoomTransformer;
        this.organizationRepository = organizationRepository;
    }

    public ConferenceRoomDto addConferenceRoom(ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.requestToEntity(conferenceRoomRequest);
        conferenceRoomRepository.findByRoomName(conferenceRoom.getRoomName()).ifPresent(room -> {
            throw new ConferenceRoomAlreadyExistException(conferenceRoomRequest.getConferenceRoomName());
        });
        Organization organization = organizationRepository.findByName(conferenceRoom.getOrganization().getName()).orElseThrow(() -> {
            throw new NoOrganizationFoundException(conferenceRoomRequest.getOrganizationName());
        });
        conferenceRoom.setOrganization(organization);
        return conferenceRoomTransformer.entityToDto(conferenceRoomRepository.save(conferenceRoom));
    }

    //add findAll
    public List<ConferenceRoomDto> getAllRoomsByOrganization(String organizationName) {
        return conferenceRoomRepository.findByOrganization_Name(organizationName).stream()
                .map(conferenceRoomTransformer::entityToDto)
                .collect(Collectors.toList());
    }

    public ConferenceRoomDto removeConferenceRoom(String roomName) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByRoomName(roomName).orElseThrow(() -> {
            throw new NoConferenceRoomFoundException(roomName);
        });
        conferenceRoomRepository.delete(conferenceRoom);
        return conferenceRoomTransformer.entityToDto(conferenceRoom);
    }

    //remove updating with organization
    public ConferenceRoomDto updateConferenceRoom(String roomName, ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByRoomName(roomName).orElseThrow(() -> {
            throw new NoConferenceRoomFoundException(roomName);
        });
        if (conferenceRoomRequest.getConferenceRoomName() != null) {
            conferenceRoomRepository.findByRoomName(conferenceRoomRequest.getConferenceRoomName()).ifPresent(room -> {
                throw new ConferenceRoomAlreadyExistException(conferenceRoomRequest.getConferenceRoomName());
            });
            conferenceRoom.setRoomName(conferenceRoomRequest.getConferenceRoomName());
        }
        if (conferenceRoomRequest.getOrganizationName() != null) {
            Organization organization = organizationRepository.findByName(conferenceRoomRequest.getOrganizationName()).orElseThrow(() -> {
                throw new NoOrganizationFoundException(conferenceRoomRequest.getOrganizationName());
            });
            conferenceRoom.setOrganization(organization);
        }
        if (conferenceRoomRequest.getLevel() != null) {
            conferenceRoom.setLevel(conferenceRoomRequest.getLevel());
        }
        if (conferenceRoomRequest.getSittingPlaces() != null) {
            conferenceRoom.setSittingPlaces(conferenceRoomRequest.getSittingPlaces());
        }
        if (conferenceRoomRequest.getStandingPlaces() != null) {
            conferenceRoom.setStandingPlaces(conferenceRoomRequest.getStandingPlaces());
        }
        return conferenceRoomTransformer.entityToDto(conferenceRoomRepository.save(conferenceRoom));
    }

}
