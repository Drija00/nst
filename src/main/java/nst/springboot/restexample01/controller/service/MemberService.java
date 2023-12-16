package nst.springboot.restexample01.controller.service;

import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO save(MemberDTO memberDTO)throws Exception;
    List<MemberDTO> getAll();
    void delete(Long id) throws Exception;
    void update(MemberDTO memberDTO)throws Exception;
    MemberDTO findById(Long id)throws Exception;
}
