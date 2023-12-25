package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.Role;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements DtoEntityConverter<RoleDTO, Role> {
    @Override
    public RoleDTO toDto(Role role) {
        return new RoleDTO(role.getId(),role.getName());
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        return new Role(roleDTO.getId(), roleDTO.getName());
    }

}
