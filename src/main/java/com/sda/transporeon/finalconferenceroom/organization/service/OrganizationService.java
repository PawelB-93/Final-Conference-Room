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
    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    //    //TODO
//    public List<OrganizationDto> getAllOrganizations() {
//        return organizationRepository.findAll().stream().map(organizationMapper::toDto).collect(Collectors.toList());
//    }
//
//    //TODO
//    public OrganizationDto getOrganizationById(Integer id) {
//        Organization organization = organizationRepository.findById(id).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        return organizationMapper.toDto(organization);
//    }
//
//    //TODO
//    public OrganizationDto addOrganization(OrganizationDto organizationDto) {
//        Organization organization = organizationMapper.toEntity(organizationDto);
//        organizationRepository.findByOrganizationName(organizationDto.getOrganizationName()).ifPresent(org -> {
//            throw new IllegalArgumentException();
//        });
//        return organizationMapper.toDto(organizationRepository.save(organization));
//    }
//
//    //TODO
//    public void deleteOrganizationById(Integer id) {
//        Organization organization = organizationRepository.findById(id).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        organizationRepository.delete(organization);
//    }
//
//    //TODO
//    public OrganizationDto updateOrganizationById(OrganizationDto organizationDto) {
//        Organization organization = organizationRepository.findById(organizationDto.getOrganizationId()).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        organizationRepository.findByOrganizationName(organizationDto.getOrganizationName()).ifPresent(org -> {
//            throw new IllegalArgumentException();
//        });
//        if (organizationDto.getOrganizationName() != null) {
//            organizationRepository.findByOrganizationName(organizationDto.getOrganizationName()).ifPresent(org -> {
//                throw new IllegalArgumentException();
//            });
//            organization.setOrganizationName(organizationDto.getOrganizationName());
//        }
//        return organizationMapper.toDto(organizationRepository.save(organization));
//    }
    public OrganizationDto addOrganization(OrganizationRequest organizationRequest) {
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

    public OrganizationDto updateOrganization(String organizationName, OrganizationRequest organizationRequest) {
        Organization organization = organizationRepository.findByOrganizationName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        if (organizationRequest.getOrganizationName() != null) {
            organizationRepository.findByOrganizationName(organizationRequest.getOrganizationName()).ifPresent(org -> {
                throw new IllegalArgumentException();
            });
            organization.setOrganizationName(organizationRequest.getOrganizationName());
        }
        return organizationMapper.toDto(organizationRepository.save(organization));
    }

    public OrganizationDto getOrganizationByName(String organizationName) {
        Organization organization = organizationRepository.findByOrganizationName(organizationName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        return organizationMapper.toDto(organization);
    }

    public List<OrganizationDto> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organizationMapper::toDto).collect(Collectors.toList());
    }
}
