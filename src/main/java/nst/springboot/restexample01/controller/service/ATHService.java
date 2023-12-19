package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.AcademicTitleDTO;

import java.util.List;

public interface ATHService {
    ATHDto save(ATHDto athDto)throws Exception;
    List<ATHDto> getAll();
    void delete(Long id) throws Exception;
    void update(ATHDto athDto)throws Exception;
    ATHDto findById(Long id)throws Exception;
}
