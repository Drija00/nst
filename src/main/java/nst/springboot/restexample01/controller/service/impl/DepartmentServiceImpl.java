/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.DepartmentRepository;
import nst.springboot.restexample01.controller.service.DepartmentService;
import nst.springboot.restexample01.converter.impl.DepartmentConverter;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author student2
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentConverter departmentConverter;
    private MemberConverter memberConverter;
    private DepartmentRepository departmentRepository;


    public DepartmentServiceImpl(DepartmentConverter departmentConverter, DepartmentRepository departmentRepository) {
        this.departmentConverter = departmentConverter;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) throws Exception {
        Optional<Department> dept = departmentRepository.findByName(departmentDto.getName());
        if (dept.isPresent()) {
            throw new DepartmentAlreadyExistException("Department sa tim imenom postoji!");
        } else {
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            Department department = departmentConverter.toEntity(departmentDto);
            department = departmentRepository.save(department);
            return departmentConverter.toDto(department);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        //proveri da li postoji department
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            departmentRepository.delete(department);
        } else {
            throw new Exception("Department does not exist!");
        }

    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) throws Exception {
        Optional<Department> dept = departmentRepository.findById(departmentDto.getId());
        if (dept.isEmpty()) {
            throw new DepartmentAlreadyExistException("Department ne postoji!");
        }
        Optional<Department> dept1 = departmentRepository.findByName(departmentDto.getName());
        if (dept1.isPresent()) {
            throw new DepartmentAlreadyExistException("Department sa tim imenom postoji!");
        }
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            Department department = departmentConverter.toEntity(departmentDto);
            department = departmentRepository.save(department);
            return departmentConverter.toDto(department);
    }

    @Override
    public DepartmentDto findById(Long id) throws Exception {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            //department.setAllMembers();
            return departmentConverter.toDto(department);
        } else {
            throw new Exception("Department does not exist!");
        }
    }

    @Override
    public List<DepartmentDto> getAll(Pageable pageable) {
        return departmentRepository
                .findAll(pageable).getContent()
                .stream().map(entity -> departmentConverter.toDto(entity))
                .collect(Collectors.toList());
    }
@Override
    public List<DepartmentDto> getAll() {
        return departmentRepository
                .findAll()
                .stream().map(entity -> departmentConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> getDepartmentMembers(Long id) throws Exception{
        /*Optional<List<Member>> members = departmentRepository.findMembers(id);
        //find deprart...
        //
        if (members.isPresent()) {
            //return members.stream().map(entity -> memberConverter.toDto(entity))
                    //.collect(Collectors.toList());*/
            return null;
        /*} else {
            throw new Exception("Error!");
        }*/
    }

}
