package com.sda.transporeon.finalconferenceroom.organization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long organizationId;
    private String organizationName;
    private String numberOfRooms;
}
