package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.controller.service.ScientificFieldService;
import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.ScientificFieldDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberConverter memberConverter;
    private MemberRepository memberRepository;
    /*private AcademicTitleConverter academicTitleConverter;
    private EducationTitleConverter educationTitleConverter;
    private ScientificFieldConverter scientificFieldConverter;
    private DepartmentConverter departmentConverter;

    private AcademicTitleRepository academicTitleRepository;
    private EducationTitleRepository educationTitleRepository;
    private ScientificFieldRepository scientificFieldRepository;
    private DepartmentRepository departmentRepository;*/

    public MemberServiceImpl(MemberConverter memberConverter, MemberRepository memberRepository) {
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        Member member = memberConverter.toEntity(memberDTO);
        member = memberRepository.save(member);
        return memberConverter.toDto(member);
    }

    @Override
    public List<MemberDTO> getAll() {
        return memberRepository
                .findAll()
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
    public void update(MemberDTO memberDTO) throws Exception {
        throw new Exception();
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
