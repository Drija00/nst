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

import java.util.ArrayList;

/**
 *
 * @author student2
 */

@Component
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department>{


    @Override
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName(), entity.getShortname());
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName(),dto.getShortname());
    }



}
