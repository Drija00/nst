package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.EducationTitleDTO;

import java.util.List;

public interface EducationTitleService {
    EducationTitleDTO save(EducationTitleDTO educationTitleDTO)throws Exception;
    List<EducationTitleDTO> getAll();
    void delete(Long id) throws Exception;
    void update(EducationTitleDTO educationTitleDTO)throws Exception;
    EducationTitleDTO findById(Long id)throws Exception;
}
