/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author student2
 */

@Component
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department>{

    @Autowired
    private MemberConverter memberConverter;

    @Override
    @JsonIgnore
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName(), entity.getShortname(),memberConverter.toDto(entity.getHead()),memberConverter.toDto(entity.getSecretary()));
    }

    @Override
    @JsonIgnore
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName(),dto.getShortname(),memberConverter.toEntity(dto.getHead()),memberConverter.toEntity(dto.getSecretary()));
    }
    
}
