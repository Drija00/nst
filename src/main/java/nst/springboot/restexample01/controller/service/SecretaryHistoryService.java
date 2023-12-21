package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.controller.domain.ScientificField;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.SecretaryHistoryDTO;

import java.util.List;

public interface SecretaryHistoryService {
    SecretaryHistoryDTO save(SecretaryHistoryDTO secretaryHistoryDTO)throws Exception;
    List<SecretaryHistoryDTO> getAll();
    List<SecretaryHistoryDTO> getAllByDepartmentId(Long id);
    void delete(Long id) throws Exception;
    void update(SecretaryHistoryDTO secretaryHistoryDTO)throws Exception;
    SecretaryHistoryDTO findById(Long id)throws Exception;
}