package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationTransformer {

    public OrganizationDto entityToDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName(organization.getName());
        List<String> conferenceRooms = organization.getConferenceRooms().stream().map(ConferenceRoom::getRoomName).collect(Collectors.toList());
        organizationDto.setConferenceRoomDtoList(conferenceRooms);
        return organizationDto;
    }

    public Organization requestToEntity(OrganizationRequest organizationRequest) {
        Organization organization = new Organization();
        organization.setName(organizationRequest.getOrganizationName());
        return organization;
    }

}
