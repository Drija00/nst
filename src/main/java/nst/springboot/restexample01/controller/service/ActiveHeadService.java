package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.ActiveHeadDTO;
import nst.springboot.restexample01.dto.HeadHistoryDTO;

import java.util.List;

public interface ActiveHeadService {
    ActiveHeadDTO save(ActiveHeadDTO activeHeadDTO)throws Exception;
    List<ActiveHeadDTO> getAll();
    void delete(Long id) throws Exception;
    void update(ActiveHeadDTO activeHeadDTO)throws Exception;
    ActiveHeadDTO findById(Long id)throws Exception;

}
