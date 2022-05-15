package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import com.sda.transporeon.finalconferenceroom.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody final OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.addOrganization(organizationRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getAllOrganizations());
    }

    @DeleteMapping("/{organizationName}")
    public ResponseEntity<OrganizationDto> removeOrganization(@PathVariable String organizationName) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.removeOrganization(organizationName));
    }

    @PutMapping("/{organizationName}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable String organizationName, @RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.updateOrganization(organizationName, organizationRequest));
    }

}
