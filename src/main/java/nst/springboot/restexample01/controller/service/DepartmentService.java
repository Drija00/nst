/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.controller.service;

import java.util.List;

import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.data.domain.Pageable;
/**
 *
 * @author student2
 */
public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;
    List<DepartmentDto> getAll(Pageable pageable);
    List<DepartmentDto> getAll();
    List<MemberDTO> getDepartmentMembers(Long id) throws Exception;
    void delete(Long id) throws Exception;
    void update(DepartmentDto department) throws Exception;
    DepartmentDto findById(Long id) throws Exception;
}
