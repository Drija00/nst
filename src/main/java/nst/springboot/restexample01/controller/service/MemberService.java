package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO save(MemberDTO memberDTO)throws Exception;
    List<MemberDTO> getAll();
    List<MemberDTO> getAllByDepartmentId(Long id);
    void delete(Long id) throws Exception;
    MemberDTO update(MemberDTO memberDTO, Long id)throws Exception;
    MemberDTO findById(Long id)throws Exception;
}
