package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationTransformer organizationTransformer;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, OrganizationTransformer organizationTransformer) {
        this.organizationRepository = organizationRepository;
        this.organizationTransformer = organizationTransformer;
    }

    public OrganizationDto addOrganization(OrganizationRequest organizationRequest) {
        Organization organization = organizationTransformer.requestToEntity(organizationRequest);
        organizationRepository.findByName(organization.getName()).ifPresent(org -> {
            throw new IllegalArgumentException();
        });
        return organizationTransformer.entityToDto(organizationRepository.save(organization));
    }

    public OrganizationDto removeOrganization(String organizationName) {
        Organization organization = organizationRepository.findByName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        organizationRepository.delete(organization);
        return organizationTransformer.entityToDto(organization);
    }

    public OrganizationDto updateOrganization(OrganizationRequest organizationRequest) {
        Organization organization = organizationRepository.findByName(organizationRequest.getOrganizationName()).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        if (organizationRequest.getOrganizationName() != null) {
            organization.setName(organizationRequest.getOrganizationName());
        }
        return organizationTransformer.entityToDto(organizationRepository.save(organization));
    }

    public OrganizationDto getOrganizationByName(String name) {
        Organization organization = organizationRepository.findByName(name).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        return organizationTransformer.entityToDto(organization);
    }

    public List<OrganizationDto> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organizationTransformer::entityToDto).collect(Collectors.toList());
    }
}
