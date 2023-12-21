package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberConverter memberConverter;

    private MemberRepository memberRepository;

    private DepartmentRepository departmentRepository;

    public MemberServiceImpl(MemberConverter memberConverter, MemberRepository memberRepository, DepartmentRepository departmentRepository) {
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        Member member = memberConverter.toEntity(memberDTO);
        if(member.getDepartment().getId()==null){
            throw new Exception("Department id is required!");
        }else{
            Optional<Department> dep = departmentRepository.findById(member.getDepartment().getId());
            if(dep.isEmpty()){
                throw new Exception("Department doesn't exist!");
            }
        }
        memberRepository.save(member);
        return memberDTO;
        //ako department ne postoji sacuvaj i department zajedno sa Subject/om
    }

    @Override
    public List<MemberDTO> getAll() {
        return memberRepository
                .findAll()
                .stream().map(entity -> memberConverter.toDto(entity))
                .collect(Collectors.toList());
    }
    public List<MemberDTO> getAllByDepartmentId(Long id) {
        return memberRepository
                .findAllByDepartmentId(id)
                .stream().map(entity -> memberConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            memberRepository.delete(member1);
        } else {
            throw new Exception("Member does not exist!");
        }
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO, Long id) throws Exception {
            Member member1 = memberConverter.toEntity(memberDTO);
            member1 = memberRepository.save(member1);
            return memberConverter.toDto(member1);
    }

    @Override
    public MemberDTO findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            return memberConverter.toDto(member1);
        } else {
            throw new Exception("Member title does not exist!");
        }
    }
}
