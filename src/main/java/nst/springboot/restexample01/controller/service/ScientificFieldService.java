package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.ScientificFieldDTO;
import nst.springboot.restexample01.dto.SubjectDto;

import java.util.List;

public interface ScientificFieldService {
    ScientificFieldDTO save(ScientificFieldDTO scientificFieldDTO)throws Exception;
    List<ScientificFieldDTO> getAll();
    void delete(Long id) throws Exception;
    void update(ScientificFieldDTO scientificFieldDTO)throws Exception;
    ScientificFieldDTO findById(Long id)throws Exception;
}
