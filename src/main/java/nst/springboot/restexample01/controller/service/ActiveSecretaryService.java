package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.ActiveHeadDTO;
import nst.springboot.restexample01.dto.ActiveSecretaryDTO;

import java.util.List;

public interface ActiveSecretaryService {

    ActiveSecretaryDTO save(ActiveSecretaryDTO activeSecretaryDTO)throws Exception;
    List<ActiveSecretaryDTO> getAll();
    void delete(Long id) throws Exception;
    void update(ActiveSecretaryDTO activeSecretaryDTO)throws Exception;
    ActiveSecretaryDTO findById(Long id)throws Exception;

}
