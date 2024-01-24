package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {
    MemberDTO save(MemberHeadSecDTO memberDTO)throws Exception;
    MemberDTO update(MemberHeadSecDTO memberDTO, Long id)throws Exception;
    List<MemberDTO> getAll();
    List<MemberDTO> getAllByDepartmentId(Long id);
    void delete(Long id) throws Exception;
    MemberDTO setSec(Long idM, DatesDTO datesDTO)throws Exception;
    MemberDTO setHead(Long idM, DatesDTO datesDTO)throws Exception;
    MemberDTO removeHead(Long idM)throws Exception;
    MemberDTO removeSec(Long idM)throws Exception;
    MemberDTO findById(Long id)throws Exception;
    MemberHistoriesDTO findAllHistories(Long id) throws Exception;
}
