package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
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

    @GetMapping()
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getAllOrganizations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOrganizationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.addOrganization(organizationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("id") Integer id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") Integer id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.updateOrganizationById(id, organizationDto));
    }
}
