package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationResponse;
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
    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    public OrganizationResponse addOrganization(OrganizationRequest organizationRequest) {
        Organization organization = organizationMapper.toEntity(organizationRequest);
        organizationRepository.findByOrganizationName(organization.getOrganizationName()).ifPresent(org -> {
            throw new IllegalArgumentException();
        });
        return organizationMapper.toDto(organizationRepository.save(organization));
    }

    public void deleteOrganization(String organizationName) {
        Organization organization = organizationRepository.findByOrganizationName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        organizationRepository.delete(organization);
    }

    public OrganizationResponse updateOrganization(String organizationName, OrganizationRequest organizationRequest) {
        Organization organization = organizationRepository.findByOrganizationName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        organizationRepository.findByOrganizationName(organizationRequest.getOrganizationName()).ifPresent(org -> {
            throw new IllegalArgumentException();
        });
        organization.setOrganizationName(organizationRequest.getOrganizationName());

        return organizationMapper.toDto(organizationRepository.save(organization));
    }

    public OrganizationResponse getOrganizationByName(String organizationName) {
        Organization organization = organizationRepository.findByOrganizationName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        return organizationMapper.toDto(organization);
    }

    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organizationMapper::toDto).collect(Collectors.toList());
    }
}
