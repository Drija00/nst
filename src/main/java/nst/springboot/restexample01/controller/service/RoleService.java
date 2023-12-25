package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO save(RoleDTO roleDTO)throws Exception;
    List<RoleDTO> getAll();
    void delete(Long id) throws Exception;
    void update(RoleDTO roleDTO)throws Exception;
    RoleDTO findById(Long id)throws Exception;
}
