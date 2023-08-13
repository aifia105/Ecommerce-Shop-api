package com.PersonalProject.Jemo.dto;

import com.PersonalProject.Jemo.model.Role;
import com.PersonalProject.Jemo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Long id;

    private String roleName;

    @JsonIgnore
    private User user;

    public static RoleDto fromEntity(Role role){
        if (role == null) {
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
    public static Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        role.setUser(UserDto.toEntity(UserDto.fromEntity(dto.getUser())));
        return role;
    }
}

