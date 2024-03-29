package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.controller.HeadHistoryController;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import nst.springboot.restexample01.dto.MemberDTO;

import java.util.List;

public interface HeadHistoryService {
    HeadHistoryDTO save(HeadHistoryDTO headHistory)throws Exception;
    List<HeadHistoryDTO> getAll();
    HeadHistoryDTO getByMemberIdAndDepartmentId(Long idD) throws Exception;
    void delete(Long id) throws Exception;
    void update(HeadHistoryDTO headHistory)throws Exception;
    HeadHistoryDTO findById(Long id)throws Exception;
}
