package com.sda.transporeon.finalconferenceroom.conference_room.rest;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomDto;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/room")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> addRoom(@RequestBody ConferenceRoomRequest conferenceRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.addConferenceRoom(conferenceRoomRequest));
    }

    @GetMapping("{organizationName}")
    public ResponseEntity<List<ConferenceRoomDto>> getAllRoomsByOrganization(@PathVariable String organizationName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.getAllRoomsByOrganization(organizationName));
    }

}
