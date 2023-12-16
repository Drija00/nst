package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.AcademicTitleDTO;

import java.util.List;

public interface AcademicTitleService {
    AcademicTitleDTO save(AcademicTitleDTO academicTitleDTO)throws Exception;
    List<AcademicTitleDTO> getAll();
    void delete(Long id) throws Exception;
    void update(AcademicTitleDTO academicTitleDTO)throws Exception;
    AcademicTitleDTO findById(Long id)throws Exception;
}
